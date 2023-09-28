package logic;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import objects.Carta;
import objects.Jugada;
import objects.Mano;

public class Evaluador {

    //Mensajes que se imprimira para las distintas jugadas
    private static final List<String> msg = List.of("Twos", "Threes", "Fours",
            "Fives", "Sixes", "Sevens", "Eights", "Nines",
            "Tens", "Jacks", "Queens", "Kings", "Ases");

    private ArrayList<String> draws; //Lista de jugadas draw
    private Mano mano;

    public Evaluador() {
        this.mano = null;
         this.draws = new ArrayList <String>();
    }

    //Metodo que busca la mejor jugada de las cartas inciales, y busca posibles jugadas(DRAW)
    public void evaluar() {
       
        List<Carta> c = mano.getCartas();
        Jugada j;

        // pasar la jugada que ha evaluado a mano correspondiente
        if ((j = EscaleraColor(c)) != null) {
            mano.setJugada(j);

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
            String msgJugada = String.format("High Card", this.mano.getStrCartas());
            j = new Jugada(c, tJugada.CARTA_ALTA, msgJugada);
            mano.setJugada(j);
        }

        mano.setDraws(draws);
        
    }

    /*-------------------------------------METODOS AUXILIARES-------------------------------------------*/
    //Metodo para comprobar que todas las cartas son del mismo palo 
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

    //Metodo para comprobar que todas las cartas son del mismo valor independientemente del palo
    private boolean esMismoValor(List<Carta> c) {
        boolean mismoValor = true;

        int i = 1;
        int primValor = c.get(0).getVal();
        while (i < c.size() && mismoValor) {
            if (primValor != c.get(i).getVal()) {
                mismoValor = false;
            }
        }

        return mismoValor;
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
        //Elimina los duplicados y forma una nueva lista
        Set<Carta> sinDuplicado = new LinkedHashSet<>(c);
        ArrayList<Carta> nList = new ArrayList<>(sinDuplicado);

        //Si hay 5 elem, y la diferencia del primero respecto al ultimo es de 4 => Escalera
        if (nList.size() == 5 && abs(nList.get(0).getVal() - nList.get(4).getVal()) == 4) {
            String msgJugada = String.format("Straight with %s", this.mano.getStrCartas());
            escalera = new Jugada(c, tJugada.ESCALERA, msgJugada);
        } //Si hay al menos 4 elem, y la diferencia del primero respecto al penultimo es de 4 => gutshot
        else if (nList.size() >= 4 && abs(nList.get(0).getVal() - nList.get(3).getVal()) == 4) {
            draws.add("Draw: Straight gutshot");
        } else if (nList.size() == 4 && abs(nList.get(0).getVal() - nList.get(3).getVal()) == 3) {
            draws.add("Draw: Straight open ended");
        } else if (nList.size() == 5
                && ((abs(nList.get(0).getVal() - nList.get(3).getVal())) == 3) || (abs(nList.get(1).getVal() - nList.get(4).getVal()) == 3)) {
            draws.add("Draw: Straight open ended");
        }

        return escalera;
    }

    //Devuelve el quad si existe
    private Jugada Poker(List<Carta> c) {
        Jugada poker = null;

        //Sublista con los 4 primeras cartas
        List<Carta> prim = c.subList(0, 4);
        //Sublista con las 4 ultimas cartas
        List<Carta> ult = c.subList(1, 5);

        if (esMismoValor(prim) || esMismoValor(ult)) {
            int cur = c.get(2).getVal();
            String msgJugada = String.format("Four of a kind (%s) with %s", Evaluador.msg.get(cur - 2), this.mano.getStrCartas());
            poker = new Jugada(c, tJugada.POKER, msgJugada);
        }

        return poker;
    }

    //Devuelve el Full House si existe
    private Jugada FullHouse(List<Carta> c) {
        Jugada fullHouse = null;

        //Sublista cogiendo XXX-XX
        List<Carta> prim = c.subList(0, 3); //XXX-YY
        List<Carta> sec = c.subList(3, 5);  //YYY-XX

        //Sublista cogiendo XX-XXX
        List<Carta> thrd = c.subList(0, 2); //XX-YYY
        List<Carta> frth = c.subList(2, 5); //YY-XXX

        //Comprobar que ambas partes son iguales
        if (((Trio(prim) != null) && (Pareja(sec) != null))
                || ((Pareja(thrd) != null) && (Trio(frth) != null))) {
            String msgJugada = String.format("Full House with %s", this.mano.getStrCartas());
            fullHouse = new Jugada(c, tJugada.FULL_HOUSE, msgJugada);
        }

        return fullHouse;
    }

