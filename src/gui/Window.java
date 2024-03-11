package gui;

import util.RenderingPipeline;
import util.Vec3;
import world.Triangle3D;

import javax.swing.*;
import java.awt.*;

import world.World;
import world.objects.Object3D;
import world.objects.Orientation;
import world.objects.Pyramid;

public class Window extends JComponent {
    private static final Color COLOR_BACKGROUND = Color.BLACK;
    private static final Color COLOR_ORIENTATION_OUTLINE = new Color(1.0f, 1.0f, 1.0f, 0.25f);
    private static final Color COLOR_ORIENTATION_FILL = new Color(1.0f, 1.0f, 1.0f, 0.125f);
    private static final Color COLOR_OBJECT_OUTLINE = new Color(1.0f, .0f, 1.0f);;
    private static final Color COLOR_OBJECT_FILL = new Color(1.0f, .0f, 1.0f, 0.25f);
    private Orientation orientation;
    private World world;

    public Window() {
        orientation = new Orientation();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawObject3D(g, orientation, COLOR_ORIENTATION_OUTLINE, COLOR_ORIENTATION_FILL);
        if (world == null) {
            return;
        } else {
            for (Object3D object: world.getWorldObjects()) {
                drawObject3D(g, object, COLOR_OBJECT_OUTLINE, COLOR_OBJECT_FILL);
            }
        }
    }

    private void drawObject3D(Graphics g, Object3D object, Color outlineColor, Color fillColor) {
        Triangle3D[] triangles = RenderingPipeline.runPipeline(
                object.getTriangles(),
                world.getCamera(),
                1,
                1000,
                90,
                ((double) getHeight())/getWidth()
        );
        for (Triangle3D triangle : triangles) {
            double width = getWidth() / 2.0;
            double height = getHeight() / 2.0;

            int x1 = (int) ((1 + triangle.v1.x) * width);
            int y1 = (int) ((1 - triangle.v1.y) * height);
            int x2 = (int) ((1 + triangle.v2.x) * width);
            int y2 = (int) ((1 - triangle.v2.y) * height);
            int x3 = (int) ((1 + triangle.v3.x) * width);
            int y3 = (int) ((1 - triangle.v3.y) * height);

            g.setColor(outlineColor);
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x2, y2, x3, y3);
            g.drawLine(x3, y3, x1, y1);

            g.setColor(fillColor);
            g.fillPolygon(
                    new int[]{x1, x2, x3},
                    new int[]{y1, y2, y3},
                    3
            );
        }
    }
}
