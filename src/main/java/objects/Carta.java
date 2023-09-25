package objects;

import logic.Palo;


public class Carta implements Comparable<Carta> {

    private String simbolo; //El simbolo representativo de la carta A, K, Q ...
    private Palo palo;
    private int valor; //Valor real representativo
    private String pal;
    
    public Carta(String s, String p) {
        this.simbolo = s;
        this.pal = p;
        init();
    }

    private void init() {
        /* 
        switch (simbolo) {

            case "A" ->
                valor = 14;
            case "K" ->
                valor = 13;

            case "Q" ->
                valor = 12;

            case "J" ->
                valor = 11;

            case "T" ->
                valor = 10;

            default -> Integer.parseInt(simbolo);

        }

        switch(pal){
            case "h" -> palo = Palo.HEARTS;
            case "d" -> palo = Palo.DIAMONDS;
                
            case "c" -> palo = Palo.CLUBS;
            case "s" -> palo = Palo.SPADES;
                
        }
        */
        /*try{
	    	if(simbolo.equals("A")) {
	    		valor=14;
	    	}
	    	else if(simbolo.equals("K")) {
	    		valor=13;
	    	}
	    	else if(simbolo.equals("Q")) {
	    		valor=12;
	    	}
	    	else if(simbolo.equals("J")) {
	    		valor=11;
	    	}
	    	else if(simbolo.equals("T")) {
	    		valor=10;
	    	}
	    	else {
	    		valor=Integer.parseInt(simbolo);
	    	}
    	}catch(NumberFormatException e) {
    		System.out.println(e);
    	}*/

	       try{
            valor = switch (simbolo) {
                case "A" -> 14;
                case "K" -> 13;
                case "Q" -> 12;
                case "J" -> 11;
                case "T" -> 10;
                default -> Integer.parseInt(simbolo);
            };
    	}catch(NumberFormatException e) {
    		System.out.println(e);
    	}
        
        try{
            switch(pal){
            case "h" -> palo = Palo.HEARTS;
            case "d" -> palo = Palo.DIAMONDS;
                
            case "c" -> palo = Palo.CLUBS;
            case "s" -> palo = Palo.SPADES;
                
            }
        }
        
        catch(Exception e){
            System.out.println(e);
        }
    }

    public int getVal() {
        return valor;
    }

    public String getPalo() {

        //return palo.toString();
        return pal;
    }

    public String getSimb() {
        return simbolo;
    }

    @Override
    public int compareTo(Carta o) {

//        //Orden ascendente
//        if (this.valor < o.getVal()) {
//            return -1;
//        } else if (this.valor == o.getVal()) {
//            return 0;
//        } else {
//            return 1;
//        }
        
        //Orden descendente
        return o.getVal() - this.valor;
    }

}
