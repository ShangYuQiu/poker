/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

/**
 *
 * @author shangyu
 */
public class Carta implements Comparable<Carta>{
    
    private int numero;
    private String palo;
    private String valor;
    
    
    public int getNum(){
    return numero;
    }
    
    public String palo(){
    return palo;
    }
    
    public String valor(){
    return valor;
    }

    @Override
    public int compareTo(Carta c) {
        
        if ( this.numero < c.getNum()){
        return -1;
        }
        
        else if ( this.numero == c.getNum()){
        return 0;}
        
        else {
        
        return 1;}
    }
    

}
