package objects;

import java.util.*;
import logic.Jugada;

//Representa una posible jugadas de todas las cartas
public class Mano {

    private List<Carta> cartas; 
    private Jugada jugada;

    public Mano(List<Carta> cartas, Jugada jugada) {
        cartas = new SortedArrayList<Carta>();
        this.jugada = jugada;
    }

    public List<Carta> getCartas() {
        return cartas;
    } 
    
    @Override
    public String toString(){
        //Imprime por ejemplo "AhAcTh.."
        return null;
    }
    
}
