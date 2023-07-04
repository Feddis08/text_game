package at.feddis08.modules.events;

import at.feddis08.world.Room;
import at.feddis08.world.entities.Entity;

import java.util.ArrayList;

public class Entity_look_event extends Event{
    public Room room;
    public ArrayList<String> response = new ArrayList<>();
    public Entity_look_event(Entity entity, Room room) {
        super("entity_look_event", entity);
        this.room = room;
    }
}
