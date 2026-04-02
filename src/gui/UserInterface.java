package gui;

import util.Vec3;
import world.Camera;
import world.World;
import world.objects.Cube;
import world.pathmanagers.LinearPath;
import world.pathmanagers.SingleBezierPath;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(
                window::repaint,
                0,
                1000 / 60,
                TimeUnit.MILLISECONDS
        );
    }

    private static World getWorld() {
        World world = new World();

        world.setCamera(
                new Camera(
                        new SingleBezierPath(
                                new Vec3(-20, 7.5, 0),
                                new Vec3(-20, 7.5, 20),
                                new Vec3(0, 7.5, 20)),
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
