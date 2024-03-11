package gui.components;

import util.Vec3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JVec3Controller extends JTextField {
    private Vec3 vec;

    public JVec3Controller() {
        super();
        addActionListener(e -> {
            if (vec == null) return;
            String[] split = getText().split(",");
            if (split.length != 3) return;
            double x, y, z;
            try {
                x = Double.parseDouble(split[0]);
                y = Double.parseDouble(split[1]);
                z = Double.parseDouble(split[2]);
            } catch (NumberFormatException ex) {
                x = vec.x;
                y = vec.y;
                z = vec.z;
            }

            vec.x = x;
            vec.y = y;
            vec.z = z;
        });
    }

    public void setVec3(Vec3 vec) {
        this.vec = vec;
    }
}
