package at.feddis08;

public class Gameloop {
    public static Thread t;
    public static int tick_count = 0;

    public static void start(){
        t = new Thread(() -> {
            while(true){
                //Console.log(tick_count + " aa");
                loop();
                tick_count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t.start();
    }

    public static void loop(){

        Main.world.tick();

    }



}
