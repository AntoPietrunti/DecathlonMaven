package it.unimol.decathlon.app;

/**
 * Rappresenta il singolo giocatore del gioco Decathlon.
 *
 * @author Antonio Pietrunti
 * @version 1.0
 */

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class Player implements Serializable {
    private String name;
    private Map<String, Integer> scores = new HashMap<String, Integer>();

    /**
     * Crea un oggetto di tipo Player.
     *
     * @param name una stringa che rappresenta il nome del giocatore.
     */
    public Player(String name) {
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Aggiunge il punteggio di una Disciplina al Player.
     *
     * @param score un intero che rappresenta il punteggio raggiunto in una Disciplina.
     * @param discipline una stringa che rappresenta il nome della Disciplina a cui assegnare il punteggio.
     */
    public void addScore(int score, String discipline) {
        this.scores.put(discipline, score);
    }

    /**
     * Restituisce il punteggio raggiunto in una Disciplina specifica. Se il punteggio di una Disciplina
     * risulta essere uguale a null, verrà restituito il punteggio uguale a 0.
     *
     * @param discipline una stringa che rappresenta il nome della Disciplina di cui si vuole avere il punteggio.
     * @return un intero rappresentante il punteggio della disciplina.
     */
    public int getDisciplineScore(String discipline) {
        Integer score = this.scores.get(discipline);
        return (score != null)? score : 0;
    }

    /**
     * Controlla se una Disciplina è presente nella HashMap delle discipline.
     *
     * @param name una stringa che rappresenta il nome della Disciplina che si vuole cercare.
     * @return un valore booleano che rappresenta l'esistenza (true) della Disciplina nella mappa.
     */
    public boolean isDisciplineExist(String name) {
        return (this.scores.get(name) != null)? true : false;
    }

    /**
     * Calcola il punteggio totale accumulato dal Player in tutte le discipline.
     *
     * @return un valore intero rappresentante il punteggio totale accumulato.
     */
    public int getTotalScore() {
        int points = 0;
        for (Integer i : this.scores.values()) {
            points = points + i;
        }
        return points;
    }

    public Map<String, Integer> getScores() {
        return this.scores;
    }

    /**
     * Azzera il punteggio del Player assegnando una nuova HashMap.
     */
    public void resetScore() {
        this.scores = new HashMap<String, Integer>();
    }
}