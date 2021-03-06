import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.Before;

public class InventoryTest {
    private static final int MAX_QUALITY = 50;
    private static final String AGED_BRIE_NAME = "Aged Brie";
    private static final String BACKSTAGE_PASS_NAME = "Backstage passes to a TAFKAL80ETC concert";
    private static final String NORMAL_ITEM_NAME = "+5 Dexterity Vest";

    private Item sulfuras;
    private Item normalItem;
    private Item conjuredItem;

    @Before
    public void setup(){
        sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        normalItem = new Item(NORMAL_ITEM_NAME, 10, 20);
        conjuredItem = new Item("Conjured", 10, 20);
    }

    @Test
    public void testUpdate_ForSulfurasQuality() throws Exception {
        int expectedQuality = sulfuras.getQuality();
        Item[] items = {sulfuras};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedQuality, sulfuras.getQuality());
    }
    @Test
    public void testUpdate_ForSulfurasSellIn() throws Exception {
        int expectedSellIn = sulfuras.getSellIn();
        Item[] items = {sulfuras};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedSellIn, sulfuras.getSellIn());
    }

    @Test
    public void testUpdate_ForNormalItemSellIn() throws Exception {
        int expectedSellIn = normalItem.getSellIn() - 1;
        Item[] items = {normalItem};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedSellIn, normalItem.getSellIn());
    }
    @Test
    public void testUpdate_ForConjuredItemQuantity() throws Exception {
        int expectedQuality = conjuredItem.getQuality() - 2;
        Item[] items = {conjuredItem};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedQuality, conjuredItem.getQuality());
    }

    @Test
    public void testUpdate_ForNormalItemQuantity() throws Exception {
        int expectedQuality = normalItem.getQuality() - 1;
        Item[] items = {normalItem};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedQuality, normalItem.getQuality());
    }
    
    

    @Test
    public void testUpdate_ForSoldOutItem() throws Exception {
        int minimumQuality = 0;
        Item normalItemWithMinimumQuality = new Item(NORMAL_ITEM_NAME, 10, minimumQuality);
        Item[] items = {normalItemWithMinimumQuality};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(minimumQuality, normalItemWithMinimumQuality.getQuality());
    }

    @Test
    public void testUpdate_ForNormalItemOnceTheSellInDatePassed() throws Exception {
        Item normalItemWithPassedSellDate = new Item(NORMAL_ITEM_NAME, -1, 25);
        int expectedQuality = normalItemWithPassedSellDate.getQuality() - 2;
        Item[] items = {normalItemWithPassedSellDate};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedQuality, normalItemWithPassedSellDate.getQuality());
    }

    @Test
    public void testUpdate_AgedBrie() throws Exception {
        Item agedBrie = new Item(AGED_BRIE_NAME, 10, 25);
        int expectedQuality = agedBrie.getQuality() + 1;
        Item[] items = {agedBrie};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedQuality, agedBrie.getQuality());
    }

    @Test
    public void testUpdate_WhenAgedBrieIsAtMaxQuality() throws Exception {
        Item agedBrie = new Item(AGED_BRIE_NAME, 10, MAX_QUALITY);
        Item[] items = {agedBrie};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(MAX_QUALITY, agedBrie.getQuality());
    }

    @Test
    public void testUpdate_WhenAgedBrieOnceTheSellInDatePassed() throws Exception {
        Item agedBrie = new Item(AGED_BRIE_NAME, -1, 20);
        int expectedQuality = agedBrie.getQuality() + 2;
        Item[] items = {agedBrie};
        Inventory sut = new Inventory(items);

        sut.updateQuality();

        assertEquals(expectedQuality, agedBrie.getQuality());
    }
}
