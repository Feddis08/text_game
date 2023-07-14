package at.feddis08.world.living;

import at.feddis08.Console;
import at.feddis08.InputParser;
import at.feddis08.Main;
import at.feddis08.modules.Item;
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
    public void draw_inv_screen(){

        Room r = Main.world.get_room_by_gameobject_id(id);
        Console.log("");
        Console.log("============================================================");
        Console.log("       ---Inventory---");
        Console.log("------------------------------------------------------------");
        Console.log("   -Size: " + this.inventory.size);
        Console.log("   -Stored: " + this.inventory.items.size());
        Console.log("------------------------------------------------------------");
        Console.log("       --Items--");
        int index = 0;
        for (Item i : this.inventory.items){
            Console.log(index + ": " + i.name);
        }
        Console.log("------------------------------------------------------------");
        Console.log("============================================================");
        Console.log("");
        Console.log("");
    }
    public void draw_info_screen(){

        Room r = Main.world.get_room_by_gameobject_id(id);
        Console.log("");
        Console.log("============================================================");
        Console.log("       ---Info---");
        Console.log("------------------------------------------------------------");
        Console.log("       --Me/" + name + "--");
        Console.log("------------------------------------------------------------");
        Console.log("   -Health:    " + health + "/" +max_health);
        Console.log("   -Stamina:    " + stamina + "/" + max_stamina);
        Console.log("   -Food:    " + food + "/" + max_food);
        Console.log("   -Power:    " + power);
        Console.log("------------------------------------------------------------");
        Console.log("       --Location--");
        Console.log("   -Room: " + r.name);
        Console.log("   " + r.description);
        Console.log("------------------------------------------------------------");
        Console.log("       ---Inventory---");
        Console.log("------------------------------------------------------------");
        Console.log("   -Stored: " + this.inventory.items.size());
        Console.log("------------------------------------------------------------");
        Console.log("       --Items--");
        if (inventory.items.size() >= 1)
            Console.log(0 + ": " + inventory.items.get(0).name);
        if (inventory.items.size() >= 2)
            Console.log(1 + ": " + inventory.items.get(1).name);
        if (inventory.items.size() >= 3)
            Console.log(2 + ": " + inventory.items.get(2).name);
        if (inventory.items.size() >= 4)
            Console.log(3 + ": " + inventory.items.get(3).name);
        if (inventory.items.size() >= 5)
            Console.log(4 + ": " + inventory.items.get(4).name);
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
