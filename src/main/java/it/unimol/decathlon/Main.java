package it.unimol.decathlon;

import it.unimol.decathlon.app.Decathlon;
import it.unimol.decathlon.gui.DecathlonFrame;
import it.unimol.decathlon.gui.NewPlayerFrame;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Main {

    public static void main(String[] args) {
        Decathlon decathlon = null;
        try (FileInputStream fileIn = new FileInputStream("decathlon.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);){
            decathlon = (Decathlon) in.readObject();
            DecathlonFrame frame = new DecathlonFrame(decathlon);
            frame.setVisible(true);
        } catch (Exception i) {
            decathlon = new Decathlon();
            NewPlayerFrame frame = new NewPlayerFrame(decathlon);
            frame.setVisible(true);
        }
    }
}