package at.feddis08.world;

import at.feddis08.modules.GameObject;
import at.feddis08.world.entities.Door;
import at.feddis08.world.entities.Entity;
import at.feddis08.world.living.Mouse;
import at.feddis08.world.living.Human;

import java.util.ArrayList;

public class World {
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();

    public Human add_player(String name) {
        Human human = new Human(name, 1);
        this.entities.add(human);
        return human;
    }

    public void tick() {
        for (Room r : this.rooms) {
            r.tick();
        }
    }

    public void create() {
        //
        Door door = new Door("F-C_gate", "A connection between the small path and the cave entry", "Forest Path", "Cave entry");

        Room room2 = new Room("Forest Path", "A small path that goes deep into the Forest. There is only one way to go.");
        room2.add_object(door);
        this.rooms.add(room2);

        Door door2 = new Door("C-P_gate", "A connection between the cave entry and the path end.", "Cave entry", "Path end");
        Door door3 = new Door("C-C_gate", "A connection between the cave entry and the cave.", "Cave entry", "Cave");

        Room spawn = new Room("Cave entry", "The small path splits in two. I can go into a cave or remain on track.");
        spawn.add_object(door2);
        spawn.add_object(door3);
        this.rooms.add(spawn);
        //

        Room r3 = new Room("Path end", "There ends the small Path. I can go back.");
        r3.add_object(door2);
        Mouse m = new Mouse("Rat", 0);
        r3.add_object(m);
        this.rooms.add(r3);

        Room r4 = new Room("Cave", "Pass the entry of the forest cave. It's really dark and it's hard to see anything. The cave looks deep. Also you can hear something.");
        r4.add_object(door3);
        Mouse m2 = new Mouse("Rat", 0);
        r4.add_object(m);
        this.rooms.add(r4);


    }

    public Room get_room_by_name(String name) {
        Room room = null;
        for (Room r : this.rooms) {
            if (r.name.equals(name))
                room = r;
        }
        return room;
    }

    public Room get_room_by_gameobject_id(String id) {
        Room room = null;
        for (Room r : this.rooms) {
            for (GameObject g : r.gameObjects) {
                if (g.id.equals(id)) {
                    room = r;
                    break;
                }
            }
        }
        return room;
    }
    public Room get_room_by_entity_name(String name){
        Room room = null;
        for (Room r : this.rooms){
            for (GameObject g : r.gameObjects){
                if (g instanceof Entity e){
                    if (e.name.equals(name)){
                        room = r;
                    }
                }
            }
        }
        return room;
    }

}
