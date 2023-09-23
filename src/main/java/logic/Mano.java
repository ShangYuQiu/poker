/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
