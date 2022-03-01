package io.muzoo.ssc.zork.map.item;

public enum ItemType {

    WEAPON("WEAPON"),
    SHIELD("SHIELD"),
    POTION("POTION");

    private String itemTypeString;

    ItemType(String itemTypeString) {
        this.itemTypeString = itemTypeString;
    }

    public String getItemTypeString() {
        return itemTypeString;
    }

    public static ItemType getItemType(String itemTypeString) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.getItemTypeString().equals(itemTypeString)) {
                return itemType;
            }
        }
        return null;
    }

}
