package control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import objects.Mano;
import objects.Carta;

public class Apartado2 {

    private String entrada;
    private Mano mano;
    private String salida;

    public Apartado2(String entra, String sal) {
        this.entrada = entra;
        this.salida = sal;

    }

    public void execute() {

        try {
            File f = new File(entrada);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader fr = new InputStreamReader(fis);
            BufferedReader in = new BufferedReader(fr);

            String carta;
            while ((carta = in.readLine()) != null) {

                Mano m = new Mano();
                for (int i = 0; i < carta.length(); i += 2) {
                    // no se si esta bien
                    Carta c = new Carta(carta.substring(i, i + 1), carta.substring(i + 1, i + 2));
                    m.anniadirCart(c);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
