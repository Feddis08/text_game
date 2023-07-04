package at.feddis08;

import at.feddis08.console.ConsoleColors;
import at.feddis08.world.World;
import at.feddis08.world.living.MainPlayer;

import java.util.Scanner;

public class Main {

    public static boolean load_from_save = false;
    public static World world = new World();

    public static MainPlayer player;


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
    public static MainPlayer add_main_player(String name) {
        MainPlayer player = new MainPlayer(name, 1);
        world.entities.add(player);
        world.rooms.get(0).add_object(player);
        return player;
    }


}