/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package control;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import control.commands.Apartado1;
import logic.Mano;
import objects.Carta;
/**
 *
 * @author shangyu
 */
public class Controller {
    
    private int nivel;
    private String entrada;
    private String salida;
    private String carta;
    
    
    public Controller(int nivel,String entrada,String salida){
        
        this.nivel=nivel;
        this.entrada=entrada;
        this.salida=salida;
    }

    public void run(){
        
        switch (nivel) {
            case 1:
            
                Apartado1 apartado1=new Apartado1(entrada, salida);
                apartado1.execute();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}
