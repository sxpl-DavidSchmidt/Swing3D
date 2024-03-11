package gui.components;

import world.World;

import javax.swing.*;
import java.awt.*;

public class WorldController extends JPanel {
    private final CameraController cameraController;
    private final ObjectController objectController;
    private final ObjectSelector objectSelector;

    public WorldController() {
        cameraController = new CameraController();
        objectController = new ObjectController();
        objectSelector = new ObjectSelector();
        objectSelector.setObjectController(objectController);

        setLayout(new GridLayout(3, 0));
        add(objectSelector);
        add(objectController);
        add(cameraController);
    }

    public void setWorld(World world) {
        this.objectSelector.setWorld(world);
        this.cameraController.setCamera(world.getCamera());
    }
}
