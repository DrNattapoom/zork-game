package io.muzoo.ssc.zork;

import io.muzoo.ssc.zork.map.item.Item;
import io.muzoo.ssc.zork.map.item.ItemFactory;
import io.muzoo.ssc.zork.map.item.weapon.Weapon;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class Player extends Mortal {

    private static int MAX_HP = 10;

    private List<Item> items;
    private int location;
    private int defense;
    private int mana;

    public Player(int hp, int attackPower) {
        super(hp, attackPower);
        this.items = new ArrayList<>();
    }

    public Player(JSONObject jsonObject) {
        this(
            ((Long) jsonObject.get("hp")).intValue(),
            ((Long) jsonObject.get("attackPower")).intValue()
        );
        this.location = ((Long) jsonObject.get("location")).intValue();
        this.defense = ((Long) jsonObject.get("defense")).intValue();
        this.mana = ((Long) jsonObject.get("mana")).intValue();
        JSONArray itemList = (JSONArray) jsonObject.get("items");
        for (Object object : itemList) {
            JSONObject jsonItemObject = (JSONObject) object;
            Item item = ItemFactory.createdItem(jsonItemObject);
            this.items.add(item);
        }
    }

    public void recover(int recoveredHp) {
        int currentHp = this.getHp();
        int newHp = Math.min(currentHp + recoveredHp, MAX_HP);
        this.setHp(newHp);
    }

    public int getMaxHp() {
        return MAX_HP;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public List<Item> getItems() {
        return items;
    }

    public void takeItem(Item item) {
        List<Item> sameNameItems = items.stream().filter(itm -> itm.getName().startsWith(item.getName())).collect(Collectors.toList());
        if (!sameNameItems.isEmpty()) {
            List<Integer> suffixes = new ArrayList<>();
            for (Item ownedItem : sameNameItems) {
                String ownedItemName = ownedItem.getName();
                String itemName = item.getName();
                if (ownedItemName.startsWith(itemName)) {
                    String suffixString = StringUtils.substringAfter(ownedItemName, itemName).trim();
                    int suffix = (StringUtils.isBlank(suffixString)) ? 0 : Integer.parseInt(suffixString);
                    suffixes.add(suffix);
                }
            }
            int max = Collections.max(suffixes);
            item.setName(String.format("%s %d", item.getName(), max + 1));
        }
        items.add(item);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void attackWith(Weapon weapon, Mortal enemy) {
        int originalAttackPower = this.getAttackPower();
        weapon.activate(this);
        attack(enemy);
        weapon.setDurability(weapon.getDurability() - 1);
        if (weapon.getDurability() <= 0) {
            System.out.println(weapon + " is now broken.");
            items.remove(weapon);
        } else {
            System.out.println(String.format("You can use this %s for %d more times before it breaks", weapon, weapon.getDurability()));
        }
        this.setAttackPower(originalAttackPower);
    }

    @Override
    public void attack(Mortal enemy) {
        int currentEnemyHp = enemy.getHp();
        int damage = new Random().nextInt(this.getAttackPower()) + 1;
        enemy.setHp(currentEnemyHp - damage);
    }

    @Override
    public void printStats() {
        System.out.println(
            String.format(
                "\tMax HP: %d \n" +
                "\tHP: %d \n" +
                "\tAttack Power: %d \n" +
                "\tDefense Power: %d \n" +
                "\tMana: %d \n" +
                "\tInventory: %s",
                this.getMaxHp(),
                this.getHp(),
                this.getAttackPower(),
                this.getDefense(),
                this.getMana(),
                this.getItems()
            )
        );
    }

}