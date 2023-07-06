package at.feddis08.world.living;

import at.feddis08.Console;
import at.feddis08.InputParser;
import at.feddis08.Main;
import at.feddis08.world.Room;

import java.io.IOException;
import java.util.Scanner;

public class MainHuman extends Human {
    public InputParser inputParser;

    public Scanner s;

    public MainHuman(String name, int age) {
        super(name, age);
        this.inputParser = new InputParser(this);
        s = new Scanner(System.in);
    }

    public void input_listener() {
        try {
            if (System.in.available() > 0) {
                String input = s.nextLine();
                inputParser.parse(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw_info_screen(){

        Room r = Main.world.get_room_by_gameobject_id(id);

        Console.log("");
        Console.log("============================================================");
        Console.log("       ---Info---");
        Console.log("");
        Console.log("------------------------------------------------------------");
        Console.log("       --Me/" + name + "--");
        Console.log("------------------------------------------------------------");
        Console.log("");
        Console.log("   -Health:    " + health + "/" +max_health);
        Console.log("   -Stamina:    " + stamina + "/" + max_stamina);
        Console.log("   -Food:    " + food + "/" + max_food);
        Console.log("   -Power:    " + power);
        Console.log("------------------------------------------------------------");
        Console.log("       --Location--");
        Console.log("   -Room: " + r.name);
        Console.log("   " + r.description);
        Console.log("------------------------------------------------------------");
        Console.log("============================================================");
        Console.log("");
        Console.log("");
    }

    public void draw_battle_screen(){
        clearScreen();
        Console.log("============================================================");
        Console.log("       ---Battle---");
        Console.log("");
        Console.log("");
        Console.log("");
        Console.log("       --Me/" + name + "--");
        Console.log("");
        Console.log("   -Health:    " + health + "/" +max_health);
        Console.log("   -Stamina:    " + stamina + "/" + max_stamina);
        Console.log("   -Food:    " + food + "/" + max_food);
        Console.log("   -Power:    " + power);
        Console.log("");
        Console.log("       --Enemy/" + enemy.name + "--");
        Console.log("");
        Console.log("   -Health:    " + enemy.health + "/" +enemy.max_health);
        Console.log("   -Stamina:    " + enemy.stamina + "/" +enemy.max_stamina);
        Console.log("   -Food:    " + enemy.food + "/" + enemy.max_food);
        Console.log("   -Power:    " + enemy.power);
        Console.log("");
        Console.log("");
        Console.log("       --Fight--");
        Console.log("");
        Console.log("   -My action: " + latest_attack + " | -" + enemy.damage_taken);
        Console.log("   -Enemy action: " + enemy.latest_attack + " | -" + damage_taken);
        Console.log("");
        Console.log("");
        Console.log("       --Action--");
        Console.log("------------------------------------------------------------");
        int i = 0;
        for (String s : battle_actions){
            if (s.equals("punch")){
                Console.log("   -" + (i+1) + ": " + s + " -" + punch_delayed + "/" + punch_delay_ticks);
            }else{
                Console.log("   -" + (i+1) + ": " + s);
            }
            i++;
        }
        Console.log("------------------------------------------------------------");
        Console.log("============================================================");
    }

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }




    @Override
    public void battle_update() {
        super.battle_update();
        if (battle_mode){
            draw_battle_screen();
        }

    }


    @Override
    public void tick() {
        super.tick();
        input_listener();

    }
}
