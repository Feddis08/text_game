package at.feddis08.modules;

import java.util.ArrayList;
import java.util.Objects;

public class Inventory {
    public String id;
    public int size;
    public ArrayList<Item> items = new ArrayList<>();

    public Inventory(int size){
        this.size = size;
    }

    public boolean add_item(Item i){
        if (items.size() != size){
            items.add(i);
        }
        return (items.size() != size);
    }
    public Item find_item_by_id(String id){
        Item item = null;
        for (Item i : items){
            if (Objects.equals(i.id, id)){
                item = i;
            }
        }
        return item;
    }
    public void remove_item_by_id(String id){
         items.removeIf(i -> Objects.equals(i.id, id));
    }
}
