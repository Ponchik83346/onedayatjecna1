package items;
import exceptions.InventoryFullException;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventář hráče.
 * Obsahuje list itemů.
 */
public class Inventory {

    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Přidat item.
     * @param item Item k přidání
     * @return Vrací stav jestli se přidal nebo ne.
     */
    public String addItem(Item item) {
        if (items.size() >= 20) {
            return "Plný inventář.";
        }
        items.add(item);
        return item.getName() + " byl přidán do inventáře";
    }

    /**
     * Odebrat item.
     * @param item Item k odebrání.
     */
    public void removeItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item) {
                items.remove(i);
                return;
            }
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Jestli inventář obsahuje item.
     * @param item Target item.
     * @return Jestli obsahuje nebo ne.
     */
    public boolean contains(Item item) {
        for (Item i : items) {
            if (i == item) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vypsání inventáře.
     */
    public void printContents() {
        System.out.println("=== INVENTORY ===");
        for (int i=0; i<items.size(); i++) {
            if (items.get(i) != null) {
                System.out.println("-"+i +" " +items.get(i).getName());
            }
        }
    }

    public List<Material> getMaterials() {
        List<Material> mats = new ArrayList<>();

        for (Item i : items) {
            if (i instanceof Material) {
                mats.add((Material) i);
            }
        }
        return mats;
    }

    public Item getItemByIndex(int index){
        if(index < 0 || index >= items.size())
            return null;
        return items.get(index);
    }
}