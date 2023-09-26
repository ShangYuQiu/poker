package objects;

import java.util.*;
import logic.Jugada;
import logic.SortedArrayList;

//Representa una posible jugadas de todas las cartas
public class Mano {

    private List<Carta> cartas; 
    private Jugada jugada;
    private ArrayList <String> draw;

    public Mano() {
        cartas = new SortedArrayList<Carta>();
        //this.jugada = jugada;
    }

    public void anniadirCart(Carta c){
        
        cartas.add(c);
    }
    public List<Carta> getCartas() {
        return cartas;
    } 
    public void ordenar() {
    	Collections.sort(cartas);
    }
    @Override
    public String toString(){
        //Imprime por ejemplo "AhAcTh.."
        String a="";
    	for(Carta l:cartas) {
    		
    		a+=l.getVal();
    		a+=l.getPalo();
    	}
        return a;
    }
    
}
