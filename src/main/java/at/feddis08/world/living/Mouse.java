package at.feddis08.world.living;

import at.feddis08.Console;
import at.feddis08.Main;
import at.feddis08.modules.events.*;
import at.feddis08.world.entities.Entity;

import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListSet;

public class Mouse extends Entity {

    public boolean in_input_event = false;

    public int normal_sound_max = 30;
    public int bite_delay_ticks = 160;
    public int bite_delay = 160;

    public Mouse(String name, int age) {
        super(name, age);
        this.health = 55;
        this.max_health = 55;
        this.stamina = 18;
        this.max_stamina = 18;
        this.food = 55;
        this.max_food = 55;
        this.entity_look_response = "A Mouse";
        this.entity_looks_at_response = "A Mouse that is ready to fight!";
        this.battle_actions.add("bite");
    }

    @Override
    public void tick() {
        super.tick();
        brain(new Event("Tick", this));
    }

    public void brain_make_sounds(){
        //making sound
        int min = 1;
        int max = this.normal_sound_max;
        if (max >= 100) this.normal_sound_max = 30;

        int value = (int) (Math.random() * (max - min)) + min;
        if (value == 1){
            this.normal_sound_max += 20;
            this.make_normal_sound();
        }
    }

    public void brain(Event e){
        if (e.entity != this){
            if (e instanceof Entity_look_event e2){
                make_normal_sound();
            }
            if (e instanceof Entity_looks_at_object_event e2){
                make_normal_sound();
            }
            brain_make_sounds();
        }else{
            if (Objects.equals(e.name, "Tick")){
                brain_battle();
            }else{
                if (e instanceof Entity_take_damage_event e2){
                    start_battle(e2.from);
                }
            }
        }
    }
    public boolean can_bite(){
        return (bite_delay == bite_delay_ticks);
    }
    public void brain_battle(){
        if (battle_mode){
            if (!(power == 0)){
                if (resting){
                    this.rest();
                }else{
                    if (stamina > 1 && can_bite()){
                       battle_action("bite");
                    }else{
                        battle_action("punch");
                    }
                }
            }else{
                this.rest();
            }
        }
    }
    public void make_attack_sound(){
        Entity_say_event e = new Entity_say_event(this, "MMMM.... IIZZ...");
        Main.world.get_room_by_gameobject_id(id).event_handler(e);
    }
    public void make_normal_sound(){
        Entity_say_event e = new Entity_say_event(this, "Miizz, Miz...");
        Main.world.get_room_by_gameobject_id(id).event_handler(e);
    }
    @Override
    public void event_handler(Event e) {
        super.event_handler(e);
        this.brain(e);
    }

    @Override
    public void take_damage(Entity e) {
        super.take_damage(e);
        brain(new Entity_take_damage_event(this, e, e.power));
    }

    public void bite(Entity e){
        if (bite_delay == bite_delay_ticks){
            stamina = stamina - (stamina * 8 / 100);
            this.add_food(food + (enemy.health / health));
            this.latest_attack = "bite";
            e.take_damage(this);
            bite_delay = 0;
        }
    }

    @Override
    public void start_battle(Entity e) {

        super.start_battle(e);
        this.entity_looks_at_response = "Mouse that is in a battle with " + enemy.name + "!";
    }

    @Override
    public void rearainge_stats() {
        super.rearainge_stats();
        if (bite_delay != bite_delay_ticks){
            bite_delay ++;
        }
    }

    public void battle_action(String action){
        super.battle_action(action);
        if (!resting){
            if (action.equals("bite")){
                this.bite(enemy);
            }
        }
    }
}
