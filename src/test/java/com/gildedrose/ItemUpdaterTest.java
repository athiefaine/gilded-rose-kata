package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemUpdaterTest {


    @Test
    public void should_Sulfuras_quality_and_sellIn_never_change() {
        int initialSellIn = 0;
        int initialQuality = 80;
        Item item = new Item("Sulfuras, Hand of Ragnaros", initialSellIn, initialQuality);

        for (int i = 0; i < 16; i++) {
            new ItemUpdater(item).updateQuality();

            assertThat(item.quality).isEqualTo(initialQuality);
            assertThat(item.sellIn).isEqualTo(initialSellIn);
        }
    }

    @Test
    public void should_item_name_never_change() {
        int initialSellIn = 10;
        int initialQuality = 20;
        String initialName= "Aged Brie";
        Item item = new Item(initialName, initialSellIn, initialQuality);

        for (int i = 0; i < 16; i++) {
            new ItemUpdater(item).updateQuality();

            assertThat(item.name).isEqualTo(initialName);
        }
    }


    @Test
    public void should_item_sellIn_decrease_daily() {
        int initialSellIn = 10;
        int initialQuality = 20;
        Item item = new Item("Aged Brie", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.sellIn).isEqualTo(initialSellIn - 1);

        new ItemUpdater(item).updateQuality();

        assertThat(item.sellIn).isEqualTo(initialSellIn - 2);
    }

    @Test
    public void should_item_quality_never_exceed_50() {
        int initialSellIn = 10;
        int initialQuality = 50;
        Item item = new Item("Aged Brie", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality);
    }

    @Test
    public void should_item_quality_never_fall_behind_0() {
        int initialSellIn = 10;
        int initialQuality = 0;
        Item item = new Item("Squared Egg", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality);
    }

    @Test
    public void should_AgedBrie_quality_increase_daily() {
        int initialSellIn = 2;
        int initialQuality = 20;
        Item item = new Item("Aged Brie", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 1);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 2);
    }

    @Test
    public void should_AgedBrie_quality_increase_twice_as_fast_when_sellIn_is_elapsed() {
        int initialSellIn = -1;
        int initialQuality = 20;
        Item item = new Item("Aged Brie", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 2);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 4);
    }

    @Test
    public void should_default_item_quality_decrease_daily() {
        int initialSellIn = 2;
        int initialQuality = 20;
        Item item = new Item("Squared Egg", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 1);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 2);
    }


    @Test
    public void should_default_item_quality_decrease_twice_as_fast_when_sellIn_is_elapsed() {
        int initialSellIn = -1;
        int initialQuality = 20;
        Item item = new Item("Squared Egg", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 2);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 4);
    }

    @Test
    public void should_Backstage_quality_increase_daily() {
        int initialSellIn = 15;
        int initialQuality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 1);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 2);
    }

    @Test
    public void should_Backstage_quality_increase_twice_as_fast_when_sellIn_is_below_ten_days() {
        int initialSellIn = 10;
        int initialQuality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 2);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 4);
    }

    @Test
    public void should_Backstage_quality_increase_thrice_as_fast_when_sellIn_is_below_five_days() {
        int initialSellIn = 5;
        int initialQuality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 3);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality + 6);
    }

    @Test
    public void should_Backstage_quality_be_zero_when_sellIn_is_elapsed() {
        int initialSellIn = 0;
        int initialQuality = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    public void should_conjured_item_quality_decrease_twice_as_fast_daily() {
        int initialSellIn = 10;
        int initialQuality = 20;
        Item item = new Item("Conjured", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 2);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 4);
    }

    @Test
    public void should_conjured_item_quality_decrease_four_time_as_fast_when_sellIn_is_elapsed() {
        int initialSellIn = -1;
        int initialQuality = 20;
        Item item = new Item("Conjured", initialSellIn, initialQuality);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 4);

        new ItemUpdater(item).updateQuality();

        assertThat(item.quality).isEqualTo(initialQuality - 8);
    }


}
