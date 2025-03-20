package gui;

import util.Vec3;
import world.Camera;
import world.World;
import world.objects.Cube;
import world.pathmanagers.LinearPath;
import world.pathmanagers.SingleBezierPath;

import javax.swing.*;
import java.awt.*;

public class UserInterface {
    public UserInterface() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Window window = new Window();
        window.setWorld(getWorld());

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame();
        frame.setSize(d.width/2, d.height/2);
        frame.setLocation(d.width/4, d.height/4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(window);
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

    private static World getWorld() {
        World world = new World();

        world.setCamera(
                new Camera(
                        new SingleBezierPath(
                                new Vec3(-20, 10, 0),
                                new Vec3(-20, 10, 20),
                                new Vec3(0, 10, 20)),
                        new LinearPath(
                                new Vec3(0, -0.5, 0),
                                new Vec3(0, 0, 0))
                )
        );

        Cube c = new Cube(
                new Vec3(0, 1, 0),
                new Vec3(0, 0.5, 0),
                1
        );

        world.add(c);
        return world;
    }
}
