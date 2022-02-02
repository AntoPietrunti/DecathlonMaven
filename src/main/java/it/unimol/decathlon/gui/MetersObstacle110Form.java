package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.AttemptException;
import it.unimol.decathlon.app.Decathlon;
import it.unimol.decathlon.app.Player;
import it.unimol.decathlon.app.SlotException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MetersObstacle110Form {
    private static final int TIME = 180;
    private JPanel mainPanel;
    private JLabel slot;
    private JLabel reRoll;
    private JTextField reRollTextField;
    private JButton confirmButton;
    private JLabel welcomeLabel;
    private JButton refuteButton;
    private JTextArea localRankingTextArea;
    private JLabel timerLabel;
    private Decathlon decathlon;
    private MetersObstacle110Frame parent;
    private int turns = 0;
    private int time;
    private Timer timer;

    public MetersObstacle110Form(MetersObstacle110Frame parent) {
        this.decathlon = parent.getDecathlon();
        this.parent = parent;
        this.decathlon.setCurrentDiscipline(this.decathlon.getCurrentDiscipline());
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
                        try {
                            decathlon.getCurrentDiscipline().roll();
                            confirmButton.setEnabled(true);
                            parent.showWelcome();
                            showSlot();
                            showReroll();
                        } catch (Exception e) {
                            getErrorForm();
                        }
                    }
                }
            }
        }, 0, 1000);
    }

    private void startGame() {
        this.showLocalRanking();
        try {
            this.decathlon.getCurrentDiscipline().roll();
            this.showSlot();
            this.showReroll();

            this.confirmButton.addActionListener(actionEvent -> {
                try {
                    this.decathlon.getCurrentDiscipline().reRoll();
                    this.showSlot();
                    this.reRoll.setText("Reroll (you will get a malus of " + this.decathlon.getCurrentDiscipline().getCurrentAttempt() +
                            " points)? ");
                    if (this.decathlon.getCurrentDiscipline().getAttemptLeft() == 1) {
                        this.confirmButton.setEnabled(false);
                    }
                } catch (Exception e) {
                    this.getErrorForm();
                }

            });

            this.refuteButton.addActionListener(actionEvent -> {
                this.turns++;
                this.timer.cancel();
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
                    try {
                        this.decathlon.getCurrentDiscipline().roll();
                        this.confirmButton.setEnabled(true);
                        this.parent.showWelcome();
                        this.showSlot();
                        this.showReroll();
                    } catch (Exception e) {
                        this.getErrorForm();
                    }
                }
            });
        } catch (Exception e) {
            this.getErrorForm();
        }
    }

    public void setShowWelcome(String text) {
        this.welcomeLabel.setText(text);
        this.runTimer();
    }


    private void showReroll() {
        this.reRoll.setText("Reroll (you will get a malus of " + this.decathlon.getCurrentDiscipline().getCurrentAttempt() +
                " points)? ");
    }

    private void showSlot() {
        this.slot.setText("Dices: " + this.decathlon.getCurrentDiscipline().getSetDicesScore() + "    (" +
                this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() + " points | " +
                this.decathlon.getCurrentDiscipline().getCurrentAttempt() +
                " malus | total = " + (this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() -
                this.decathlon.getCurrentDiscipline().getCurrentAttempt()) + ")");
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
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
}
