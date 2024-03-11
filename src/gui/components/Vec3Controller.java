package gui.components;

import util.Vec3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Vec3Controller extends JPanel {
    private final JTextField textFieldX;
    private final JTextField textFieldY;
    private final JTextField textFieldZ;
    private Vec3 vec;

    public Vec3Controller() {
        textFieldX = new JTextField();
        textFieldY = new JTextField();
        textFieldZ = new JTextField();

        ActionListener actionListener = e -> {
            if (vec == null) return;
            vec.x = parseDouble(textFieldX.getText(), vec.x);
            vec.y = parseDouble(textFieldY.getText(), vec.y);
            vec.z = parseDouble(textFieldZ.getText(), vec.z);
        };

        textFieldX.addActionListener(actionListener);
        textFieldY.addActionListener(actionListener);
        textFieldZ.addActionListener(actionListener);

        setLayout(new GridLayout(2, 3));
        add(new JLabel("X"));
        add(new JLabel("Y"));
        add(new JLabel("Z"));
        add(textFieldX);
        add(textFieldY);
        add(textFieldZ);
    }

    public void setVec3(Vec3 vec) {
        this.vec = vec;
    }

    private double parseDouble(String text, double fallback) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException ex) {
            return fallback;
        }
    }
}
