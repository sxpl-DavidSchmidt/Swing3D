package gui.components;

import world.World;

import javax.swing.*;
import java.awt.*;

public class JWorldController extends JPanel {
    private World world;
    private CameraController cameraController;

    public JWorldController() {
        cameraController = new CameraController();

        setLayout(new GridLayout(2, 0));
        add(new JLabel("World Controller"));
        add(cameraController);
    }

    public void setWorld(World world) {
        this.world = world;
        cameraController.setCamera(world.getCamera());
    }
}
