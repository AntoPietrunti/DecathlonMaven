package it.unimol.decathlon.gui;

import it.unimol.decathlon.app.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InsertPlayerForm {
    private JPanel mainPanel;
    private JTextField playerTextField;
    private List<Player> players = new ArrayList<>();

    private JButton okButton;
    private JButton exitButton;
    private JButton addPlayerButton;
    private JLabel playerLabel;
    private JLabel errorLabel;

    public InsertPlayerForm() {
        this.okButton.setEnabled(false);
        this.exitButton.addActionListener(event -> System.exit(0));
        this.addPlayerButton.addActionListener(event -> {
            if (playerTextField.getText().strip().length() > 0){
                this.players.add(createPlayer(this.playerTextField));
                this.clean();
                this.okButton.setEnabled(true);
                this.playerLabel.setText("PLAYER " + (this.players.size()+1));
            } else {
                this.errorLabel.setText("The field can not be empty");
            };
        });
    }


    private Player createPlayer(JTextField textField) {
        return new Player(textField.getText());
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JButton getOkButton() {
        return this.okButton;
    }

    private void clean() {
        this.errorLabel.setText("");
        this.playerTextField.setText("");
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
