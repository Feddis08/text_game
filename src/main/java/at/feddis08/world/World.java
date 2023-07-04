package at.feddis08.world;

import at.feddis08.modules.GameObject;
import at.feddis08.world.entities.Door;
import at.feddis08.world.entities.Entity;
import at.feddis08.world.living.Mouse;
import at.feddis08.world.living.Player;

import java.util.ArrayList;

public class World {
    public ArrayList<Room> rooms = new ArrayList<>();
    public ArrayList<Entity> entities = new ArrayList<>();

    public Player add_player(String name) {
        Player player = new Player(name, 1);
        this.entities.add(player);
        return player;
    }

    public void tick() {
        for (Room r : this.rooms) {
            r.tick();
        }
    }

    public void create() {
        Door door = new Door("door_name", "A small wooden door that connects Room2 and the Spawn", "Room_name", "Spawn");

        Room room2 = new Room("Room_name", "room_description");
        room2.add_object(door);
        this.rooms.add(room2);

        Room spawn = new Room("Spawn", "the spawn room");
        spawn.add_object(door);
        this.rooms.add(spawn);


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
