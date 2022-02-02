package it.unimol.decathlon.gui;

import javax.swing.*;

public class ShowDisciplines {
    private JButton highJumpButton;
    private JButton meters100Button;
    private JButton meters400Button;
    private JButton meters1500Button;
    private JButton metersObstacle110Button;
    private JLabel rankingField;
    private JLabel disciplinesField;
    private JPanel mainPanel;
    private JTextArea rankingTextArea;
    private JButton restartButton;
    private JButton exitButton;
    private JLabel rankingLabel;

    public void setRankingTextArea(String text) {
        this.rankingTextArea.setText(text);
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JButton getHighJumpButton() {
        return this.highJumpButton;
    }

    public JButton getMeters100Button() {
        return this.meters100Button;
    }

    public JButton getMeters400Button() {
        return this.meters400Button;
    }

    public JButton getMeters1500Button() {
        return this.meters1500Button;
    }

    public JButton getMetersObstacle110Button() {
        return this.metersObstacle110Button;
    }

    public JButton getExitButton() {
        return this.exitButton;
    }

    public JButton getRestartButton() {
        return this.restartButton;
    }
}
