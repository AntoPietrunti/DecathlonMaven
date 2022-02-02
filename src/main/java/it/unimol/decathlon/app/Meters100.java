package it.unimol.decathlon.app;

/**
 * Rappresenta la disciplina dei 100 metri. Si devono lanciare 4 dadi alla volta per un massimo di due volte cercando
 * di raggiungre il totale più alto possibile tenendo conto che tutti i 6 valgono -6, con la possibilità di effettuare
 * fino a 5 rilanci.
 *
 * @author Antonio Pietrunti
 * @version 1.0
 */

public class Meters100 extends Discipline {
    /**
     *Istanzia un oggetto Meters100 impostando il nome del gioco a "100 Meters", il numero massimo di rilanci a 5, il
     * numero di dadi da lanciare a 4 e il numero massimo di slot a 2 della superclasse.
     * Crea 8 istanze di oggetti dice e li assegna alla superclasse.
     * @see it.unimol.decathlon.app.Discipline Discipline
     */
    public Meters100() {
        super("100 Meters", 5,4, 2);

        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        Dice dice3 = new Dice();
        Dice dice4 = new Dice();
        Dice dice5 = new Dice();
        Dice dice6 = new Dice();
        Dice dice7 = new Dice();
        Dice dice8 = new Dice();

        super.addDice(dice1);
        super.addDice(dice2);
        super.addDice(dice3);
        super.addDice(dice4);
        super.addDice(dice5);
        super.addDice(dice6);
        super.addDice(dice7);
        super.addDice(dice8);
    }

    /**
     * Se il valore di un dado risulterà uguale a 6, verrà conteggiato come -6.
     *
     * @return Il totale degli ultimi 4 dadi lanciati.
     */
    public int getTotalSetDicesScore() {
        int score = 0;
        for (int a : super.getSetDicesScore()) {
            if (a == 6) {
                score += -6;
            } else {
                score += a;
            }
        }
        return score;
    }

    /**
     * Se il valore di un dado risulterà uguale a 6, verrà conteggiato come -6.
     *
     * @return  Il punteggio totale della Disciplina.
     */
    public int getFullScore() {
        int score = 0;
        for (Dice dice : super.getDices()) {
            if (dice.getValue() == 6) {
                score += -6;
            } else {
                score += dice.getValue();
            }
        }
        return score;
    }
}
