package items;
import exceptions.InventoryFullException;

import java.util.ArrayList;
import java.util.List;
public class Inventory {

    private ArrayList<Item> items = new ArrayList<>();

    public String addItem(Item item) {
        if (items.size() >= 20) {
            return "Plný inventář.";
        }
        items.add(item);
        return item.getName() + " byl přidán do inventáře";
    }

    public void removeItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) == item) {
                items.set(i, null);
                return;
            }
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean contains(Item item) {
        for (Item i : items) {
            if (i == item) {
                return true;
            }
        }
        return false;
    }
    public void printContents() {
        System.out.println("=== INVENTORY ===");
        for (Item item : items) {
            if (item != null) {
                System.out.println("- " + item.getName());
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
}