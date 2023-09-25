package objects;

import logic.Palo;


public class Carta implements Comparable<Carta> {

    private final String simbolo; //El simbolo representativo de la carta A, K, Q ...
    private Palo palo;
    private int valor; //Valor real representativo
    private String pal;
    
    public Carta(String s, String p) {
        this.simbolo = s;
        this.pal = p;
        init();
    }

    private void init() {

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

        }

        switch(pal){
            case "h" -> palo = Palo.HEARTS;
            case "d" -> palo = Palo.DIAMONDS;
                
            case "c" -> palo = Palo.CLUBS;
            case "s" -> palo = Palo.SPADES;
                
        }
    }

    public int getVal() {
        return valor;
    }

    public String getPalo() {
        return palo.toString();
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
