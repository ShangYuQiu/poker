package objects;

import java.util.*;
import logic.SortedArrayList;
import logic.tJugada;

public class Mano {

    private ArrayList<Carta> cartas;   //Cartas de la mano
    private StringBuilder strCartas;    //Representacion en cadena de la mano
    private ArrayList<String> draws; //Mensajes explicativos de los posibles draws
    private Jugada mejorJugada;  //Mejor jugada de la mano
    private tJugada tipoJugada;  //Tipo de la mejor jugada, por ejemplo, Color, Escalera...
    private String descripcion;   //Descripcion de la mejor jugada
    private boolean ordenado;    //Nos dice si la lista de cartas esta ordenada

    public Mano() {
        this.cartas = new SortedArrayList<>();
        this.strCartas = new StringBuilder();
        this.mejorJugada = null;
        this.descripcion = null;
        this.ordenado = false;
    }

    public void agregarCarta(Carta c) {
        cartas.add(c);
    }

    //Devuelve la mano en forma de una unica String
    public String getStrCartas() {
        for (Carta c : cartas) {
            strCartas.append(c.getSimb()).append(c.getPalo());
        }
        return strCartas.toString();
    }

    public List<Carta> getCartas() {
        if (ordenado) {
            return cartas;
        }

        //Ordena las cartas de manera descendente
        Collections.sort(cartas);
        this.ordenado = true;
        return cartas;
    }

    public Jugada getJugada() {
        return this.mejorJugada;
    }

    public ArrayList<String> getDraws() {
        return draws;
    }

    public tJugada getTipoJugada() {
        return mejorJugada.getJugada();
    }

    // setters 
    public void setJugada(Jugada j) {
        this.mejorJugada = j;
    }

    public void setDraw(ArrayList<String> dr) {
        this.draws = dr;
    }

}
