package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.Decathlon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;


public class MetersObstacle110Frame extends JFrame {
    private MetersObstacle110Form form;
    private Decathlon decathlon;
    DecathlonFrame parent;


    public MetersObstacle110Frame(DecathlonFrame parent) {
        super();
        this.decathlon = parent.getDecathlon();
        this.parent = parent;
        try {
            this.decathlon.setCurrentDiscipline(this.decathlon.getDiscipline("110 Meters Obstacle"));
        } catch (NoSuchElementException e) {
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
            System.exit(-1);
        }

        initializePanel();

        this.setTitle("MetersObstacle110 Game");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    private void initializePanel() {
        this.form  = new MetersObstacle110Form(this);
        this.setContentPane(this.form.getMainPanel());
        this.setVisible(true);
        this.showWelcome();
    }

    public void showWelcome() {
        this.form.setShowWelcome(this.decathlon.getCurrentPlayer().getName() + ", Welcome to 110 Meters Obastacle");
    }



    public Decathlon getDecathlon() {
        return this.decathlon;
    }

    public DecathlonFrame getParent() {
        return this.parent;
    }

}
