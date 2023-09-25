package logic;

import java.util.ArrayList;
import java.util.List;
import objects.Carta;
import objects.Mano;

//Evaluador para el apartado 1 de la práctica 
public class Evaluador {

    //private List<Carta> cartas; //Cartas iniciales
    private ArrayList<Mano> posiblesManos; //Lista de posibles jugadas 
    private Mano mejorMano; //Mejor jugada hasta el momento
    private Mano _mano;


    public Evaluador(Mano mano){
        _mano = mano;
    }
    
    /*public Evaluador(SortedArrayList<Carta> cartas) {
        this.cartas = new SortedArrayList<>();
        this.cartas = cartas;
        this.mejorMano = null;
        this.posiblesManos = new ArrayList<>();
    }
    */
    //Metodo que busca la mejor jugada posible de las cartas inciales, e busca posibles jugadas(DRAW)
    public void evaluar() {

    }

    private boolean esTodomismoPalo(){
    
        boolean b = false;
        
        
        return b;
    }
    
    private boolean esEscalera(){
        boolean b = false;
        
        
        return b;
    }
    
    private boolean esPoker(){
        boolean b = false;
        
        
        return b;
    }
    
    //Metodo de busca formar una pareja entre las cartas
   private boolean pair() {

        return false;
    }

}
