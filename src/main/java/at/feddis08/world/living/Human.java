package at.feddis08.world.living;

import at.feddis08.Console;
import at.feddis08.modules.events.Entity_looks_at_object_event;
import at.feddis08.modules.events.Entity_say_event;
import at.feddis08.modules.events.Event;
import at.feddis08.world.entities.Entity;

public class Human extends Entity {

    public boolean in_input_event = false;

    public Human(String name, int age) {
        super(name, age);

        this.health = 75;
        this.max_health = 75;
        this.stamina = 33;
        this.max_stamina = 33;
        this.food = 110;
        this.max_food = 110;
        this.entity_look_response = "A Player";
        this.entity_looks_at_response = "Normal person called " + name;

    }


    @Override
    public void handle_win() {
        send_message_to_player("You won! " + enemy.name + " has been defeated!");
        super.handle_win();
    }

    public void send_message_to_player(String message){
        if (this instanceof MainHuman p){
            Console.log(message);
        }
    }
    @Override
    public void event_handler(Event e) {
        super.event_handler(e);
        if (e instanceof Entity_say_event e2){
            if (!battle_mode)
                this.send_message_to_player(e2.message);
        }
    }

}
