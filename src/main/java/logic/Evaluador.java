package logic;

import java.util.ArrayList;
import java.util.List;
import objects.Carta;
import objects.Jugada;
import objects.Mano;

public class Evaluador {

    //Mensajes que se imprimira para las distintas jugadas
    private static final List<String> msg = List.of("Twos", "Threes", "Fours",
            "Fives", "Sixes", "Sevens", "Eights", "Nines",
            "Tens", "Jacks", "Queens", "Kings", "Ases");

    private Mano mano;

    public Evaluador() {
        this.mano = null;
    }

    //Metodo que busca la mejor jugada de las cartas inciales, y busca posibles jugadas(DRAW)
    public void evaluar() {

        List<Carta> c = mano.getCartas();
        Jugada j;

        // pasar la jugada que ha evaluado a mano correspondiente
        if ((j = EscaleraColor(c)) != null) {
            mano.setJugada(j);
            Flush(c);
        } else if ((j = Poker(c)) != null) {
            mano.setJugada(j);
        } else if ((j = FullHouse(c)) != null) {
            mano.setJugada(j);
        } else if ((j = Flush(c)) != null) {
            mano.setJugada(j);
        } else if ((j = Escalera(c)) != null) {
            mano.setJugada(j);
        } else if ((j = Trio(c)) != null) {
            mano.setJugada(j);
        } else if ((j = DoblePareja(c)) != null) {
            mano.setJugada(j);
        } else if ((j = Pareja(c)) != null) {
            mano.setJugada(j);
        } else {
            String msgJugada = String.format("High Card with %s", this.mano.getStrCartas());
            j = new Jugada(c, tJugada.CARTA_ALTA, msgJugada);
            mano.setJugada(j);
        }

    }

    /*-------------------------------------METODOS PRIVADOS-------------------------------------------*/
    //Comprobar que todas las cartas son del mismo palo 
    private boolean esMismoPalo(List<Carta> c) {
        boolean mismoPalo = true;
        int i = 0;

        while (i < c.size() - 1 && mismoPalo) {
            if (!c.get(i).getPalo().equals(c.get(i + 1).getPalo())) {
                mismoPalo = false;
            }
            i++;
        }
        return mismoPalo;
    }

    //Comprobar que todas las cartas son del mismo valor independientemente del palo
    private boolean esMismoValor(List<Carta> c) {
        boolean mismoValor = true;
        int i = 1;
        int primValor = c.get(0).getVal();
        while (i < c.size() && mismoValor) {
            if (primValor != c.get(i).getVal()) {
                mismoValor = false;
            }
            i++;
        }
        return mismoValor;
    }

    //Añade un posible draw a la mano
    private void addDraw(String msg) {
        this.mano.addDraw(msg);
    }

    //Devuelve la mano en forma de string
    private String getStrCartas() {
        return this.mano.getStrCartas();
    }

    /*--------------------------------------------------------------------------------------------------*/
 /*-- METODOS PARA COMPROBAR SI CON LA MANO ACTUAL SE PUEDA FORMAR ALGUNAS DE LAS JUGADAS DEL POKER--*/
    private Jugada EscaleraColor(List<Carta> c) {
        Jugada escaleraColor = null;

        if (Escalera(c) != null && esMismoPalo(c)) {
            String msgJugada = String.format("Straight Flush with %s", this.mano.getStrCartas());
            escaleraColor = new Jugada(c, tJugada.ESCALERA_COLOR, msgJugada);
        }

        return escaleraColor;
    }

