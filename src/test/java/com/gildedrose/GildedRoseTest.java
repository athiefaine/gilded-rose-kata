package com.gildedrose;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    Item[] items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6)};

    Item[] legacyItems = Arrays.stream(items)
            .map(item -> new Item(item.name, item.sellIn, item.quality))
            .toArray(Item[]::new);


    @Test
    public void should_refactored_behave_exactly_as_legacy() {
        // GIVEN
        GildedRose inn = new GildedRose(items);
        LegacyGildedRose legacyInn = new LegacyGildedRose(legacyItems);

        for (int i = 0; i < 16; i++) {
            //WHEN
            inn.updateQuality();
            legacyInn.updateQuality();

            //THEN
            assertThat(inn.items).extracting("name").containsExactly(
                    Arrays.stream(legacyInn.items).map(item -> item.name).toArray());
            assertThat(inn.items).extracting("quality").containsExactly(
                    Arrays.stream(legacyInn.items).map(item -> item.quality).toArray());
            assertThat(inn.items).extracting("sellIn").containsExactly(
                    Arrays.stream(legacyInn.items).map(item -> item.sellIn).toArray());
        }
    }


}