    private Jugada Flush(List<Carta> c) {
        Jugada flush = null;

        // contadores de 4 palos
        int contH = 0;
        int contD = 0;
        int contC = 0;
        int contS = 0;

        //Cuenta el numero de carta de cada tipo de palo
        for (int i = 0; i < c.size(); i++) {

            switch (c.get(i).getPalo()) {

                case "HEARTS" ->
                    contH++;

                case "DIAMONDS" ->
                    contD++;

                case "CLUBS" ->
                    contC++;

                case "SPADES" ->
                    contS++;

            }
        }

        if (contH == 4 || contD == 4 || contC == 4 || contS == 4) { //Comprobar si hay draw de flush
            draws.add("Draw: Flush");
        } else if (contH > 4 || contD > 4 || contC > 4 || contS > 4) { //Comprobar si hay flush
            flush = new Jugada(c, tJugada.COLOR, "Flush");
        }

        return flush;
    }

    //Devuelve el mejor trio
    private Jugada Trio(List<Carta> c) {
        Jugada trio = null;

        //Existe exactamente 3 maneras de hacer trios con 5 cartas(cartas ordenadas)
        List<Carta> prim = c.subList(0, 3); //XXX-XX
        List<Carta> seg = c.subList(1, 4);  //X-XXX-X
        List<Carta> ult = c.subList(2, 5);  //XX-XXX

        //Segun el segmento que forma el trio tiene un mensaje u otro
        if (esMismoValor(prim)) {
            int cur = c.get(0).getVal(); //Valor de la carta que forma el trio
            String msgJugada = String.format("Three of a kind (%s) with %s", Evaluador.msg.get(cur - 2), mano.getStrCartas());
            trio = new Jugada(c, tJugada.TRIO, msgJugada);
        } else if (esMismoValor(seg)) {
            int cur = c.get(1).getVal(); //Valor de la carta que forma el trio
            String msgJugada = String.format("Three of a kind (%s) with %s", Evaluador.msg.get(cur - 2), mano.getStrCartas());
            trio = new Jugada(c, tJugada.TRIO, msgJugada);
        } else if (esMismoValor(ult)) {
            int cur = c.get(2).getVal(); //Valor de la carta que forma el trio
            String msgJugada = String.format("Three of a kind (%s) with %s", Evaluador.msg.get(cur - 2), mano.getStrCartas());
            trio = new Jugada(c, tJugada.TRIO, msgJugada);
        }

        return trio;
    }

    //Devuelve la mejor doble pareja
    private Jugada DoblePareja(List<Carta> c) {
        Jugada doblePareja = null;

        //Sublistas segun las posibles combinaciones
        List<Carta> fst = c.subList(0, 2);  //XX-YYY
        List<Carta> sec = c.subList(1, 3);   //Y-XX-YY
        List<Carta> thrd = c.subList(2, 4); //YY-XX-Y
        List<Carta> frth = c.subList(3, 5); //YYY-XX

        //Primera posibilidad : XX-XX-Y
        if (((Pareja(fst) != null) && (Pareja(thrd) != null))
                //Segunda posibilidad : XX-Y-XX
                || ((Pareja(fst) != null) && (Pareja(frth) != null))
                //Tercarea posibilidad : Y-XX-XX
                || ((Pareja(sec) != null) && (Pareja(frth) != null))) {
            String msgJugada = String.format("%s with %s ", "Two pair", mano.getStrCartas());
            doblePareja = new Jugada(c, tJugada.DOBLE_PAREJA, msgJugada);
        }

        return doblePareja;

    }

    //Devuelve la mejor pareja 
    private Jugada Pareja(List<Carta> c) {
        Jugada pareja = null;

        int i = 0;
        while (i < c.size() - 1) {
            int cur = c.get(i).getVal();
            int sig = c.get(i + 1).getVal();
            if (cur == sig) {
                //Forma la cadena de la jugada, por ejemplo: "A pair of Ases with AhAh7h6c2d"
                String msgJugada = String.format("Pair of %s with %s", Evaluador.msg.get(cur - 2), mano.getStrCartas());
                pareja = new Jugada(c, tJugada.PAREJA, msgJugada);
                break;
            }
        }

        return pareja;
    }


    /*-------------------------------------------------------------------------------------------------*/
 /*Getters y Setters*/
    public void setMano(Mano mano) {
        this.mano = mano;   //Cambiamos de mano
        this.draws.clear(); //Limpia los draws de la anterior mano
    }

}
