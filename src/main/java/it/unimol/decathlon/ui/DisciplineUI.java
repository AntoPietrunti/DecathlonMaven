package it.unimol.decathlon.ui;

import it.unimol.decathlon.app.AttemptException;
import it.unimol.decathlon.app.Discipline;
import it.unimol.decathlon.app.SlotException;

import java.util.Scanner;

public abstract class DisciplineUI  {
    private Discipline discipline;
    public DisciplineUI(Discipline discipline) {
        this.discipline = discipline;
    }

    public void startGame() {
        try {
            this.discipline.roll();
        } catch (SlotException e) {
            e.printStackTrace();
        }
        while ((this.discipline.getAttemptLeft() > 0) && (this.discipline.getSlot() < this.discipline.getMaxSlot())) {
            this.showSlot();
            if (this.showReroll()) {
                try {
                    this.discipline.reRoll();
                } catch (AttemptException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    this.discipline.roll();
                } catch (SlotException e) {
                    e.printStackTrace();
                }
            }
        }

        if (this.discipline.getSlot() < this.discipline.getMaxSlot()) {
            while (this.discipline.getSlot() < this.discipline.getMaxSlot()) {
                this.showSlot();
                try {
                    this.discipline.roll();
                } catch (SlotException e) {
                    e.printStackTrace();
                }
            }
            this.showSlot();
        } else {
            this.showSlot();
            while ((this.discipline.getAttemptLeft() > 0) && (this.showReroll())) {

                try {
                    this.discipline.reRoll();
                } catch (AttemptException e) {
                    e.printStackTrace();
                }
                this.showSlot();
            }
        }
    }

    public Discipline getDiscipline() {
        return this.discipline;
    }

    public abstract boolean showReroll();

    public abstract void showSlot();

    public String inputString() {
        return new Scanner(System.in).nextLine();
    }
}
