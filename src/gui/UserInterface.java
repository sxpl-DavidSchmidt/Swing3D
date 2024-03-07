package gui;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private JFrame frame;
    public UserInterface() {
        frame = new JFrame();
        frame.setVisible(true);
        //die Maße des Bildschirms bekommen
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //Größe und Platzierung des Fensters bestimmen
        frame.setSize(d.width/2, d.height/2);
        frame.setLocation(d.width/4, d.height/4);
        //wenn das Programm schließt endet das Programm
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fügt neue Instans in frame (in frame muss alles drin sein)
        frame.add(new Window());
    }
}
