package it.unimol.decathlon.ui;

import it.unimol.decathlon.app.Discipline;
import it.unimol.decathlon.app.SlotException;

import java.util.List;

public class Meters1500UI extends DisciplineUI {
    public Meters1500UI(Discipline discipline) {
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
            int index = 1;
            try {
                for (List<Integer> listSlot : super.getDiscipline().getSetPreviousDices()) {
                    System.out.println("Slot " + index + " " + listSlot.get(index-1) + " (congelato)");
                    index++;
                }
            } catch (SlotException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Slot " + super.getDiscipline().getSlot() + ": " + super.getDiscipline().getSetDicesScore() +
                " (Totale = " + super.getDiscipline().getTotalSetDicesScore() + ")");

    }
}
