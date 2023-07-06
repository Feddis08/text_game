package at.feddis08;

import at.feddis08.console.ConsoleColors;
import at.feddis08.world.World;
import at.feddis08.world.living.MainHuman;

import java.util.Scanner;

public class Main {

    public static boolean load_from_save = false;
    public static World world = new World();

    public static MainHuman player;


    public static void main(String[] args) {

        if (!load_from_save){
            world.create();
            System.out.println(ConsoleColors.GREEN_BRIGHT + "Hello, What's your name?");
            Scanner s = new Scanner(System.in);
            String name = s.next();
            System.out.println(ConsoleColors.GREEN_BOLD + "Nice to meet you " + ConsoleColors.GREEN_BOLD_BRIGHT + name + ConsoleColors.GREEN_BOLD + "!");


            Gameloop.start();
            player = add_main_player(name);
            //player.move_to_room("Spawn");




        }

    }
    public static MainHuman add_main_player(String name) {
        MainHuman player = new MainHuman(name, 1);
        world.entities.add(player);
        world.rooms.get(2).add_object(player);
        return player;
    }


}