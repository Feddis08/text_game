package at.feddis08.world.entities;

import at.feddis08.Main;
import at.feddis08.modules.events.Entity_enter_door_event;
import at.feddis08.modules.events.Entity_looks_at_object_event;
import at.feddis08.modules.events.Event;
import at.feddis08.world.Room;
import at.feddis08.world.living.Human;

public class Door extends Entity{

    public String room_name1;
    public String room_name2;
    public String description;
    public Door(String name, String description, String room_name1, String room_name2) {
        super(name, 0);
        this.room_name1 = room_name1;
        this.room_name2 = room_name2;
        this.description = description;
    }

    public String entity_looks_at_object_response(){
        String str = description;
        return str;
    }
    public String get_look_response(){
        String str = name;
        return str;
    }
    public void enter(Entity entity){
        Room room_entity = Main.world.get_room_by_gameobject_id(entity.id);
        if (room_entity.name.equals(room_name1)){
            entity.move_to_room(room_name2);
        }else{
            entity.move_to_room(room_name1);
        }
        if (entity instanceof Human p){
            p.send_message_to_player("You went into the door. Explore the new room!");
        }
    }

    @Override
    public void event_handler(Event e) {
        super.event_handler(e);
        if (e instanceof Entity_looks_at_object_event e2){
            if (e2.gameObject == this)
                e2.response = entity_looks_at_object_response();
        }
        if (e instanceof Entity_enter_door_event e2){
            if (e2.door == this){
                this.enter(e2.entity);
            }
        }

    }
}
