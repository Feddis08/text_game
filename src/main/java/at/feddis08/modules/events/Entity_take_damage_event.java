package at.feddis08.modules.events;

import at.feddis08.world.Room;
import at.feddis08.world.entities.Entity;

import java.util.ArrayList;

public class Entity_say_event extends Event{
    public String message;
    public Entity_say_event(Entity entity, String message) {
        super("entity_say_event", entity);
        this.message = message;
    }
}
