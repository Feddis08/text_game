package at.feddis08.world.items;

import at.feddis08.modules.Item;

public class Food extends Item {

    public int saturation;
    public Food(String name) {
        super(name);
        this.saturation = 1;
    }
}
