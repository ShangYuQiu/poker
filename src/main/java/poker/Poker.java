/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker;
import control.Controller;
/**
 *
 * @author shangyu
 */
public class Poker {
    
    private static void cargar2(String entrada){
	        
	 }
    private static void cargar3(String entrada){
	        
	 }
    public static void main(String[] args) {
        
        if(args[0].equalsIgnoreCase("1")){
            Controller controller=new Controller(1,args[1],args[2]);
            controller.run();
        }
        else if(args[0].equalsIgnoreCase("2")){
             cargar2(args[1]);
        }
        else if(args[0].equalsIgnoreCase("3")){
             cargar3(args[1]);
        }
    }
}
