package at.feddis08;

import at.feddis08.console.ConsoleColors;

public class Console {

    public static void log(String message){
        System.out.println(ConsoleColors.RESET + message);
    }
    public static void error(String message){
        System.out.println(ConsoleColors.RED + message);
    }
    public static void warning(String message){
        System.out.println(ConsoleColors.YELLOW + message);
    }
}
