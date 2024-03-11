package gui.components;

import world.Camera;

import javax.swing.*;
import java.awt.*;

public class CameraController extends JPanel {
    private JVec3Controller positionController;
    private JVec3Controller orientationController;

    public CameraController() {
        positionController = new JVec3Controller();
        orientationController = new JVec3Controller();

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());
        add(new JLabel("Camera Controller"), BorderLayout.NORTH);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new GridLayout(2, 1));

        JPanel positionPanel = new JPanel();
        positionPanel.setLayout(new BorderLayout());
        positionPanel.add(new JLabel("Position"), BorderLayout.WEST);
        positionPanel.add(positionController, BorderLayout.CENTER);

        JPanel orientationPanel = new JPanel();
        orientationPanel.setLayout(new BorderLayout());
        orientationPanel.add(new JLabel("Orientation"), BorderLayout.WEST);
        orientationPanel.add(orientationController, BorderLayout.CENTER);

        wrapperPanel.add(positionPanel);
        wrapperPanel.add(orientationPanel);
        add(wrapperPanel);
    }

    public void setCamera(Camera camera) {
        positionController.setVec3(camera.getPosition());
        orientationController.setVec3(camera.getOrientation());
    }
}
