package at.feddis08;

import at.feddis08.console.ConsoleColors;
import at.feddis08.modules.events.*;
import at.feddis08.world.entities.Door;
import at.feddis08.world.entities.Entity;
import at.feddis08.world.living.MainHuman;

public class InputParser {
    public MainHuman mainPlayer;
    public InputParser(MainHuman mainPlayer){
        this.mainPlayer = mainPlayer;
    }
    public void parse(String input){
        String[] words = input.split("\\s+");
        if (words[0].equals("look") || words[0].equals("l")){

            if (words.length == 1){
                Entity_look_event e = new Entity_look_event(mainPlayer, Main.world.get_room_by_gameobject_id(mainPlayer.id));
                Entity_look_event e2 = (Entity_look_event) Main.world.get_room_by_gameobject_id(mainPlayer.id).event_handler(e);
                if (!e2.canceled){
                    Console.log("You look around...");
                    Console.log("");
                    Console.log("   -" + Main.world.get_room_by_gameobject_id(mainPlayer.id).description);
                    Console.log("");
                    Console.log("You also found some other stuff...");
                    Console.log("");
                    for (String s : e2.response){
                        Console.log("   -" + s);
                    }
                }
            } else if (words.length == 3) {
                Entity_looks_at_object_event e;
                if (words[2].equals("myself")){
                     e = new Entity_looks_at_object_event(mainPlayer, mainPlayer);
                }else{
                    e = new Entity_looks_at_object_event(mainPlayer, Main.world.get_room_by_gameobject_id(mainPlayer.id).get_entity_by_name(words[2]));
                }
                Entity_looks_at_object_event e2 = (Entity_looks_at_object_event) Main.world.get_room_by_gameobject_id(mainPlayer.id).event_handler(e);
                if (!e2.canceled){
                    Console.log("You took a closer look on " + words[2]);
                    if (e2.response == null){
                        Console.log("Unfortunately you couldn't figure out what you are looking at.");
                    }else{
                        Console.log(ConsoleColors.YELLOW_UNDERLINED  + e2.response);
                    }
                }
            }
        }
        if (words[0].equals("rest")){
            if (words.length == 1){
                if (mainPlayer.resting){
                    Console.log("You quit resting.");
                }else{
                    Console.log("You get some rest.");
                }
                mainPlayer.rest();
            }
        }
        if (words[0].equals("info")){
            if (words.length == 1){
                mainPlayer.draw_info_screen();
            }
        }
        if (words[0].equals("battle")){
            if (words.length == 2){
                Entity entity = Main.world.get_room_by_gameobject_id(mainPlayer.id).get_entity_by_name(words[1]);
                mainPlayer.start_battle(entity);
                mainPlayer.draw_battle_screen();
            }
        }
        if (words[0].equals("enter") || words[0].equals("go")){
            if (words.length == 2){
                Entity_enter_door_event e = new Entity_enter_door_event(mainPlayer, (Door) Main.world.get_room_by_gameobject_id(mainPlayer.id).get_entity_by_name(words[1]));
                Entity_enter_door_event e2 = (Entity_enter_door_event) Main.world.get_room_by_gameobject_id(mainPlayer.id).event_handler(e);
                if (!e2.canceled){
                    Console.log("... ");
                }
            }
        }
        if (mainPlayer.battle_mode){
            if (words.length == 1 && !words[0].equals("")){
                mainPlayer.battle_action(mainPlayer.battle_actions.get(Integer.parseInt(words[0]) - 1));
            }
        }
        Console.log(">");
    }

}
