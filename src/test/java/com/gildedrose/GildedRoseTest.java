package com.gildedrose;

import static org.junit.Assert.*;

import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GildedRoseTest {

    Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20), //
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            // this conjured item does not work properly yet
            new Item("Conjured Mana Cake", 3, 6) };

    @Test
    public void testWithApprovals() {
        GildedRose gildedRose = new GildedRose(items);
        List<String> itemsHistory = new ArrayList<>();
        IntStream.range(0, 100).forEach(day -> {
            gildedRose.updateQuality();
            itemsHistory.addAll(Arrays.stream(items)
                    .map(item -> "Day " + day + ": " + item.toString())
                    .collect(Collectors.toList()));

        });
        Approvals.verifyAll("", itemsHistory);
    }

}
