package at.feddis08.world;

import at.feddis08.Console;
import at.feddis08.Gameloop;
import at.feddis08.modules.GameObject;
import at.feddis08.modules.events.Event;
import at.feddis08.world.entities.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Room {
    public String id;
    public String name;
    public String description;
    public ArrayList<GameObject> gameObjects = new ArrayList();
    public ArrayList<GameObject> adding_gameObjects = new ArrayList();
    public Room(String name, String description){
        this.name = name;
        this.description = description;
        this.id = String.valueOf ((int) System.currentTimeMillis() * Math.random());
    }


    public void tick() {
        Iterator<GameObject> iterator = gameObjects.iterator();
        List<GameObject> objectsToRemove = new ArrayList<>();

        while (iterator.hasNext()) {
            GameObject g = iterator.next();
            if (g.leave_room) {
                g.leave_room = false;
                objectsToRemove.add(g);
            } else if (g instanceof Entity e) {
                e.tick();
            }
        }

        // Remove the objects marked for removal
        for (GameObject objectToRemove : objectsToRemove) {
            gameObjects.remove(objectToRemove);
        }

        if (adding_gameObjects.size() > 0) {
            gameObjects.addAll(adding_gameObjects);
            adding_gameObjects.clear();
        }
    }



    public Event event_handler(Event e) {
        Iterator<GameObject> iterator = gameObjects.iterator();

        while (iterator.hasNext()) {
            GameObject g = iterator.next();
            if (g instanceof Entity en) {
                en.event_handler(e);
            }
        }
        return e;
    }


    public void add_object(GameObject g){
        adding_gameObjects.add(g);
    }
    public Entity get_entity_by_name(String name) {
        Entity e = null;
        for (GameObject g : this.gameObjects) {
            if (g instanceof Entity e2){
                if (e2.name.equals(name))
                    e = e2;
                }
        }
        return e;
    }
}
