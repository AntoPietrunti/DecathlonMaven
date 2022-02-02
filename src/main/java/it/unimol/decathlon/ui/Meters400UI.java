package it.unimol.decathlon.ui;

import it.unimol.decathlon.app.Discipline;


public class Meters400UI extends DisciplineUI {
    
    public Meters400UI(Discipline discipline) {
        super(discipline);
    }


    public boolean showReroll() {
        System.out.println("Rilanci (" + super.getDiscipline().getAttemptLeft() + " restanti)? (s/n)");
        String answer = this.inputString();
        if (answer.equalsIgnoreCase("s")) {
            return true;
        } else {
            return false;
        }

    }

    public void showSlot() {
        System.out.println("Slot " + super.getDiscipline().getSlot() + ": " + super.getDiscipline().getSetDicesScore() +
                            " (Totale = " + super.getDiscipline().getTotalSetDicesScore() + ")");

    }
}
