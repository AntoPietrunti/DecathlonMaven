package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.Decathlon;
import it.unimol.decathlon.app.Discipline;
import it.unimol.decathlon.app.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.List;


public class DecathlonFrame extends JFrame {
    private Decathlon decathlon;
    private ShowDisciplines form;

    public DecathlonFrame(Decathlon decathlon) {
        this.decathlon = decathlon;
        initializeFrame();
        this.setPlayersRanking();
    }

    private void initializeFrame() {
        this.setTitle("Decathlon");
        this.setSize(400, 400);
        this.setMinimumSize(new Dimension(400, 400));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                onExit();
            }
        });
        this.pack();

        this.form = new ShowDisciplines();
        this.setContentPane(this.form.getMainPanel());
        this.setVisible(true);

        this.enableDiscipline();


        this.form.getHighJumpButton().addActionListener(actionEvent -> {
            goToHighJump();
        });

        this.form.getMeters100Button().addActionListener(actionEvent -> {
            goToMeters100();
        });

        this.form.getMeters400Button().addActionListener(actionEvent -> {
            goToMeters400();
        });

        this.form.getMeters1500Button().addActionListener(actionEvent -> {
            goToMeters1500();
        });

        this.form.getMetersObstacle110Button().addActionListener(actionEvent -> {
            goToMetersObstacle110();
        });

        this.form.getExitButton().addActionListener(actionEvent -> this.onExit());


        this.form.getRestartButton().addActionListener(actionEvent -> {
            for (Player player : this.decathlon.getPlayers()) {
                player.resetScore();
            }
            this.enableDiscipline();
            this.setPlayersRanking();
            this.form.getRestartButton().setEnabled(false);
        });
        this.form.getRestartButton().setEnabled(false);

    }

    private void onExit() {
        if (isGameCompleted()) {
            try {
                File deSer = new File("decathlon.ser");
                deSer.delete();
            } catch (Exception e) {}
        } else {
            try (FileOutputStream fileOut = new FileOutputStream("decathlon.ser");
                 ObjectOutputStream out = new ObjectOutputStream(fileOut);) {
                out.writeObject(this.decathlon);
            } catch (Exception e) {
                JDialog d = new JDialog(this , "Error", true);
                d.setLayout( new FlowLayout() );
                JButton b = new JButton ("OK");
                b.addActionListener ( new ActionListener()
                {
                    public void actionPerformed( ActionEvent e )
                    {
                        d.setVisible(false);
                    }
                });
                d.add( new JLabel ("Impossible to save the game."));
                d.add(b);
                d.setSize(300,300);
                d.setVisible(true);
            }
        }
        System.exit(0);
    }

    private void getErrorForm() {
        JDialog d = new JDialog(this , "Error", true);
        d.setLayout( new FlowLayout() );
        JButton b = new JButton ("OK");
        b.addActionListener ( new ActionListener()
        {
            public void actionPerformed( ActionEvent e )
            {
                d.setVisible(false);
            }
        });
        d.add( new JLabel ("Unexpected error. Discipline not found."));
        d.add(b);
        d.setSize(300,300);
        d.setVisible(true);
    }

    private void goToMetersObstacle110() {
        MetersObstacle110Frame metersObstacle110 = new MetersObstacle110Frame(this);
        metersObstacle110.setVisible(true);
        this.dispose();
    }

    private void goToMeters1500() {
        Meters1500Frame meters1500Frame = new Meters1500Frame(this);
        meters1500Frame.setVisible(true);
        this.dispose();
    }

    private void goToMeters400() {
        Meters400Frame meters400Frame = new Meters400Frame(this);
        meters400Frame.setVisible(true);
        this.dispose();
    }

    private void goToMeters100() {
        Meters100Frame meters100Frame = new Meters100Frame(this);
        meters100Frame.setVisible(true);
        this.dispose();
    }

    private void goToHighJump() {
        HighJumpFrame highJumpFrame = new HighJumpFrame(this);
        highJumpFrame.setVisible(true);
        this.dispose();
    }

    public void enableDiscipline() {
        try {
            if (this.isDisciplineCompleted(this.decathlon.getDiscipline("High Jump"))) {
                this.form.getHighJumpButton().setEnabled(false);
            } else {
                this.form.getHighJumpButton().setEnabled(true);
            }

            if (this.isDisciplineCompleted(this.decathlon.getDiscipline("100 Meters"))) {
                this.form.getMeters100Button().setEnabled(false);
            } else {
                this.form.getMeters100Button().setEnabled(true);
            }

            if (this.isDisciplineCompleted(this.decathlon.getDiscipline("400 Meters"))) {
                this.form.getMeters400Button().setEnabled(false);
            } else {
                this.form.getMeters400Button().setEnabled(true);
            }

            if (this.isDisciplineCompleted(this.decathlon.getDiscipline("1500 Meters"))) {
                this.form.getMeters1500Button().setEnabled(false);
            } else {
                this.form.getMeters1500Button().setEnabled(true);
            }

            if (this.isDisciplineCompleted(this.decathlon.getDiscipline("110 Meters Obstacle"))) {
                this.form.getMetersObstacle110Button().setEnabled(false);
            } else {
                this.form.getMetersObstacle110Button().setEnabled(true);
            }
        } catch (NoSuchElementException e) {
            this.getErrorForm();
            System.exit(-1);
        }
    }

    private boolean isGameCompleted() {
        boolean isCompleted = true;
        int disciplieNumber = this.decathlon.getDisciplines().size();
        for (Player player : this.decathlon.getPlayers()) {
            if (player.getScores().size() != disciplieNumber) {
                return false;
            }
        }
        return isCompleted;
    }

    private String getPlayersRanking() {
        String ranking = "";
        List<Player> rankingPlayers = new ArrayList<Player>(this.decathlon.getPlayers());
        Collections.sort(rankingPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getTotalScore() < o2.getTotalScore()) {
                    return 1;
                }
                if (o1.getTotalScore() > o2.getTotalScore()) {
                    return -1;
                }
                return 0;
            }
        });

        if (isGameCompleted()) {
            ranking += "The Game is completed \n\n " + "THE WINNER IS: " +
                        rankingPlayers.get(0).getName() + "\n\n" + "Classifica finale: \n";
            for (Player player : rankingPlayers) {
             ranking += player.getName() + " (" + player.getTotalScore() + ") \n";
            }
            this.form.getRestartButton().setEnabled(true);
        } else {
            for (Player player : rankingPlayers) {
                ranking += player.getName() + " (" + player.getTotalScore() + ") \n";
            }
        }
        return ranking;
    }

    private boolean isDisciplineCompleted(Discipline discipline) {
        boolean isCompleted = true;
        for (Player player : this.decathlon.getPlayers()) {
            if (!(player.isDisciplineExist(discipline.getName()))) {
                isCompleted = false;
                break;
            }
        }
        return isCompleted;
    }

    public void setPlayersRanking() {
        this.form.setRankingTextArea(this.getPlayersRanking());
    }

    public Decathlon getDecathlon() {
        return this.decathlon;
    }


}
