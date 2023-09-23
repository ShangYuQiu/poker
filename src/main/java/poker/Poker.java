/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poker;
import control.controller;
/**
 *
 * @author shangyu
 */
public class Poker {
    
    public static void main(String[] args) {
        
        Controller controller=new Controller(args[1],args[2]);
        
        if(args[0].equalsIgnoreCase("1")){
            
            controller.run(1);
        }
        else if(args[0].equalsIgnoreCase("2")){
             controller.run(2);
        }
        else if(args[0].equalsIgnoreCase("3")){
             controller.run(3);
        }
    }
}