    private Jugada Escalera(List<Carta> c) {
                Jugada escalera = null;

        //Distinguimos casos dependiendo de si la mano contiene Aces o no 
         List<Carta> tmp = new ArrayList<>(c);
        if (c.get(0).getSimb().equals("A")){
            Carta card = new Carta ("A",c.get(0).getPalo());
            card.setValor(1);
            tmp.add(card);

        }

        int cont = 1;
        boolean gutshot = false;
        boolean openended = false;
        boolean roto = false;
        boolean ace = false;
        int contR = 0;
        
        //caso especial Ace al principio
        if (tmp.get(0).getSimb().equals("A")){
            ace = true;
        }
        
        for ( int i = 0; i< tmp.size()-1;i++){

            if ( (tmp.get(i).getVal() - tmp.get(i+1).getVal()) == 1){
                cont ++;
            }
           
            //gutshot : K Q J 9 8
            else if ((tmp.get(i).getVal() - tmp.get(i+1).getVal()) == 2){
                roto = true;
                contR = cont +1;
                cont =1;
                ace = false;
               
            }
            else if ((tmp.get(i).getVal() - tmp.get(i+1).getVal()) > 2){
                roto = false;
                contR =0;
                cont=1;
                ace = false;
            }
           
            if (cont == 5){
                String msgJugada = String.format("Straight with %s", this.mano.getStrCartas());
                escalera = new Jugada(c, tJugada.ESCALERA, msgJugada);
                gutshot = false;
                roto = false;
                openended=false;
                contR =0;
            }
           
            else if ( cont == 4 && !ace){
                openended = true;
            }
           
            else if ( cont == 4 && ace){
                gutshot=true;
            }
            else if ( cont > 0 && roto && contR > 0 ){
                if ( cont + contR == 5){
                gutshot=true;
                roto = false;
                contR = 0;
                }
            }
        }
       
        if (openended == true){
             addDraw("Draw: Straight Open ended");
        }
       
        else if ( gutshot == true){
            addDraw("Draw: Straight Gutshot");
        }
       
        return escalera;
    }
    

    //Devuelve el poker si existe (Funciona)
    private Jugada Poker(List<Carta> c) {
        Jugada poker = null;

        int i = 0;
        int cont = 1;
        ArrayList<Carta> lista = new ArrayList<>();

        while (i < c.size() - 1) {
            int cur = c.get(i).getVal();
            int sig = c.get(i + 1).getVal();
            
            if (cur == sig) {
                cont++;
            } else {
                cont = 1;
            }

            if (cont == 4) {
                int index = i - 2;

                //Quita las 4 cartas iguales
                for (int j = 0; j < 4; j++) {
                    Carta tmp = c.remove(index);
                    lista.add(0, tmp);
                }

                //Este seria el kicker (Primero de la mano (Descendente) quitado las 4 cartas iguales)
                Carta kicker = c.remove(0); 
                lista.add(0, kicker);

                for (int k = 0; k < 5; k++) {
                    Carta tmp = lista.remove(0);
                    c.add(0, tmp);
                }

                String msgJugada = String.format("Four of a kind (%s) with %s", Evaluador.msg.get(cur - 2), getStrCartas());
                poker = new Jugada(c, tJugada.POKER, msgJugada);
                break;
            }

            ++i;
        }

        return poker;
    }

    //Devuelve un Full House (Funciona)
    private Jugada FullHouse(List<Carta> c) {
        Jugada fullHouse = null;

        //Lista auxiliar que almacenan las cartas que forman el Full House
        ArrayList<Carta> lista = new ArrayList<>();

        if (Trio(c) != null) {
            lista.add(0, c.remove(0));
            lista.add(0, c.remove(0));
            lista.add(0, c.remove(0));

            if (Pareja(c) != null) {
                lista.add(0, c.remove(0));
                lista.add(0, c.remove(0));

                for (int i = 0; i < 5; ++i) {
                    Carta tmp = lista.remove(0);
                    c.add(0, tmp);
                }
                String msgJugada = String.format("Full House with %s", getStrCartas());
                fullHouse = new Jugada(c, tJugada.FULL_HOUSE, msgJugada);
            }

        }
        return fullHouse;
    }

