package at.feddis08.modules;

public class Item {
    public int time_created;
    public String id;
    public String name;

    public Item (String name){
        this.id = String.valueOf ((int) System.currentTimeMillis() * Math.random());
        this.time_created = (int) System.currentTimeMillis();
        this.id = id;
        this.name = name;
    }

}
