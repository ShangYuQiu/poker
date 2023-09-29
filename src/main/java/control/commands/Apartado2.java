package control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
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

            String carta[];
            int n;
            while ((carta = in.readLine().split(";")) != null) {

                Mano m = new Mano();
                for (int i = 0; i < carta[0].length(); i += 2) {
                    // no se si esta bien
                    Carta c = new Carta(carta[0].substring(i, i + 1), carta[0].substring(i + 1, i + 2));
                    m.agregarCarta(c);
                }
                n=Integer.parseInt(carta[1]);
               
                    for (int i = 0; i < carta[2].length(); i += 2) {
                    Carta c = new Carta(carta[0].substring(i, i + 1), carta[0].substring(i + 1, i + 2));
                    m.agregarCarta(c);
                    }
                }
                //salida=sol
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
