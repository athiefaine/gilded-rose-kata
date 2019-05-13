package com.gildedrose;

public class ItemUpdater {

    private Item item;

    public ItemUpdater() {
    }

    public ItemUpdater(Item item) {
        this.item = item;
    }

    void updateQuality() {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        item.sellIn = item.sellIn - 1;

        if (item.name.equals("Aged Brie")) {
            increaseQuality();
            if (item.sellIn < 0) increaseQuality();
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality();

            if (item.sellIn < 10) increaseQuality();
            if (item.sellIn < 5) increaseQuality();
            if (item.sellIn < 0) item.quality = item.quality - item.quality;
        } else {
            decreaseQuality();
            if (item.sellIn < 0) decreaseQuality();
        }
    }

    void increaseQuality() {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    void decreaseQuality() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }
}