    //Devuelve el mejor Flush (Funciona)
    private Jugada Flush(List<Carta> c) {
        Jugada flush = null;

        //Contador para cartas de cada palo
        int contH = 0;
        int contD = 0;
        int contC = 0;
        int contS = 0;

        String palo = null; //El palo que primero consigue sus 5 cartas

        int i = 0;
        int index = c.size() - 1; //Indice hasta la cual ya hay 5 cartas del mismo palo

        while (i < c.size()) {
            //Contamos los palos
            switch (c.get(i).getPalo()) {
                case "h" ->
                    contH++;
                case "d" ->
                    contD++;
                case "c" ->
                    contC++;
                case "s" ->
                    contS++;
            }

            //Identifica que palo tiene ya sus 5 cartas
            if (contH == 5) {
                palo = "h";
                index = i;
                break;
            } else if (contD == 5) {
                palo = "d";
                index = i;
                break;
            } else if (contC == 5) {
                palo = "c";
                index = i;
                break;
            } else if (contS == 5) {
                palo = "s";
                index = i;
                break;
            }
            i++;

        }

        //Si hay flush
        if (contH > 4 || contD > 4 || contC > 4 || contS > 4) {
            //Lista auxiliar para almacenar valores del flush
            ArrayList<Carta> lista = new ArrayList<>();

            //Recorrido en sentido inverso desde index

            for (int j = index; j >= 0; --j) {
                if (c.get(j).getPalo().equals(palo)){
                    lista.add (c.get(j));
                }
            }
            
            /*for (int j = index; j >= 0; --j) {
                if (c.get(j).getPalo().equals(palo)) {
                    Carta tmp = c.remove(j);
                    lista.add(tmp);
                }
            }*/
-
            //Extraen los valores de flush y los inserta al incio de la mano
            /*for (int k = 0; k < 5; ++k) {
                Carta tmp = lista.remove(0);
                c.add(0, tmp);
            }*/
            flush = new Jugada(c, tJugada.COLOR, "Flush");
        } //No hay Flush pero si draw
        else if (contH == 4 || contD == 4 || contC == 4 || contS == 4) {
            addDraw("Draw: Flush");
        }

        return flush;
    }

    //Devuelve el mejor trio (Funciona)
    private Jugada Trio(List<Carta> c) {
        Jugada trio = null;
        int i = 0;
        int cont = 1;   //Numero de cartas del trio actual

        while (i < c.size() - 1) {
            int cur = c.get(i).getVal();
            int sig = c.get(i + 1).getVal();

            //Contamos si la actual es igual a la siguiente
            if (cur == sig) {
                cont++;
            } //Contamos de nuevo
            else {
                cont = 1;
            }

            //Si hay trio
            if (cont == 3) {
                //Quitamos esas cartas de la mano para insertarlas al inicio 
                int index = i - 1;
                Carta tmp = c.remove(index);
                Carta tmp2 = c.remove(index);
                Carta tmp3 = c.remove(index);
                //Los insertamos de esta manera para que se mantenga el orden relativo
                c.add(0, tmp3);
                c.add(0, tmp2);
                c.add(0, tmp);
                //
                String msgJugada = String.format("Three of a kind (%s) with %s", Evaluador.msg.get(cur - 2), getStrCartas());
                trio = new Jugada(c, tJugada.TRIO, msgJugada);
                break;
            }
            i++;
        }
        return trio;
    }

    //Devuelve la mejor doble pareja (Funciona)
    private Jugada DoblePareja(List<Carta> c) {
        Jugada doblePareja = null;

        //Se busca la primera pareja
        if (Pareja(c) != null) {
            //Los quitamos de la lista
            Carta tmp = c.remove(0);
            Carta tmp2 = c.remove(0);

            //Si se encuentra una segunda pareja
            if (Pareja(c) != null) {
                //Se insertan la primera pareja en la mano
                c.add(0, tmp2);
                c.add(0, tmp);
                String msgJugada = String.format("%s with %s ", "Two pairs", getStrCartas());
                doblePareja = new Jugada(c, tJugada.DOBLE_PAREJA, msgJugada);
            }
        }
        return doblePareja;
    }

    //Devuelve la mejor pareja (Funciona)
    private Jugada Pareja(List<Carta> c) {
        Jugada pareja = null;

        int i = 0;
        while (i < c.size() - 1) {
            int cur = c.get(i).getVal();
            int sig = c.get(i + 1).getVal();
            if (cur == sig) {
                //Mete la pareja de carta al principio de la jugada
                Carta tmp = c.remove(i);
                Carta tmp2 = c.remove(i);
                c.add(0, tmp);
                c.add(1, tmp2);

                //Forma la cadena de la jugada, por ejemplo: "A pair of Ases with AhAh7h6c2d"
                String msgJugada = String.format("Pair of %s with %s", Evaluador.msg.get(cur - 2), getStrCartas());
                pareja = new Jugada(c, tJugada.PAREJA, msgJugada);
                break;
            }
            i++;
        }

        return pareja;
    }


    /*-------------------------------------------------------------------------------------------------*/
 /*Getters y Setters*/
    public void setMano(Mano mano) {
        this.mano = mano;   //Cambiamos de mano
    }

}
