package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;

public class HighJumpForm {
    private static final int TIME = 180;
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JButton confirmButton;
    private JLabel highLabel;
    private JSpinner highSpinner;
    private JLabel errorLabel;
    private JLabel diceLabel;
    private JLabel reRollLabel;
    private JButton reRollButton;
    private JButton nextTurnButton;
    private JTextArea localRankingtextArea;
    private JLabel timerLabel;
    private Decathlon decathlon;
    private HighJumpFrame parent;
    private int turns = 0;
    private Timer timer;
    private int time;



    public HighJumpForm(HighJumpFrame parent) {
        this.parent = parent;
        this.decathlon = parent.getDecathlon();
        this.decathlon.setCurrentDiscipline(this.decathlon.getCurrentDiscipline());
        this.confirmButton.setEnabled(false);
        this.showWelcome();

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
                        diceLabel.setText("");
                        reRollLabel.setText("");
                        reRollButton.setEnabled(true);
                    }
                }
            }
        }, 0, 1000);
    }

    private void showWelcome() {
        this.welcomeLabel.setText(this.decathlon.getCurrentPlayer().getName() + ", Welcome to High Jump");
        this.runTimer();
    }


    private void startGame() {

        this.showLocalRanking();
        this.highSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner source = (JSpinner) e.getSource();
                if ((((Integer) source.getValue()) < 5) || (((Integer) source.getValue()) > 30)) {
                    errorLabel.setText("The height must be between 5 and 30!");
                    confirmButton.setEnabled(false);
                } else {
                    confirmButton.setEnabled(true);
                    errorLabel.setText("");
                }
            }
        });

        this.confirmButton.addActionListener(actionEvent -> {
            try {
                this.decathlon.getCurrentDiscipline().roll();
                this.confirmButton.setEnabled(false);
                this.showSlot();
                ((HighJump) this.decathlon.getCurrentDiscipline()).setHeight((Integer) this.highSpinner.getValue());
                if (this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() >= ((HighJump) this.decathlon.getCurrentDiscipline()).getHeight()) {
                    this.reRollButton.setEnabled(false);
                    this.confirmButton.setEnabled(false);
                    this.reRollLabel.setText("You scored " + this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() +
                            ": you reached your goal of " + ((HighJump) this.decathlon.getCurrentDiscipline()).getHeight());
                } else {
                    this.reRollLabel.setText("You scored " + this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() +
                            ", you didn't reach your goal of " +
                            ((HighJump) this.decathlon.getCurrentDiscipline()).getHeight());
                }
                this.showSlot();
            } catch (Exception e) {
                this.getErrorForm();
            }
        });

        this.reRollButton.addActionListener(event -> {
            try {
                this.decathlon.getCurrentDiscipline().reRoll();
                if (this.decathlon.getCurrentDiscipline().getCurrentAttempt() >= this.decathlon.getCurrentDiscipline().getMaxAttempts()) {
                    this.reRollButton.setEnabled(false);
                }
                if (this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() >= ((HighJump) this.decathlon.getCurrentDiscipline()).getHeight()) {
                    this.reRollButton.setEnabled(false);
                    this.reRollLabel.setText("You scored " + this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() +
                            ": you reached your goal of " + ((HighJump) this.decathlon.getCurrentDiscipline()).getHeight());
                } else {
                    this.reRollLabel.setText("You scored " + this.decathlon.getCurrentDiscipline().getTotalSetDicesScore() +
                            ", you didn't reach your goal of " +
                            ((HighJump) this.decathlon.getCurrentDiscipline()).getHeight());
                }
                this.showSlot();
            } catch (Exception e) {
                this.getErrorForm();
            }
        });




        this.nextTurnButton.addActionListener(event -> {
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
                this.showWelcome();
                this.diceLabel.setText("");
                this.reRollLabel.setText("");
                this.reRollButton.setEnabled(true);
            }
        });

    }


    private void showSlot() {
        this.diceLabel.setText("Try " + this.decathlon.getCurrentDiscipline().getCurrentAttempt() + ": " +
                this.decathlon.getCurrentDiscipline().getSetDicesScore());
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    private void setShowWelcome(String text) {
        this.welcomeLabel.setText(text);
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
        this.localRankingtextArea.setText(ranking);
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