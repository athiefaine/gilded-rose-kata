package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class GildedRoseDomainTest {


    @Test
    public void should_Sulfuras_quality_never_change () {
        // GIVEN
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);

        //WHEN
        new ItemUpdater(item).updateQuality();

        //THEN
        Assertions.assertThat(item.quality).isEqualTo(80);

    }

}
