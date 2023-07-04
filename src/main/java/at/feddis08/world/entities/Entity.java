package at.feddis08.world.entities;

import at.feddis08.modules.Inventory;

public class Player {
    public String id;
    public String name;
    public int level;
    public int age;
    public Inventory inventory;

    Player(String name, int age){
        this.name = name;
        this.age = age;
    }

}
