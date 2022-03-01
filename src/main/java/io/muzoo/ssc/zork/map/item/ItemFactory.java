package io.muzoo.ssc.zork.map.item;

import io.muzoo.ssc.zork.map.item.potion.PotionFactory;
import io.muzoo.ssc.zork.map.item.potion.PotionType;
import io.muzoo.ssc.zork.map.item.shield.ShieldFactory;
import io.muzoo.ssc.zork.map.item.shield.ShieldType;
import io.muzoo.ssc.zork.map.item.weapon.WeaponFactory;
import io.muzoo.ssc.zork.map.item.weapon.WeaponType;
import org.json.simple.JSONObject;

public class ItemFactory {

    public static Item createdItem(JSONObject jsonObject) {
        ItemType itemType = ItemType.getItemType((String) jsonObject.get("itemType"));
        String category = (String) jsonObject.get("category");
        String name = (String) jsonObject.get("name");
        switch (itemType) {
            case WEAPON:
                WeaponType weaponType = WeaponType.getWeaponType(category);
                int damage = ((Long) jsonObject.get("damage")).intValue();
                int durability = ((Long) jsonObject.get("durability")).intValue();
                return WeaponFactory.createdWeapon(weaponType, name, damage, durability);
            case SHIELD:
                ShieldType shieldType = ShieldType.getShieldType(category);
                int defense = ((Long) jsonObject.get("defense")).intValue();
                return ShieldFactory.createdShield(shieldType, name, defense);
            case POTION:
                PotionType potionType = PotionType.getPotionType(category);
                return PotionFactory.createdPotion(potionType, name);
            default:
                throw new RuntimeException("unknown item type");
        }
    }

}
