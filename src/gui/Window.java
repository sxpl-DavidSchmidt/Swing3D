package gui;

import util.RenderingPipeline;
import util.Vec3;
import world.Triangle3D;

import javax.swing.*;
import java.awt.*;
import world.Triangle2D;
import util.Vec2;
import world.Triangle4D;
import world.objects.Viereck;

//JComponent gibt bereits Werte für alle Methoden, durch das Erben verändern wir nur die Methoden die wir brauchen und müssen nicht die anderen noch indidividuell befüllen
public class Window extends JComponent {
    //Graphics kann zeichnen
    public void paintComponent(Graphics g) {
        //alles was JComponent kann wird in diese klasser rein getan und kann verändert werden
        super.paintComponent(g);

        Viereck viereck = new Viereck();

        Triangle3D[] triangles = viereck.getTriangles();
        Triangle4D[] trianlges4 = RenderingPipeline.Projection(triangles);
        triangles = RenderingPipeline.perspectiveDivide(trianlges4);


        for(int i=0; i< triangles.length; i++){
            drawTriangle3D(g, triangles[i]);
        }

    }

    public void drawTriangle3D(Graphics g, Triangle3D tri3D){

        double breite = getWidth()/2.0;
        double hoehe = getHeight()/2.0;

        double x1 = (tri3D.V1.x + 1) * breite;
        double y1 = (1 - tri3D.V1.y) * hoehe;

        double x2 = (tri3D.V2.x + 1) * breite;
        double y2 = (1 - tri3D.V2.y) * hoehe;

        double x3 = (tri3D.V3.x + 1) * breite;
        double y3 = (1 - tri3D.V3.y) * hoehe;

        g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        g.drawLine((int) x2, (int) y2, (int) x3, (int) y3);
        g.drawLine((int) x3, (int) y3, (int) x1, (int) y1);

    }
}
