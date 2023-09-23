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
    
    public Carta ( String v , String p){
        
        valor =v;
        palo = p;
        conv();
    }
    
    private void conv (){
        
        switch (valor){
            
            case "A":
                numero = 14;
                break;
            case "K":
                numero = 13;
            break;
                
            case "Q":
                numero = 12;
            break;
            
            case "J":
                numero = 11;
            break;
            
            case "T":
                numero = 13;
            break;
            
            default:
                break;
                
        }
    }
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
