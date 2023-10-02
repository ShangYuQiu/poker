package control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import logic.Evaluador;
import objects.Mano;
import objects.Jugada;
import objects.Carta;
import java.io.IOException;
import java.util.HashMap;
public class Apartado3 {

    private final String entrada;
    private final String salida;
    private Evaluador ev;
    private HashMap<String, Jugada> jugador;
    private final String tipo[]={"CARTA_ALTA", "PAREJA", "DOBLE_PAREJA", "TRIO", "ESCALERA", "COLOR", "FULL_HOUSE", "POKER", "ESCALERA_COLOR"};
    private final String palo[]={"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
    public Apartado3(String entra, String sal) {
        this.entrada = entra;
        this.salida = sal;
        this.jugador=new HashMap<String, Jugada>();
    }

    public void execute() {

        try {
            File f = new File(entrada);
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader fr = new InputStreamReader(fis);
            BufferedReader in = new BufferedReader(fr);
            
            String card;
            int n;
            int j;
            
            while ((card = in.readLine()) != null) {
                String carta[]=card.split(";");
                j=Integer.parseInt(carta[0]);//numero del jugador
                for(int k=0;k<j;k++){
                    Mano m = new Mano();
                    //carta propia de cada jugador
                    for (int i = 2; i < carta[k+1].length(); i += 2) {
                        Carta c = new Carta(carta[k+1].substring(i, i + 1), carta[k+1].substring(i + 1, i + 2));
                        m.agregarCarta(c);
                    }
                    //carta comun
                    for (int i = 0; i < carta[j+1].length(); i += 2) {
                    Carta c = new Carta(carta[j+1].substring(i, i + 1), carta[j+1].substring(i + 1, i + 2));
                    m.agregarCarta(c);
                    }
                    this.ev = new Evaluador();
                    ev.setMano(m);
                    ev.evaluar();
                    jugador.put(carta[k+1].substring(0,2), m.getJugada());

                } 
                //salida
                FileWriter f_salida = new FileWriter(salida, true);
                try (BufferedWriter writer = new BufferedWriter(f_salida)) {
                    //salida por orden de jugador con las cartas de mayor a menor
                    for(int i=tipo.length-1;i>=0;i--){
                        for(int m=0;m<palo.length;m++){
                            for(String k: jugador.keySet()){
                                if((jugador.get(k).getCadCartas().substring(0, 1).equalsIgnoreCase(palo[m]))&&
                                        (jugador.get(k).getJugada().toString().equalsIgnoreCase(tipo[i]))){
                                    writer.append(k+": "+jugador.get(k).getDescripcion());
                                    writer.newLine();
                                }
                            }
                        }
                    }
                  writer.close();
                }
            }
            in.close();

        } catch (IOException e) {
            System.out.print(e);
        }
    }

}
