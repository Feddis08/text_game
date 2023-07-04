package at.feddis08.modules.events;

import at.feddis08.modules.GameObject;
import at.feddis08.world.Room;
import at.feddis08.world.entities.Entity;

import java.util.ArrayList;

public class Entity_looks_at_object_event extends Event{
    public GameObject gameObject;
    public String response;
    public Entity_looks_at_object_event(Entity entity, GameObject gameObject) {
        super("entity_looks_at_object_event", entity);
        this.gameObject = gameObject;
    }
}
