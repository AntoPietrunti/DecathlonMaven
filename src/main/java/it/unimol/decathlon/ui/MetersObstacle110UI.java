package it.unimol.decathlon.ui;

import it.unimol.decathlon.app.Discipline;

public class MetersObstacle110UI extends DisciplineUI {

    public MetersObstacle110UI(Discipline discipline) {
        super(discipline);
    }


    public boolean showReroll() {
        System.out.println("Rilanci (verrà applicato un malus di " + super.getDiscipline().getCurrentAttempt() + " punti)? (s/n)");
        String answer = this.inputString();
        if (answer.equalsIgnoreCase("s")) {
            return true;
        } else {
            return false;
        }

    }

    public void showSlot() {

        System.out.println("Dadi: " + super.getDiscipline().getSetDicesScore() + "    (" +
                super.getDiscipline().getTotalSetDicesScore() + " punti | " + super.getDiscipline().getCurrentAttempt() +
                " malus | totale = " +  (super.getDiscipline().getTotalSetDicesScore() - super.getDiscipline().getCurrentAttempt()) + ")");

    }
}
