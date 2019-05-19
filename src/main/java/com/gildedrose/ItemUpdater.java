package com.gildedrose;

public class ItemUpdater {

    private Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    void updateQuality() {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            return;
        }

        item.sellIn = item.sellIn - 1;

        switch (item.name) {
            case "Aged Brie":
                increaseQuality();
                if (item.sellIn < 0) increaseQuality();
                break;
            case "Backstage passes to a TAFKAL80ETC concert":
                increaseQuality();

                if (item.sellIn < 10) increaseQuality();
                if (item.sellIn < 5) increaseQuality();
                if (item.sellIn < 0) item.quality = item.quality - item.quality;
                break;
            case "Conjured":
                decreaseQuality();
                decreaseQuality();
                if (item.sellIn < 0) {
                    decreaseQuality();
                    decreaseQuality();
                }
                break;
            default:
                decreaseQuality();
                if (item.sellIn < 0) decreaseQuality();
                break;
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