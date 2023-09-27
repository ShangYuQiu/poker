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

    private ArrayList<tJugada> Jugadas; //Lista de posibles jugadas 
    private Mano mano;

    public Evaluador() {
        this.mano = null;
    }

    //Metodo que busca la mejor jugada de las cartas inciales, y busca posibles jugadas(DRAW)
    public void evaluar() {
        List<Carta> c = mano.getCartas();
    }

    /*-------------------------------------METODOS AUXILIARES-------------------------------------------*/
    //Metodo para comprobar que todas las cartas son del mismo palo 
    private boolean esMismoPalo() {
        List<Carta> c = this.mano.getCartas();
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
    private boolean esEscaleraColor() {
    }

    private Jugada esEscalera() {
        Jugada j = null;
        Mano //Caso especial, vamos a ver si podemos formar escalera colacando A al final
                /*if (c.get(0).getSimb().equals("A")) {
            Carta A = c.get(0);
            c.remove(A);
            c.add(A);

            b = true;
            i = 0;
            while (i < c.size() - 1 && b) {
                if (abs(c.get(i).getNum() - c.get(i + 1).getNum()) != 1) {
                    b = false;
                }
                i++;
            }
        }*/
        return j;
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

    private boolean esFlush(List<Carta> c) {

        //draw
        return false;
        //TODO: pendiente comprobar si se forma draws
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
        this.Jugadas.clear(); //Limpiamos las jugadas de la anterior mano
    }

}
