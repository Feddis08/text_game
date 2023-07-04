package at.feddis08.modules.events;

import at.feddis08.world.entities.Door;
import at.feddis08.world.entities.Entity;

public class Entity_enter_door_event extends Event{
    public Door door;
    public Entity_enter_door_event(Entity entity, Door door) {
        super("entity_enter_door_event", entity);
        this.door = door;
    }
}
