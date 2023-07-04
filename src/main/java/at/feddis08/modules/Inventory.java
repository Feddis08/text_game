package at.feddis08.modules;

import java.util.ArrayList;

public class Inventory {
    public String id;
    public int size;
    public ArrayList<Item> items;

    public Inventory(int size){
        this.size = size;
    }
}
