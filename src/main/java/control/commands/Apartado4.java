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
    private final String simb[]={"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
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
            int n;
            String card;
            while ((card = in.readLine()) != null) {
                String carta[]=card.split(";");
                n=Integer.parseInt(carta[1]);
                    int s=0;
                    int j=2;
                    
                    while(s<=4){
                        int a=0;
                        int b=2;
                        int d=4;
                        boolean continu=true;//si num cartas comunes es mayor que 3 continu=true 
                        while(a<((n*2)-(2*2))&&continu){
                            Mano m = new Mano();
                            //dos cartas propias
                            Carta c = new Carta(carta[0].substring(s, s + 1), carta[0].substring(s + 1, s + 2));
                            m.agregarCarta(c);
                            c = new Carta(carta[0].substring(j, j + 1), carta[0].substring(j + 1, j + 2));
                            m.agregarCarta(c);
                            if(n==3){//si solo hay tres cartas comunes
                                continu=false;//num cartas comunes=3
                                for (int k = 0; k < carta[2].length(); k += 2) {
                                    c = new Carta(carta[2].substring(k, k + 1), carta[2].substring(k + 1, k + 2));
                                    m.agregarCarta(c);
                                }
                            }else{
                                //tres cartas comunes
                                c = new Carta(carta[2].substring(a, a + 1), carta[2].substring(a + 1, a + 2));
                                m.agregarCarta(c);
                                c = new Carta(carta[2].substring(b, b + 1), carta[2].substring(b + 1, b + 2));
                                m.agregarCarta(c);
                                c = new Carta(carta[2].substring(d, d + 1), carta[2].substring(d + 1, d + 2));
                                m.agregarCarta(c);
                                
                                d+=2;
                                if(d>=n*2){//si es la ultima carta
                                    b+=2;//pasa a siguente carta
                                    d=b+2;
                                    if(b>=(n*2)-2){//si es la penultima carta
                                    a+=2;//pasa a siguente carta
                                    b=a+2;
                                    d=b+2;
                                    }
                                }
                                
                            }
                            this.ev = new Evaluador();
                            ev.setMano(m);
                            ev.evaluar();
                            listaCarta.add(m.getJugada());
                            
                        }
                        j+=2;
                        if(j>=8){
                            s+=2;
                            j=s+2;
                        }
                    }
                //salida
                FileWriter f_salida = new FileWriter(salida, true);
                try (BufferedWriter writer = new BufferedWriter(f_salida)) {
                    //salida mayor carta
                    writer.append(card);
                    writer.newLine();
                    writer.append("-Best hand: ");
                    boolean continuar=true;
                    for(int i=tipo.length-1;i>=0&&continuar;i--){
                        for(int m=0;m<simb.length&&continuar;m++){
                            for(int k=0;k<listaCarta.size()&&continuar;k++){
                                if((listaCarta.get(k).getCadCartas().substring(0, 1).equalsIgnoreCase(simb[m]))&&
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
