package at.feddis08.modules.events;

import at.feddis08.world.entities.Entity;

public class Event {
    public String name;
    public boolean canceled;
    public Entity entity;

    public Event(String name, Entity entity){
        this.name = name;
        this.entity = entity;
    }

    public void cancel(){
        this.canceled = true;
    }
}
