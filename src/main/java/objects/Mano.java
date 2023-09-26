package objects;

import java.util.*;
import logic.tJugada;
import logic.SortedArrayList;

public class Mano {

    private List<Carta> cartas;   //Cartas de la mano
    private tJugada mejorJugada;  //Mejor jugada de la mano
    private String descripcion;   //Descripcion de la mejor jugada

    public Mano() {
        cartas = new SortedArrayList<>(); //Las cartas se ordenan automaticamente en funcion de su valor
        this.mejorJugada = null;
        this.descripcion = null;
    }

    //Getters y Setters
    public void agregarCarta(Carta c) {
        cartas.add(c);
    }

    public List<Carta> getCartas() {
        Collections.sort(cartas);
        return cartas;
    }

    @Override
    //Imprime la mejor jugada que se puede formar con esta mano
    public String toString() {
        return mejorJugada.toString();
    }

}
