package it.unimol.decathlon.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta una disciplina del gioco Decathlon.
 *
 * @author Antonio Pietrunti
 * @version 1.0
 */

public abstract class Discipline implements Serializable {
    private String name;
    private List<Dice> dices = new ArrayList<Dice>();
    private int maxAttempts;
    private int currentAttempt;
    private int maxSlot;
    private int slot;
    private int setDices;

    /**
     *
     * @param name nome della Disciplina.
     * @param maxAttempts numero massimo di tentativi per il lancio dei dadi.
     * @param setDices numero di dadi da lanciare ad ogni lancio.
     * @param maxSlot numero di lanci da effettuare.
     */
    public  Discipline(String name, int maxAttempts, int setDices, int maxSlot) {
        this.setName(name);
        this.setMaxAttempts(maxAttempts);
        this.currentAttempt = 0;
        this.slot = 0;
        this.setDices = setDices;
        this.maxSlot = maxSlot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    public int getAttemptLeft() {
        return this.maxAttempts - this.currentAttempt;
    }

    public void setDice(List<Dice> dices) {
        this.dices = dices;
    }

    public List<Dice> getDices() {
        return this.dices;
    }

    public int getCurrentAttempt() {
        return this.currentAttempt;
    }

    public int getSetDices() {
        return this.setDices;
    }

    public void addDice(Dice dice) {
        this.dices.add(dice);
    }

    public int getSlot() {
        return this.slot;
    }

    public int getMaxSlot() {
        return this.maxSlot;
    }

    /**
     * Due istanze di Disciplina sono uguali se hanno lo stesso nome.
     *
     * @param obj Disciplina da confrontare.
     * @return true se le discipline hanno lo stesso nome, false altrimenti.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Discipline)) {
            return false;
        }
        Discipline o = (Discipline) obj;
        return o.name.equals(this.getName());
    }

    /**
     * Lancia il set di dadi se non si è raggiunto il numero massimo di lanci consentito.
     *
     * @throws SlotException viene creata se si supera il numero massimo di lanci consentito.
     */
    public void roll() throws SlotException {
        if (this.slot + 1 > this.maxSlot) {
            throw new SlotException(SlotException.MAX_SLOT_ERR, "Number of slot exceeded.");
        }
        this.slot++;
        int slot = this.slot;
        if (slot > this.maxSlot) {
            slot = this.maxSlot;
        }

        for (int i = (slot-1) * this.setDices; i < this.setDices * slot; i++) {
            this.dices.get(i).roll();
        }

    }

    /**
     * Effettua un rilancio del set dei dadi lanciato precedentemente {@link #roll()} se non si è raggiunto il numero
     * massimo di rilanci consentito
     *
     * @throws AttemptException viene creata se si supera il numero massimo di rilanci consentito.
     */
    public void reRoll() throws AttemptException {
        if (this.currentAttempt >= this.maxAttempts) {
            throw new AttemptException(AttemptException.MAX_ATTEMPT_ERR, "Max attempt exceeded.");
        }
        int slot = this.slot;
        if (slot > this.maxSlot) {
            slot = this.maxSlot;
        }

        for (int i = (slot-1) * this.setDices; i < this.setDices * slot; i++) {
            this.dices.get(i).roll();
        }
        this.currentAttempt++;
    }

    /**
     *
     * @return Lista di interi con l'ultimo set di dadi lanciato/rilanciato.
     */
    public List<Integer> getSetDicesScore() {
        int slot = this.slot;
        if (slot > this.maxSlot) {
            slot = this.maxSlot;
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i = (slot-1) * this.setDices; i < this.setDices * slot; i++) {
            list.add(this.dices.get(i).getValue());
        }
        return list;
    }

    /**
     *
     * @return Lista di Liste di interi con il penultimo set di dadi lanciato/rilanciato.
     * @throws SlotException viene creata se non esiste un set di dadi lanciato prima di quello corrente.
     */
    public List<List<Integer>> getSetPreviousDices() throws SlotException {
        if (this.slot - 1 == 0) {
            throw new SlotException(SlotException.PREVIOUS_SLOT_ERR, "Previous slot doesn't exist.");
        }
        int slot = this.slot - 1;
        List<List<Integer>> listSlot = new ArrayList<List<Integer>>();
        List<Integer> listDice = new ArrayList<Integer>();
        for (int i = 0; i < slot; i++) {
            for (int k = i * this.setDices; k < this.setDices * (i + 1); k++) {
                listDice.add(this.dices.get(k).getValue());
            }
            listSlot.add(listDice);
        }
        return listSlot;
    }

    /**
     * Imposta i valori di default settando slot, currentAttempt e il valore dei dadi a 0.
     */
    public void reset() {
        this.slot = 0;
        this.currentAttempt = 0;
        this.resetDices();
    }

    private void resetDices() {
        for (Dice dice : this.dices) {
            dice.setValue(0);
        }
    }

    public abstract int getFullScore();

    public abstract int getTotalSetDicesScore();

}