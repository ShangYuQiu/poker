package logic;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import objects.Carta;
import objects.Mano;

//Evaluador para el apartado 1 de la práctica 
public class Evaluador {

    //private List<Carta> cartas; //Cartas iniciales
    private ArrayList<tJugada> posiblesManos; //Lista de posibles jugadas 
    private Mano _mano;

    public Evaluador(Mano mano) {
        _mano = mano;
    }

    //Metodo que busca la mejor jugada de las cartas inciales, y busca posibles jugadas(DRAW)
    public void evaluar() {
        List<Carta> c = _mano.getCartas();
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
        while (i < c.size()) {
            if (primValor != c.get(i).getVal()) {
                mismoValor = false;
            }
        }

        return mismoValor;
    }

    /*--------------------------------------------------------------------------------------------------*/

 /*-- METODOS PARA COMPROBAR SI CON LA MANO ACTUAL SE PUEDA FORMAR ALGUNAS DE LAS JUGADAS DEL POKER--*/
    private boolean esEscaleraColor(List<Carta> c) {
        return esEscalera(c) && esMismoPalo(c);
    }

    private boolean esEscalera(List<Carta> c) {
        boolean b = true;

        int i = 0;
        while (i < c.size() - 1 && b
                ) {
            if (abs(c.get(i).getVal() - c.get(i + 1).getVal()) != 1) {
                b = false;
            }
            i++;
        }

        //Caso especial, vamos a ver si podemos formar escalera colacando A al final
        if (c.get(0).getSimb().equals("A")) {
            Carta A = c.get(0);
            c.remove(A);
            c.add(A);

            b = true;
            i = 0;
            while (i < c.size() - 1 && b) {
                if (abs(c.get(i).getVal() - c.get(i + 1).getVal()) != 1) {
                    b = false;
                }
                i++;
            }
        }

        //TODO: Pendiente de comprobar si se forma draws
        return b;
    }

    private boolean esPoker(List<Carta> c) {
        boolean poker = true;

        //Sublista con los 4 primeras cartas
        List<Carta> prim = c.subList(0, 4);
        //Sublista con las 4 ultimas cartas
        List<Carta> ult = c.subList(1, 5);

        //Comprobar si ambas sublistas contiene 4 cartas iguales
        poker = esMismoValor(prim);

        //Si no forma quad se comprueba el otro caso
        if (!poker) {
            esMismoValor(ult);
        }

        return poker;
    }

    private boolean esFullHouse(List<Carta> c) {
        boolean fullHouse = true;

        //Sublista cogiendo XXX y XX
        List<Carta> prim = c.subList(0, 3);
        List<Carta> ult = c.subList(3, 5);

        //Comprobar que las 3 primeras son iguales, y las 2 ultimas son iguales
        fullHouse = esMismoValor(prim) && esMismoValor(ult);

        //Si la primera sublista no forma un fullHouse
        if (!fullHouse) {
            //Sublista cogiendo XX y XXX
            prim = c.subList(0, 2);
            ult = c.subList(2, 5);

            //Comprobar que las 2 primeras son iguales, y las 2 ultimas son iguales
            fullHouse = esMismoValor(prim) && esMismoValor(ult);

        }

        return fullHouse;
    }

    private boolean esFlush(List<Carta> c) {
        return esMismoPalo(c);
        //TODO: pendiente comprobar si se forma draws
    }

    private boolean esTrio(List<Carta> c) {
        boolean trio;

        //Existe exactamente 3 maneras de hacer trios con 5 cartas(cartas ordenadas)
        List<Carta> prim = c.subList(0, 3); //XXX-XX
        List<Carta> seg = c.subList(1, 4);  //X-XXX-X
        List<Carta> ult = c.subList(2, 5);  //XX-XXX

        trio = esMismoValor(prim) || esMismoValor(seg) || esMismoValor(ult);

        return trio;
    }

    private boolean esDoblePareja(List<Carta> c) {
        boolean doblePareja;

        List<Carta> fst = c.subList(0, 2);  //XX-YYY
        List<Carta> sec = c.subList(1, 3);   //Y-XX-YY
        List<Carta> thrd = c.subList(2, 4); //YY-XX-Y
        List<Carta> frth = c.subList(3, 5); //YYY-XX

        //Primera posibilidad : XX-XX-Y
        //Segunda posibilidad : XX-Y-XX
        //Tercarea posibilidad : Y-XX-XX
        doblePareja = (esPareja(fst) && esPareja(sec)) || (esPareja(fst) && esPareja(frth)) || (esPareja(sec) && esPareja(frth));

        return doblePareja;

    }

    private boolean esPareja(List<Carta> c) {
        boolean pareja = false;

        int i = 0;
        while (i < c.size() - 1 && !pareja) {
            if (c.get(i).getVal() == c.get(i + 1).getVal()) {
                pareja = true;
            }
        }

        return pareja;
    }

    private boolean esCartaAlta(List<Carta> c) {
        return !esEscaleraColor(c) && !esPoker(c)
                && !esFullHouse(c) && !esFlush(c) && !esEscalera(c) && !esTrio(c) && !esDoblePareja(c) && !esPareja(c);
    }

    /*-------------------------------------------------------------------------------------------------*/
}
