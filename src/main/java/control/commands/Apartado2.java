package control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import logic.Evaluador;
import objects.Mano;
import objects.Carta;
import java.io.IOException;
public class Apartado2 {

    private final String entrada;
    private final String salida;

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

            String card;
            int n;
            while ((card = in.readLine()) != null) {
                String carta[]=card.split(";");
                Mano m = new Mano();
                //carta propia
                for (int i = 0; i < carta[0].length(); i += 2) {
                    Carta c = new Carta(carta[0].substring(i, i + 1), carta[0].substring(i + 1, i + 2));
                    m.agregarCarta(c);
                }
                n=Integer.parseInt(carta[1]);
                //carta comun
               for (int i = 0; i < carta[2].length(); i += 2) {
                    Carta c = new Carta(carta[2].substring(i, i + 1), carta[2].substring(i + 1, i + 2));
                    m.agregarCarta(c);
                }
                this.ev = new Evaluador();
                ev.setMano(m);
                ev.evaluar();
                }
                FileWriter f_salida = new FileWriter(salida, true);
                try (//salida
                        BufferedWriter writer = new BufferedWriter(f_salida)) {
                        writer.append(carta[0]+";"+carta[1]+";"+carta[2]);
                        writer.newLine();
                        //mejor mano
                        writer.append("- Best Hand: ");
                        writer.append(m.getJugada().getDescripcion());
                        writer.newLine();
                        //si carta comun es 5 no hay draw
                        if(n!=5){
                            for (String s : m.getDraws()) {
                                writer.append("- " + s);
                                writer.newLine();
                            }
                        }
                        writer.newLine();
                        writer.close();
                    }
                }
            in.close();
            

        } catch (IOException e) {
            System.out.print(e);
        }
    }

}
