package gui.components;

import world.objects.Object3D;

import javax.swing.*;
import java.awt.*;

public class ObjectController extends JPanel {
    private final Vec3Controller positionController;
    private final Vec3Controller orientationController;

    public ObjectController() {
        positionController = new Vec3Controller();
        orientationController = new Vec3Controller();

        setLayout(new BorderLayout());
        add(new JLabel("Object Controller"), BorderLayout.NORTH);

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new GridLayout(2, 0));
        wrapperPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        wrapperPanel.add(positionController);
        wrapperPanel.add(orientationController);
        add(wrapperPanel, BorderLayout.CENTER);
    }

    public void setObject(Object3D object) {
        positionController.setVec3(object.getPosition());
        orientationController.setVec3(object.getOrientation());
    }
}
