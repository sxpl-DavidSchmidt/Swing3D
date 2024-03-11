package gui;

import gui.components.WorldController;
import util.Vec3;
import world.World;
import world.objects.Cube;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    public UserInterface() {
        Window window = new Window();
        WorldController worldController = new WorldController();

        World world = new World();
        Cube c = new Cube(new Vec3(0, 3, -30), new Vec3(0, 0, 0), 5);
        world.add(c);

        window.setWorld(world);
        worldController.setWorld(world);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setSize(d.width/2, d.height/2);
        frame.setLocation(d.width/4, d.height/4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(worldController, BorderLayout.WEST);
        frame.add(window, BorderLayout.CENTER);
        frame.setVisible(true);

        new Thread(() -> {
            while (true) {
                window.repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    System.out.println("Failed to sleep thread");
                }
            }
        }).start();
    }
}
