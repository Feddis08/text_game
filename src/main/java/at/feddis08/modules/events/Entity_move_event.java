package at.feddis08.modules.events;

import at.feddis08.world.Room;
import at.feddis08.world.entities.Entity;

public class Entity_move_event extends Event{
    public Room from_room;
    public Room to_room;
    public Entity_move_event(Entity entity, Room from_room, Room to_room) {
        super("entity_move_event", entity);
        this.from_room = from_room;
        this.to_room = to_room;
    }
}
