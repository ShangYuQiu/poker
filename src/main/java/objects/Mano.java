package objects;

import java.util.*;
import logic.tJugada;
import logic.SortedArrayList;

public class Mano {

    private List<Carta> cartas;   //Cartas de la mano
    private StringBuilder strCartas;    //Representacion en cadena de la mano
    private ArrayList<String> draws; //Mensajes explicativos de los posibles draws
    private Jugada jugada;
    private boolean ordenado;    //Nos dice si la lista de cartas esta ordenada

    public Mano() {
        this.cartas = new SortedArrayList<>();
        this.strCartas = new StringBuilder();
        this.jugada = null;
        this.draws = new ArrayList<String>();
        this.ordenado = false;
    }

    //Getters y Setters
    public void agregarCarta(Carta c) {
        cartas.add(c);
        strCartas.append(c.getSimb()).append(c.getPalo());
    }

    //Devuelve la mano en forma de una unica String
    public String getStrCartas() {
        return strCartas.toString();
    }

    public List<Carta> getCartas() {
        if (ordenado) {
            return cartas;
        }

        Collections.sort(cartas);
        this.ordenado = true;
        return cartas;
    }

    public Jugada getJugada(){
        return this.jugada;
    }
    
    public ArrayList <String> getDraws(){
        return draws;
    }
    // setters
    
    public void setJugada(Jugada j){
           this.jugada = j;
    }
    
    public void setDraws ( ArrayList <String> dr){
        this.draws = dr;
    }


}
