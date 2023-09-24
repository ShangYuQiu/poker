package control;

import control.commands.Apartado1;

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
            }
            case 3 -> {
            }
            default -> {
            }
        }
    }
}
