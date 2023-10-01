package objects;

import java.util.*;
import logic.SortedArrayList;
import logic.tJugada;

public class Mano {

    private ArrayList<Carta> cartas;   //Cartas de la mano
    private StringBuilder strCartas;   //Representacion en cadena de la mano
    private ArrayList<String> draws;   //Mensajes explicativos de los posibles draws de la mano
    private Jugada jugada;             //Mejor jugada de la mano
    private String descripcion;        //Descripcion de la mejor jugada
    private boolean ordenado;          //Para saber si la mano esta ordenada

    public Mano() {
        this.cartas = new SortedArrayList<>();
        this.strCartas = new StringBuilder();
        this.draws = new ArrayList<>();
        this.jugada = null;
        this.ordenado = false;
    }

    //Metodo para anyadir carta a la mano
    public void agregarCarta(Carta c) {
        cartas.add(c);
    }

    //Devuelve la mano en forma de una unica String
    public String getStrCartas() {
        if (strCartas.isEmpty()) {
            for (Carta c : cartas) {
                strCartas.append(c.getSimb()).append(c.getPalo());
            }
        }
        return strCartas.toString();
    }

    //Devuelve la mano pero ordenado si no lo estaba (Descendente)
    public List<Carta> getCartas() {
        if (ordenado) {
            return cartas;
        }
        //Ordena las cartas 
        Collections.sort(cartas);
        this.ordenado = true;
        return cartas;
    }

    //Devuelve la mejor jugada de la mano
    public Jugada getJugada() {
        return this.jugada;
    }

    //Lista de mensajes de draws
    public ArrayList<String> getDraws() {
        return draws;
    }

    //Si la jugada es Escalera, Color... 
    public tJugada getTipoJugada() {
        return jugada.getJugada();
    }
    
    //Devuelve msg de la mejor jugada
    public String getDescripcion(){
        return this.descripcion;
    }

    public void addDraw(String msg) {
        this.draws.add(msg);
    }
    
    public void setJugada(Jugada j) {
        this.jugada = j;
    }

}
