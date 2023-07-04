package at.feddis08.world.entities;

import at.feddis08.Console;
import at.feddis08.Main;
import at.feddis08.modules.GameObject;
import at.feddis08.modules.Inventory;
import at.feddis08.modules.events.Entity_look_event;
import at.feddis08.modules.events.Entity_looks_at_object_event;
import at.feddis08.modules.events.Entity_move_event;
import at.feddis08.modules.events.Event;
import at.feddis08.world.Room;
import at.feddis08.world.living.Player;

import java.util.ArrayList;

public class Entity extends GameObject {
    public String name;
    public int level;
    public int age;
    public Inventory inventory;
    public int health;
    public int max_health;
    public int stamina;
    public int max_stamina;
    public int food;
    public int max_food;
    public boolean battle_mode;
    public Entity enemy;
    public String latest_attack;
    public String enemy_latest_attack;
    public int damage_taken;
    public int power;
    public ArrayList<String> battle_actions;
    public boolean dead;
    public boolean win;
    public String entity_looks_at_response;
    public String entity_look_response;
    public int punch_delay_ticks;
    public int punch_delayed;
    public boolean resting = false;
    public Entity(String name, int age){
        super();
        this.name = name;
        this.age = age;
        this.level = 0;
        this.inventory = new Inventory(16);
        this.health = 100;
        this.max_health = 100;
        this.max_stamina = 100;
        this.stamina = 100;
        this.food = 100;
        this.max_food = 100;
        this.battle_mode = false;
        this.latest_attack = "";
        this.enemy_latest_attack = "";
        this.damage_taken = 0;
        this.power = 100;
        this.battle_actions = new ArrayList<>();
        this.battle_actions.add("punch");
        this.battle_actions.add("rest");
        this.dead = false;
        this.win = false;
        this.entity_looks_at_response = "a thing called " + name;
        this.entity_look_response = "a being";
        this.punch_delay_ticks = 10;
        this.punch_delayed = 10;
        this.resting = false;
    }


    public void age(){
        this.age++;
    }
    public void regenerate(){
        if (this.health != max_health){
            if (!battle_mode || resting){
                if (stamina > 0){
                    stamina -= 1;
                    health += 1;
                }
            }
        }
        if (stamina != max_stamina){
            if (!battle_mode || resting) {
                if (food > 0) {
                    food -= 1;
                    stamina += 1;
                }
            }
        }
        if (food == 0){
                health -= 0.5;
        }
        if (health <= 0){
            die();
        }
    }
    public void die(){
        this.dead = true;
        leave_room = true;
        battle_update();
    }
    public void rearainge_stats(){
        power = (stamina * 2) * health / 1000;
        if (punch_delayed != punch_delay_ticks){
            punch_delayed++;
        }
    }

    public void tick(){
        if (!dead){
            this.age();
            regenerate();
            rearainge_stats();

            if (battle_mode){
                if (enemy.dead){
                    this.win = true;
                    handle_win();
                }
            }

        }
    }

    public void move_to_room(String room_name) {
        if (!(resting)){
            Room r = Main.world.get_room_by_gameobject_id(this.id);
            Room r2 = Main.world.get_room_by_name(room_name);
            Entity_move_event e = new Entity_move_event(this, r, r2);
            Entity_move_event e3 = new Entity_move_event(this, r2, r);
            Event e2 = r.event_handler(e);
            Event e4 = r2.event_handler(e3);
            if (!e2.canceled && !e4.canceled){
                this.leave_room = true;
                r2.add_object(this);
            }
        }else{
            if (this instanceof Player p){
                p.send_message_to_player("I first have to stop resting.");
            }
        }
    }
    public String get_look_response(){
        return this.entity_look_response;
    }
    public String get_own_look_response(){
        String str = "myself";
        return str;
    }
    public String entity_looks_at_object_response(){
        return this.entity_looks_at_response;
    }
    public void event_handler(Event e){
        if (!dead){
            if (e instanceof Entity_look_event e2){
                if (e.entity == this){
                    e2.response.add(get_own_look_response());
                }else{
                    e2.response.add(get_look_response());
                }
            }
            if (e instanceof Entity_looks_at_object_event e2){
                if (e2.gameObject == this)
                    e2.response = entity_looks_at_object_response();
            }
        }
    }
    public void start_battle(Entity e){
        this.battle_mode = true;
        this.enemy = e;
        battle_update();
    }

    public void handle_win(){
        battle_update();
        win = false;
        battle_mode = false;
        enemy = null;
    }
    public void punch(Entity e){
        if (punch_delayed == punch_delay_ticks){
            punch_delayed = punch_delay_ticks/2;
            this.stamina = stamina - power/20;
            this.latest_attack = "punch";
            e.take_damage(this);
        }
    }
    public void take_damage(Entity e){
        if (e.latest_attack.equals("punch")){
            health = health - e.power;
            damage_taken = e.power;
            battle_update();
        }
    }
    public void battle_update(){

    }
    public void rest(){
        this.resting = !resting;
        this.latest_attack = "rest";
    }
    public void battle_action(String action){
        if (!resting){
            if (action.equals("punch")){
                this.punch(enemy);
            }
            if(action.equals("rest")){
                rest();
            }
            this.battle_update();
        }else if(action.equals("rest")){
            rest();
            this.battle_update();
        }else if (this instanceof Player p){
            p.send_message_to_player("Can't attack while resting!");
        }
    }
}
