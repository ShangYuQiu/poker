package objects;


public class Carta implements Comparable<Carta> {

    private final String simbolo; //El simbolo representativo de la carta A, K, Q ...
    private final Palo palo;
    private int valor; //Valor real representativo

    public Carta(String s, Palo p) {
        this.simbolo = s;
        this.palo = p;
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
    public int compareTo(Carta c) {

        if (this.valor < c.getVal()) {
            return -1;
        } else if (this.valor == c.getVal()) {
            return 0;
        } else {
            return 1;
        }
    }

}
