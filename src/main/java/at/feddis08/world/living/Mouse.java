package at.feddis08.world.living;

import at.feddis08.Console;
import at.feddis08.Main;
import at.feddis08.modules.events.Entity_enter_door_event;
import at.feddis08.modules.events.Entity_looks_at_object_event;
import at.feddis08.modules.events.Event;
import at.feddis08.world.entities.Entity;
import at.feddis08.world.entities.MainPlayer;

public class Player extends Entity {

    public boolean in_input_event = false;

    public Player(String name, int age) {
        super(name, age);
    }

    @Override
    public void tick() {
        super.tick();
    }
    public void send_message_to_player(String message){
        if (this instanceof MainPlayer p){
            Console.log(message);
        }
    }
    public String entity_looks_at_object_response(){
        String str = "A person named " + name;
        return str;
    }
    @Override
    public void event_handler(Event e) {
        super.event_handler(e);
        if (e instanceof Entity_looks_at_object_event e2){
            if (e2.gameObject == this)
                e2.response = entity_looks_at_object_response();
        }
    }
}
