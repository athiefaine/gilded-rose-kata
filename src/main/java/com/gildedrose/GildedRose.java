package com.gildedrose;

class GildedRose {
    private final ItemUpdater itemUpdater = new ItemUpdater();
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            new ItemUpdater(items[i]).updateQuality();
        }
    }

}