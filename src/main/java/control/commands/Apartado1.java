package control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import objects.Mano;
import objects.Carta;

public class Apartado1 {

    private final String entrada;
    private final String salida;
    private Mano mano;

    public Apartado1(String entra, String sal) {
        this.entrada = entra;
        this.salida = sal;

    }

    public void execute() {

        try {
            File f = new File(entrada);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader fr = new InputStreamReader(fis);
            BufferedReader in = new BufferedReader(fr);
            FileWriter f_salida=new FileWriter(salida,true);
            String carta;
            while ((carta = in.readLine()) != null) {

                Mano m = new Mano();
                for (int i = 0; i < carta.length(); i += 2) {
                    // no se si esta bien
                    Carta c = new Carta(carta.substring(i, i + 1), carta.substring(i + 1, i + 2));
                    m.agregarCarta(c);
                }
                //salida
                //salida
            	
                BufferedWriter writer = new BufferedWriter(f_salida);
                   writer.newLine();
                writer.append("Best Hand: ");
                    //solucion
                   writer.append(m.getStrCartas());
                   writer.newLine();
                    //if es Straight Gutshot
                   writer.append("Draw: ");
                   writer.append("Straight Gutshot");
                   //else if es gutshot
                   writer.append("Draw: ");
                   writer.append("Gutshot");
                   //if es Flush
                   writer.append("Draw:");
                   writer.append("Flush");
                   
                   writer.close();
            }
                } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
