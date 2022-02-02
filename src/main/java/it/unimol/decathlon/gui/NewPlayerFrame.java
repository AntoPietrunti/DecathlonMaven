package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.Decathlon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NewPlayerFrame extends JFrame {
    private InsertPlayerForm form;
    private Decathlon decathlon;

    public NewPlayerFrame(Decathlon decathlon) {
        super();
        this.decathlon = decathlon;
        initializePanel();

        this.setTitle("Add Players");
        this.setSize(400, 400);
        this.setMinimumSize(new Dimension(400, 400));
        this.setContentPane(this.form.getMainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }



    private void goToMainWindow() {
        try {
            this.decathlon.setPlayers(this.form.getPlayers());
            this.decathlon.start();
            DecathlonFrame decathlonFrame = new DecathlonFrame(this.decathlon);
            decathlonFrame.setVisible(true);
            this.dispose();
        } catch (IllegalArgumentException e) {
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
            d.add( new JLabel (e.getMessage()));
            d.add(b);
            d.setSize(300,300);
            d.setVisible(true);
        }
    }

    private void initializePanel() {
        this.form = new InsertPlayerForm();
        this.form.getOkButton().addActionListener(actionEvent -> goToMainWindow());
    }
}
