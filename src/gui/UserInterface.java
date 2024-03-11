package gui;

import gui.components.JWorldController;
import util.Vec3;
import world.World;
import world.objects.Pyramid;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private JFrame frame;

    public UserInterface() {
        frame = new JFrame();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(d.width/2, d.height/2);
        frame.setLocation(d.width/4, d.height/4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        World world = new World();
        world.add(new Pyramid(new Vec3(1, -1, -15), new Vec3(0, 0, 0), 1));

        Window window = new Window();
        window.setWorld(world);

        JWorldController jwc = new JWorldController();
        jwc.setWorld(world);

        frame.add(jwc, BorderLayout.WEST);
        frame.add(window, BorderLayout.CENTER);
        frame.setVisible(true);

        new Thread(() -> {
            while (true) {
                window.repaint();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {
                    System.out.println("Failed to sleep thread");
                }
            }
        }).start();
    }
}
