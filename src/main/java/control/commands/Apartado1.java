package control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import objects.Mano;
import objects.Carta;
import logic.Evaluador;
import java.io.IOException;

public class Apartado1 {

    private final String entrada;
    private final String salida;
    private Mano mano;
    private Evaluador ev;

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
            	this.ev = new Evaluador();
               ev.setMano(m);
                ev.evaluar();

                try ( //salida
                        //salida
                        BufferedWriter writer = new BufferedWriter(f_salida)) {
                    writer.append("- Best Hand: ");
                    //solucion
                    writer.append(m.getJugada().getDescripcion());
                    writer.newLine();
                    //if es Straight Gutshot
                    for (String s : m.getDraws()) {
                        writer.append("- " + s);
                        writer.newLine();
                    }
                    
                    writer.close();
                }
            }
                } catch (IOException e) {
            System.out.print(e);
        }
    }
}
