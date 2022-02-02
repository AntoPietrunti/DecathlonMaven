package it.unimol.decathlon.ui;

import it.unimol.decathlon.app.*;


import java.util.*;


public class DecathlonUI {
    private Decathlon decathlon;

    public DecathlonUI() {
        this.decathlon = new Decathlon(this.getPlayers());
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        System.out.println("Quanti giocatori ci sono? ");
        int numberOfPlayers = this.inputNumber();


        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Inserisci il nome del giocatore " + (i + 1));
            String name = inputString();

            Player player = new Player(name);
            players.add(player);
        }
        return players;
    }

    public void showDiscplines() {
        int index = 0;
        for (Discipline discipline : this.decathlon.getDisciplines()) {
            if (discipline.equals(this.decathlon.getCurrentDiscipline())) {
                System.out.println("* " + discipline.getName());
            } else if (index < this.decathlon.getDisciplines().indexOf(this.decathlon.getCurrentDiscipline())) {
                System.out.println("# " + discipline.getName());
            } else {
                System.out.println(discipline.getName());
            }
            index++;
        }
    }

    public void showFinalScreen() {
        System.out.println("\n\n\n IL GIOCO È TERMINATO! \n\n CLASSIFICA FINALE:");
        this.showRanking();
    }

    public void showRanking() {
        for (Player player : this.decathlon.getPlayers()) {
            System.out.println(player.getName() + " (" + player.getTotalScore() + ")");
        }
    }

    public void showMainScreen() {
        System.out.println("Discipline:");
        this.showDiscplines();
        System.out.println("\n\n\nClassifica:");
        this.showRanking();
        System.out.println("[Premi invio per iniziare il prossimo gioco]");
        this.inputString();
    }

    public void startGame() {
        for (Discipline discipline : this.decathlon.getDisciplines()) {
            if (this.decathlon.getCurrentDiscipline() instanceof Meters400) {
                for (Player player : this.decathlon.getPlayers()) {
                    this.showTurn();
                    DisciplineUI disciplineUI = new Meters400UI(this.decathlon.getCurrentDiscipline());
                    disciplineUI.startGame();
                    this.decathlon.getCurrentPlayer().addScore(this.decathlon.getCurrentDiscipline().getFullScore(),
                            this.decathlon.getCurrentDiscipline().getName());
                    this.showDisciplineTotalScore();
                    this.decathlon.nextTurn();
                }
                this.showDisciplineRanking();
            } else if (this.decathlon.getCurrentDiscipline() instanceof Meters100) {
                this.showMainScreen();
                for (Player player : this.decathlon.getPlayers()) {
                    this.showTurn();
                    DisciplineUI disciplineUI = new Meters100UI(this.decathlon.getCurrentDiscipline());
                    disciplineUI.startGame();
                    this.decathlon.getCurrentPlayer().addScore(this.decathlon.getCurrentDiscipline().getFullScore(),
                            this.decathlon.getCurrentDiscipline().getName());
                    this.showDisciplineTotalScore();
                    this.decathlon.nextTurn();
                }
                this.showDisciplineRanking();
            } else if (this.decathlon.getCurrentDiscipline() instanceof Meters1500) {
                this.showMainScreen();
                for (Player player : this.decathlon.getPlayers()) {
                    this.showTurn();
                    DisciplineUI disciplineUI = new Meters1500UI(this.decathlon.getCurrentDiscipline());
                    disciplineUI.startGame();
                    this.decathlon.getCurrentPlayer().addScore(this.decathlon.getCurrentDiscipline().getFullScore(),
                            this.decathlon.getCurrentDiscipline().getName());
                    this.showDisciplineTotalScore();
                    this.decathlon.nextTurn();
                }
                this.showDisciplineRanking();
            } else if (this.decathlon.getCurrentDiscipline() instanceof MetersObstacle110) {
                this.showMainScreen();
                for (Player player : this.decathlon.getPlayers()) {
                    this.showTurn();
                    DisciplineUI disciplineUI = new MetersObstacle110UI(this.decathlon.getCurrentDiscipline());
                    disciplineUI.startGame();
                    this.decathlon.getCurrentPlayer().addScore(this.decathlon.getCurrentDiscipline().getFullScore(),
                            this.decathlon.getCurrentDiscipline().getName());
                    this.showDisciplineTotalScore();
                    this.decathlon.nextTurn();
                }
                this.showDisciplineRanking();
            } else if (this.decathlon.getCurrentDiscipline() instanceof HighJump) {
                this.showMainScreen();
                for (Player player : this.decathlon.getPlayers()) {

                    this.showTurn();
                    DisciplineUI disciplineUI = new HighJumpUI(this.decathlon.getCurrentDiscipline());
                    boolean bool = false;
                    while (!bool) {
                        System.out.println("Che altezza vuoi raggiungere? (tra 5 e 30)");
                        bool = ((HighJump) this.decathlon.getCurrentDiscipline()).setHeight(this.inputNumber());
                    }
                    disciplineUI.startGame();
                    this.decathlon.getCurrentPlayer().addScore(this.decathlon.getCurrentDiscipline().getFullScore(),
                            this.decathlon.getCurrentDiscipline().getName());
                    this.showDisciplineTotalScore();
                    this.decathlon.nextTurn();
                }
                this.showDisciplineRanking();
            }
            this.decathlon.nextDiscipline();
        }
        this.showFinalScreen();
    }

    public void showTurn() {
        System.out.println("È il turno di " + this.decathlon.getCurrentPlayer().getName());
    }

    private void showDisciplineTotalScore() {
        System.out.println(this.decathlon.getCurrentPlayer().getName() + " ha cumulato " +
                this.decathlon.getCurrentPlayer().getDisciplineScore(this.decathlon.getCurrentDiscipline().getName()) +
                " punti a questa disciplina.");
    }

    private void showDisciplineRanking() {
        for (Player player : this.decathlon.getPlayers()) {
            System.out.println(player.getName()+ " " +
                    player.getDisciplineScore(this.decathlon.getCurrentDiscipline().getName()));
        }
    }



    private int inputNumber() {
        return new Scanner(System.in).nextInt();
    }

    private String inputString() {
        return new Scanner(System.in).nextLine();
    }
}
