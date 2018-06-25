
public class Inventory {
    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            String name = item.getName();
            int currentQ = item.getQuality();
            int currentS = item.getSellIn();
            if(currentQ >0 && currentQ<50)
                setQuality(name, item, currentQ, setSellIn(item, currentS, name));
        }
    }
    public int setSellIn(Item item, int previousS, String name){
        int newS = previousS -1;
        item.setSellIn(newS);
        int adjustment = (newS <= 0 || name.equals("Conjured"))? 2 : 1;
        return adjustment;
    }
    public void setQuality(String name,Item item, int previousQ, int SellInAdjustment){
        if(name.equals("Aged Brie"))
            item.setQuality(previousQ + SellInAdjustment);
        else
            item.setQuality(previousQ - SellInAdjustment);
    }
}
