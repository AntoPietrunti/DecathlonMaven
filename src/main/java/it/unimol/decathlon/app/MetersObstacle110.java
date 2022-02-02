package it.unimol.decathlon.app;

/**
 * Rappresenta la disciplina dei 110 metri ad ostacoli. Si devono lanciare 5 dadi alla volta per un massimo di 1 volta
 * cercando di raggiungre il totale più alto possibile, con la possibilità di effettuare fino a 6 rilanci.
 *
 * @author Antonio Pietrunti
 * @version 1.0
 */

public class MetersObstacle110 extends Discipline {
    /**
     *Istanzia un oggetto MetersObstacle110 impostando il nome del gioco a "110 Meters Obstacle", il numero massimo di
     * rilanci a 6, il numero di dadi da lanciare a 5 e il numero massimo di slot a 1 della superclasse.
     * Crea 5 istanze di oggetti dice e li assegna alla superclasse.
     * @see it.unimol.decathlon.app.Discipline Discipline
     */
    public MetersObstacle110() {
        super("110 Meters Obstacle", 6, 5, 1);

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
     *
     * @return Il totale degli ultimi 5 dadi lanciati.
     */
    public int getTotalSetDicesScore() {
        int score = 0;
        for (int a : super.getSetDicesScore()) {
            score += a;
        }
        return score;
    }

    /**
     *
     * @return  Il punteggio totale della Disciplina.
     */
    public int getFullScore() {
        int score = 0;
        for (Dice dice : super.getDices()) {
            score += dice.getValue();
        }
        return score - super.getCurrentAttempt();
    }
}
