package com.gildedrose;

class GildedRose {
    private final ItemUpdater itemUpdater = new ItemUpdater();
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            new ItemUpdater(item).updateQuality();
        }
    }

}