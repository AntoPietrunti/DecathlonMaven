package it.unimol.decathlon.ui;

import it.unimol.decathlon.app.Discipline;
import it.unimol.decathlon.app.SlotException;


public class Meters100UI extends DisciplineUI {

    public Meters100UI(Discipline discipline) {
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
        if(super.getDiscipline().getSlot() > 1) {
            try {
                System.out.println("Slot " + (super.getDiscipline().getSlot() - 1) + " " +
                        super.getDiscipline().getSetPreviousDices() + " (congelato)");
            } catch (SlotException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Slot " + super.getDiscipline().getSlot() + ": " + super.getDiscipline().getSetDicesScore());
        System.out.println("(Totale = " + super.getDiscipline().getTotalSetDicesScore() + ")");

    }

}

