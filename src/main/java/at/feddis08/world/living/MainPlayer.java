package at.feddis08.world.entities;

import at.feddis08.InputParser;
import at.feddis08.world.living.Player;

import java.io.IOException;
import java.util.Scanner;

public class MainPlayer extends Player {
    public InputParser inputParser;

    public Scanner s;

    public MainPlayer(String name, int age) {
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


    @Override
    public void tick() {
        super.tick();
        input_listener();
    }
}
