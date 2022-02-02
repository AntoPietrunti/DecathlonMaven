package it.unimol.decathlon.app;

import java.io.Serializable;
import java.util.Random;

/**
 * Rappresenta il dado che verrà lanciato dal singolo giocatore.
 * Per lanciare il dado chiamare il metodo {@link #roll()}.
 *
 * @author Antonio Pietrunti
 * @version 1.0
 */
public class Dice implements Serializable {

    private int dado;
    public Dice () { };

    /**
     * Roll assegna al dado un valore casuale compreso tra 1 e 6.
     */
    public void roll() {
        Random random = new Random();
        this.dado = random.nextInt(6) + 1;
    }

    public int getValue() {
        return  this.dado;
    }

    public void setValue(int n) {
        this.dado = n;
    }
}