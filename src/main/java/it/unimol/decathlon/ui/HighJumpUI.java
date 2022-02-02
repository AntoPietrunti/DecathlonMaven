package it.unimol.decathlon.ui;

import it.unimol.decathlon.app.Discipline;
import it.unimol.decathlon.app.HighJump;
import it.unimol.decathlon.app.SlotException;

public class HighJumpUI extends DisciplineUI {
    public HighJumpUI(Discipline discipline) {
        super(discipline);
    }

    public void startGame() {
        boolean result = false;
        while ((super.getDiscipline().getCurrentAttempt() < super.getDiscipline().getMaxAttempts()) && (!result)){
            System.out.println("Premi invio per lanciare i dadi ");
            super.inputString();
            try {
                super.getDiscipline().roll();
            } catch (SlotException e) {
                e.printStackTrace();
            }
            if (super.getDiscipline().getTotalSetDicesScore() >= ((HighJump)super.getDiscipline()).getHeight()) {
                result = true;
            }
            this.showSlot();
            if(result){
                System.out.println("Hai totalizzato " + super.getDiscipline().getTotalSetDicesScore() +
                        ": hai raggiunto l'obiettivo di " + ((HighJump)super.getDiscipline()).getHeight());
            } else {
                System.out.println("Hai totalizzato " + super.getDiscipline().getTotalSetDicesScore() +
                        ", non hai raggiunto l'obiettivo di " + ((HighJump)super.getDiscipline()).getHeight());
            }
        }
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
        System.out.println("Tentativo " + super.getDiscipline().getSlot() + ": " +
                            super.getDiscipline().getSetDicesScore());
    }


}
