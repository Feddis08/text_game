package at.feddis08.modules;

import at.feddis08.Main;
import at.feddis08.modules.events.Entity_move_event;
import at.feddis08.world.Room;

public class GameObject {
    public String id;
    public int time_created;
    public boolean leave_room = false;

    public GameObject(){
        this.id = String.valueOf ((int) System.currentTimeMillis() * Math.random());
        this.time_created = (int) System.currentTimeMillis();
    }


}
