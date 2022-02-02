package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.AttemptException;
import it.unimol.decathlon.app.Decathlon;
import it.unimol.decathlon.app.Player;
import it.unimol.decathlon.app.SlotException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class Meters100Form {
    private static final int TIME = 180;
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JButton freezeButton;
    private JButton reRollButton;
    private JTextArea localRankingTextArea;
    private JLabel reRollLabel;
    private JLabel dicesLabel;
    private JButton nextTurnButton;
    private JLabel timerLabel;
    private Meters100Frame parent;
    private Decathlon decathlon;
    private int turns = 0;
    private Timer timer;
    private int time;

    public Meters100Form(Meters100Frame parent) {
        this.parent = parent;
        this.decathlon = parent.getDecathlon();
        this.decathlon.setCurrentDiscipline(this.decathlon.getCurrentDiscipline());

        this.nextTurnButton.setEnabled(false);
        this.startGame();
    }

    private void runTimer() {
        this.time = TIME;
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(time > 0) {
                    timerLabel.setText("Time left (seconds): " + time);
                    time--;
                } else {
                    turns++;
                    timer.cancel();
                    decathlon.getCurrentPlayer().addScore(0, decathlon.getCurrentDiscipline().getName());
                    showLocalRanking();

                    if (turns == decathlon.getPlayers().size()) {
                        parent.getParent().setPlayersRanking();
                        parent.getParent().enableDiscipline();
                        parent.getParent().setVisible(true);
                        parent.dispose();
                    } else {
                        decathlon.nextTurn();
                        showWelcome();
                        try {
                            decathlon.getCurrentDiscipline().roll();
                            showSlot();
                            showReRollLabel();
                            showLocalRanking();
                            reRollButton.setEnabled(true);
                            freezeButton.setEnabled(true);
                            nextTurnButton.setEnabled(false);
                        } catch (Exception e) {
                            getErrorForm();
                        }
                    }
                }
            }
        }, 0, 1000);
    }

    private void startGame() {
        this.showWelcome();
        try {
            this.decathlon.getCurrentDiscipline().roll();
            this.showSlot();
            this.showReRollLabel();
            this.showLocalRanking();

            this.freezeButton.addActionListener(event -> {
                if (this.decathlon.getCurrentDiscipline().getSlot() < this.decathlon.getCurrentDiscipline().getMaxSlot()) {
                    try {
                        this.decathlon.getCurrentDiscipline().roll();
                        this.showSlot();
                        this.showReRollLabel();
                    } catch (Exception e) {
                        this.getErrorForm();
                    }
                } else {
                    this.showSlot();
                    this.freezeButton.setEnabled(false);
                    this.reRollButton.setEnabled(false);
                    this.nextTurnButton.setEnabled(true);

                }
            });

            this.reRollButton.addActionListener(event -> {
                try {
                    this.decathlon.getCurrentDiscipline().reRoll();
                } catch (Exception e) {
                    this.getErrorForm();
                }

                if (this.decathlon.getCurrentDiscipline().getAttemptLeft() == 0)  {
                    this.reRollButton.setEnabled(false);
                }
                this.showSlot();
                this.showReRollLabel();
            });

            this.nextTurnButton.addActionListener(event -> {
                this.turns++;
                timer.cancel();
                this.decathlon.getCurrentPlayer().addScore(this.decathlon.getCurrentDiscipline().getFullScore(),
                        this.decathlon.getCurrentDiscipline().getName());
                this.showLocalRanking();

                if (this.turns == this.decathlon.getPlayers().size()) {
                    this.parent.getParent().setPlayersRanking();
                    this.parent.getParent().enableDiscipline();
                    this.parent.getParent().setVisible(true);
                    this.parent.dispose();
                } else {
                    this.decathlon.nextTurn();
                    this.showWelcome();
                    try {
                        this.decathlon.getCurrentDiscipline().roll();
                        this.showSlot();
                        this.showReRollLabel();
                        this.showLocalRanking();
                        this.reRollButton.setEnabled(true);
                        this.freezeButton.setEnabled(true);
                        this.nextTurnButton.setEnabled(false);
                    } catch (Exception e) {
                        this.getErrorForm();
                    }
                }
            });
        } catch (Exception e) {
            this.getErrorForm();
        }

    }

    private void showReRollLabel() {
        this.reRollLabel.setText("Tries (" + this.decathlon.getCurrentDiscipline().getAttemptLeft() + " left)?");
    }

    private void showWelcome() {
        this.welcomeLabel.setText(this.decathlon.getCurrentPlayer().getName() + ", Welcome to 100 Meters");
        this.runTimer();
    }

    private void showSlot() {
        String dices = "";
        if(this.decathlon.getCurrentDiscipline().getSlot() > 1) {
            try {
                dices += "Slot " + (this.decathlon.getCurrentDiscipline().getSlot() - 1) + " " +
                        this.decathlon.getCurrentDiscipline().getSetPreviousDices() + " (confirmed) \n";
            } catch (Exception e) {
                this.getErrorForm();
            }
        }
        dices += "Slot " + this.decathlon.getCurrentDiscipline().getSlot() + ": " +
                            this.decathlon.getCurrentDiscipline().getSetDicesScore();
        dices += "(Total = " + this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() + ")";
        this.dicesLabel.setText(dices);
    }

    private void showLocalRanking() {
        String ranking = "";
        List<Player> localRankingPlayers = new ArrayList<Player>(this.decathlon.getPlayers());
        Collections.sort(localRankingPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getDisciplineScore(decathlon.getCurrentDiscipline().getName()) <
                        o2.getDisciplineScore(decathlon.getCurrentDiscipline().getName())) {
                    return 1;
                }
                if (o1.getDisciplineScore(decathlon.getCurrentDiscipline().getName()) >
                        o2.getDisciplineScore(decathlon.getCurrentDiscipline().getName())) {
                    return -1;
                }
                return 0;
            }
        });

        for (Player player : localRankingPlayers) {
            ranking += player.getName() + " got " + player.getDisciplineScore(
                    this.decathlon.getCurrentDiscipline().getName()) + " points to this discipline. \n";
        }
        this.localRankingTextArea.setText(ranking);
    }

    private void getErrorForm() {
        JDialog d = new JDialog(this.parent , "Error", true);
        d.setLayout( new FlowLayout() );
        JButton b = new JButton ("OK");
        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                d.setVisible(false);
            }
        });
        d.add( new JLabel ("Unexpected error."));
        d.add(b);
        d.setSize(300,300);
        d.setVisible(true);
        System.exit(-1);
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
