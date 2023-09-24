package logic;

import objects.Carta;
import java.util.*;
/**
 *
 * @author shangyu
 */
public class Mano {
 
     private String id;
    private List <Carta> cartas ;

    
    public Mano (){
        cartas = new SortedArrayList<Carta>();
    }
    
    
    public void anniadirCart(Carta c){
        
        cartas.add(c);
    }
    
    public List <Carta> getCartas(){
    
        return cartas;
    }
}
