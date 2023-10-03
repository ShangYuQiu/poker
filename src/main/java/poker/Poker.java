package poker;

import control.Controller;

public class Poker {

    public static void main(String[] args) {

        Controller controller = new Controller(args[1], args[2]);

        switch (args[0]) {
            case "1" -> controller.run(1);
            case "2" -> controller.run(2);
            case "3" -> controller.run(3);
            case "4" -> controller.run(4);
            default -> {
                System.out.println("Primer argumento introducido debe ser entre 1 y 3.");
            }
        }
    }
}
