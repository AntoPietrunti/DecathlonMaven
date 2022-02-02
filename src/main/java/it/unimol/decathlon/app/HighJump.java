package it.unimol.decathlon.app;

/**
 * Rappresenta la disciplina del salto in alto. Bisogna scegliere un'altezza da raggiungere lanciando 5 dadi
 * compresa tra {@value MIN_HEIGHT} e {@value MAX_HEIGHT}. Se il totale dei dadi lanciati sarà superiore all'altezza
 * prefissata, verrà assegnato un punteggio pari all'altezza prefissata, altrimenti 0. Inoltre, se non si
 * raggiunge l'altezza, è possibile effettuare fino a 3 rilanci.
 *
 * @author Antonio Pietrunti
 * @version 1.0
 */

public class HighJump extends Discipline {
    private int height;
    private static final int MIN_HEIGHT = 5;
    private static final int MAX_HEIGHT = 30;

    /**
     *Istanzia un oggetto HighJump impostando il nome del gioco a "High Jump", il numero massimo di rilanci a 3, il
     * numero di dadi da lanciare a 5 e il numero massimo di slot a 1 della superclasse.
     * Crea 5 istanze di oggetti dice e li assegna alla superclasse.
     * @see it.unimol.decathlon.app.Discipline Discipline
     */
    public HighJump() {
        super("High Jump", 3, 5, 1);

        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        Dice dice3 = new Dice();
        Dice dice4 = new Dice();
        Dice dice5 = new Dice();

        super.addDice(dice1);
        super.addDice(dice2);
        super.addDice(dice3);
        super.addDice(dice4);
        super.addDice(dice5);
    }

    /**
     * Controllo sulla compatibilità dell'altezza che si desidera raggiungere con relativo set.
     *
     * @param height altezza che si desidera raggiungere.
     * @return ritorna true se l'altezza che si desidera raggiungere è compresa tra 5 e 30.
     */
    public boolean setHeight(int height) {
        if ((MIN_HEIGHT <= height) && (height <= MAX_HEIGHT)) {
            this.height = height;
            return true;
        } else {
            return false;
        }
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * @return il totale dei 5 dadi lanciati.
     */
    public int getTotalSetDicesScore() {
        int score = 0;
        for (int a : super.getSetDicesScore()) {
            score += a;
        }
        return score;
    }

    /**
     * Se il totale dei dadi lanciati è superiore all'altezza prefissata il punteggio totale sarà l'altezza stessa,
     * altrimenti 0.
     *
     * @return Il punteggio totale della Disciplina.
     */
    public int getFullScore() {
        int score = 0;
        for (int a : super.getSetDicesScore()) {
            score += a;
        }
        if (this.height > score) {
            return 0;
        } else {
            return this.height;
        }
    }
}