package control;

import control.commands.Apartado1;
import control.commands.Apartado2;
import control.commands.Apartado3;
import control.commands.Apartado4;
public class Controller {

    private final String entrada;
    private final String salida;

    public Controller(String entrada, String salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    public void run(int nivel) {

        switch (nivel) {
            case 1 -> {
                Apartado1 apartado1 = new Apartado1(entrada, salida);
                apartado1.execute();
            }
            case 2 -> {
                Apartado2 apartado2 = new Apartado2(entrada, salida);
                apartado2.execute();
            }
            case 3 -> {
                Apartado3 apartado3 = new Apartado3(entrada, salida);
                apartado3.execute();
            }
            case 4 -> {
                Apartado4 apartado4 = new Apartado4(entrada, salida);
                apartado4.execute();
            }
            default -> {
                System.out.println("Primer argumento introducido debe ser entre 1 y 4.");
            }
        }
    }
}
