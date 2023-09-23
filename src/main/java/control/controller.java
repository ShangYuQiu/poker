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
import control.commands.apartado1;
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
    public void cargar1(String entrada){
        try{
	        File f = new File(entrada);
                        FileInputStream fis = new FileInputStream(f);
			InputStreamReader fr = new InputStreamReader(fis);
	        	BufferedReader in = new BufferedReader(fr);
	        	carta=in.readLine();
                        
        }catch(Exception e){ 
            e.printStackTrace();
        }          
	 
    }
    public void run(){
        if(nivel==1){
            cargar1(entrada);
            Apartado1 apartado1=new Apartado1(carta);
            apartado1.execute();
        }
        else if(nivel==2){
            
        }
        else if(nivel==3){
            
        }
    }
}
