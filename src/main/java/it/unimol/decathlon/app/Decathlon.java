package it.unimol.decathlon.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Rappresenta il gioco del Decathlon contenente le discipline:
 * Salto in alto,
 * 100 Metri,
 * 400 Metri,
 * 1500 Metri,
 * 110 Metri ad ostacoli.
 * Inoltre contiene la lista dei giocatori istanziati precedentemente.
 *
 * @see it.unimol.decathlon.app.HighJump Salto in alto
 * @see it.unimol.decathlon.app.Meters100 100 Metri
 * @see it.unimol.decathlon.app.Meters400 400 Metri
 * @see it.unimol.decathlon.app.Meters1500 1500 Metri
 * @see it.unimol.decathlon.app.MetersObstacle110 110 Metri ad ostacoli
 *
 * @author Antonio Pietrunti
 * @version 1.0
 */

public class Decathlon implements Serializable {
    private  List<Discipline> disciplines = new ArrayList<Discipline>();
    private List<Player> players = new ArrayList<Player>();
    private Player currentPlayer;
    private  Discipline currentDiscipline;

    /**
     * Crea un'istanza Decathlon impostando i giocatori, istanziando le discipline presenti nel gioco e impostando il
     * giocatore corrente al primo giocatore della lista dei giocatori e la disciplina corrente alla prima disciplina
     * presente nella lista delle discipline.
     *
     * @param players Lista dei giocatori.
     */
    public Decathlon(List<Player> players) {
        this.players = players;
        this.initialize();
        this.start();
    }

    /**
     * Crea un'istanza Decathlon istanziando le discipline presenti nel gioco.
     */
    public Decathlon() {
        this.initialize();
    }

    private void initialize() {
        Discipline meters100 = new Meters100();
        Discipline meters400 = new Meters400();
        Discipline meters1500 = new Meters1500();
        Discipline metersObstacle110 = new MetersObstacle110();
        Discipline highJump = new HighJump();
        this.disciplines.add(meters400);
        this.disciplines.add(meters100);
        this.disciplines.add(meters1500);
        this.disciplines.add(metersObstacle110);
        this.disciplines.add(highJump);
    }

    /**
     * Imposta il giocatore corrente al primo giocatore della lista dei giocatori e la disciplina corrente alla
     * prima disciplina presente nella lista delle discipline.
     */
    public void start() {
        this.currentPlayer = this.players.get(0);
        this.currentDiscipline = this.disciplines.get(0);
    }

    /**
     * Imposta il giocatore corrente al giocatore che si trova nella posizione successiva rispetto a quello attuale
     * nella lista dei giocatori.
     * Nel caso in cui il giocatore corrente è l'ultimo della lista, imposta il giocatore corrente al primo giocatore
     * presente nella lista dei giocatori.
     * Successivamente riporta la disciplina corrente allo stato di default tramite il metodo: {@link Discipline#reset()}.
     *
     */
    public void nextTurn() {
        int indexPlayer = this.players.indexOf(currentPlayer);
        if (indexPlayer + 1 == this.players.size()) {
            indexPlayer = 0;
        } else {
            indexPlayer++;
        }
        this.currentPlayer = this.players.get(indexPlayer);
        this.currentDiscipline.reset();
    }

    /**
     * Modifica in modo casuale la posizione dei giocatori nella lista dei giocatori.
     * Imposta il giocatore corrente al primo elemento della lista dei giocatori.
     * Imposta la disciplina corrente alla disciplina che si trova nella posizione successiva rispetto a quella attuale.
     */
    public void nextDiscipline() {
        Collections.shuffle(this.players);
        this.currentPlayer = this.players.get(0);
        int indexDiscipline = this.disciplines.indexOf(currentDiscipline);
        if (indexDiscipline + 1 != this.disciplines.size()) {
            indexDiscipline++;
        }
        this.currentDiscipline = this.disciplines.get(indexDiscipline);
    }

    /**
     * Lancia il set di dadi della disciplina corrente se non si è raggiunto il numero massimo di lanci consentito.
     *
     * @throws SlotException viene creata se si supera il numero massimo di lanci consentito nella disciplina corrente.
     */
    public void roll() throws SlotException {
        this.currentDiscipline.roll();
    }

    /**
     * Effettua un rilancio del set dei dadi lanciato precedentemente {@link #roll()} se non si è raggiunto il numero
     * massimo di rilanci consentito nella disciplina corrente.
     *
     * @throws AttemptException viene creata se si supera il numero massimo di rilanci consentito nella disciplina
     * corrente.
     */
    public void reRoll() throws AttemptException {
        this.currentDiscipline.reRoll();
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Discipline getCurrentDiscipline() {
        return this.currentDiscipline;
    }

    public List<Discipline> getDisciplines() {
        return this.disciplines;
    }

    public List<Player> getPlayers() {
        return  this.players;
    }

    /**
     * Imposta la lista dei giocatori se non è vuota o nulla.
     *
     * @param players Lista dei giocatori.
     * @throws IllegalArgumentException viene creata se la lista dei giocatori risulta essere nulla o vuota.
     */
    public void setPlayers(List<Player> players) throws IllegalArgumentException {
        if (players == null || players.size() == 0) {
            throw new IllegalArgumentException("Players can not be null or empty");
        }
        this.players = players;
    }

    public void setCurrentDiscipline(Discipline discipline) {
        this.currentDiscipline = discipline;
    }

    /**
     * Dato il nome di una disciplina cerca all'interno della lista delle discipline se è presente.
     *
     * @param name nome della disciplina da cercare.
     * @return oggetto Discipline con lo stesso nome.
     * @throws NoSuchElementException viene creata se il nome della disciplina inserito non corrisponde a nessuna delle
     * discipline presenti nella lista delle discipline.
     */
    public Discipline getDiscipline(String name) throws NoSuchElementException {
        try {
            return this.disciplines.stream().filter(item -> item.getName().equals(name)).
                    collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Discipline not found.");
        }
    }

}
