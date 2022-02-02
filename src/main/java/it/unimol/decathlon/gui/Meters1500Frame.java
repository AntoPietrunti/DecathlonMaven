package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.Decathlon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class Meters1500Frame extends JFrame {
    private Meters1500Form form;
    private Decathlon decathlon;
    private DecathlonFrame parent;

    public Meters1500Frame(DecathlonFrame parent) {
        this.decathlon = parent.getDecathlon();
        this.parent = parent;
        try {
            this.decathlon.setCurrentDiscipline(this.decathlon.getDiscipline("1500 Meters"));
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

        this.initialize();

        this.setTitle("100 Meters Game");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

    private void initialize() {
        this.form  = new Meters1500Form(this);
        this.setContentPane(this.form.getMainPanel());
        this.setVisible(true);
    }

    public Decathlon getDecathlon() {
        return this.decathlon;
    }

    public DecathlonFrame getParent() {
        return this.parent;
    }
}
