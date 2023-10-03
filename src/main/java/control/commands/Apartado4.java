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
import java.util.ArrayList;
public class Apartado4 {

    private final String entrada;
    private final String salida;
    private Evaluador ev;
    private ArrayList<Jugada> listaCarta;
    private final String tipo[]={"CARTA_ALTA", "PAREJA", "DOBLE_PAREJA", "TRIO", "ESCALERA", "COLOR", "FULL_HOUSE", "POKER", "ESCALERA_COLOR"};
    private final String palo[]={"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
    public Apartado4(String entra, String sal) {
        this.entrada = entra;
        this.salida = sal;
        this.listaCarta=new ArrayList<Jugada>();
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
                for(int j=0;j<4*2;j+=4){
                    int cont=0;
                    Mano m = new Mano();
                    //carta propia de cada jugador
                    for (int i = j; i < carta[0].length(); i += 2) {
                        Carta c = new Carta(carta[0].substring(i, i + 1), carta[0].substring(i + 1, i + 2));
                        m.agregarCarta(c);
                        cont++;
                        
                    }
                    n=Integer.parseInt(carta[1]);
                    //carta comun
                    for (int i = 0; i < carta[2].length()&&cont<5; i += 2) {
                    Carta c = new Carta(carta[2].substring(i, i + 1), carta[2].substring(i + 1, i + 2));
                    m.agregarCarta(c);
                    cont++;
                    }
                    this.ev = new Evaluador();
                    ev.setMano(m);
                    ev.evaluar();
                    listaCarta.add(m.getJugada());
                }
                //salida
                FileWriter f_salida = new FileWriter(salida, true);
                try (BufferedWriter writer = new BufferedWriter(f_salida)) {
                    //salida por orden de jugador con las cartas de mayor a menor
                    writer.append(card);
                    writer.newLine();
                    writer.append("-Best hand: ");
                    boolean continuar=true;
                    for(int i=tipo.length-1;i>=0&&continuar;i--){
                        for(int m=0;m<palo.length&&continuar;m++){
                            for(int k=0;k<listaCarta.size()&&continuar;k++){
                                if((listaCarta.get(k).getCadCartas().substring(0, 1).equalsIgnoreCase(palo[m]))&&
                                        (listaCarta.get(k).getJugada().toString().equalsIgnoreCase(tipo[i]))&&continuar){
                                    writer.append(listaCarta.get(k).getDescripcion());
                                    writer.newLine();
                                    continuar=false;
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
