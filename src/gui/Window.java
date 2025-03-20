package gui;

import util.RenderingPipeline;
import world.Triangle3D;

import javax.swing.*;
import java.awt.*;

import world.World;
import world.objects.Object3D;
import world.objects.Orientation;
import world.pathmanagers.PathUtility;

public class Window extends JComponent {
    private static final Color COLOR_BACKGROUND = Color.BLACK;
    private static final Color COLOR_ORIENTATION_OUTLINE = new Color(1.0f, 1.0f, 1.0f, 0.25f);
    private static final Color COLOR_ORIENTATION_FILL = new Color(1.0f, 1.0f, 1.0f, 0.125f);
    private static final Color COLOR_OBJECT_OUTLINE = new Color(1.0f, .0f, 1.0f);
    private static final Color COLOR_OBJECT_FILL = new Color(1.0f, .0f, 1.0f, 0.25f);
    private final Orientation orientation;
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
        for (Object3D object: world.getWorldObjects()) {
            drawObject3D(g, object, COLOR_OBJECT_OUTLINE, COLOR_OBJECT_FILL);
        }

        // draw animation progression line
        int anim_x1 = (int) (getWidth() * 0.25);
        int anim_x2 = (int) (getWidth() * 0.75);
        int anim_x3 = (int) (getWidth() * (0.25+0.5*PathUtility.getSyncedAnimationProgression()));
        int anim_y = (int) (getHeight() * 0.1);

        // draw bg
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(10));
        g2.drawLine(anim_x1, anim_y, anim_x2, anim_y);

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(anim_x1, anim_y, anim_x3, anim_y);
    }

    private void drawObject3D(Graphics g, Object3D object, Color outlineColor, Color fillColor) {
        Triangle3D[] triangles = RenderingPipeline.runPipeline(
                object.getTriangles(),
                world.getCamera(),
                -1,
                -1000,
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
