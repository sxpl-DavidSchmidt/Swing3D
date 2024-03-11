package world.objects;

import util.Vec3;
import world.Triangle3D;

public class Pyramid extends Object3D {
    public Pyramid(Vec3 position, Vec3 orientation, double size) {
        super(position, orientation, size);
    }

    public Pyramid() {
        super();
    }

    @Override
    protected void initializeLocalSpaceTriangles() {
        Vec3 b1 = new Vec3(-1, 0, -1);  // LV
        Vec3 b2 = new Vec3(-1, 0, 1);   // LH
        Vec3 b3 = new Vec3(1, 0, -1);   // RV
        Vec3 b4 = new Vec3(1, 0, 1);    // RH
        Vec3 m  = new Vec3(0, 1, 0);    // M

        this.localSpaceTriangles = new Triangle3D[] {
                new Triangle3D(b1, b2, b4),
                new Triangle3D(b3, b4, b1),
                new Triangle3D(b1, b2, m),
                new Triangle3D(b1, b3, m),
                new Triangle3D(b2, b4, m),
                new Triangle3D(b3, b4, m)
        };
    }

    @Override
    public Vec3 getPosition() {
        return new Vec3(position.x, position.y, -(5*(System.currentTimeMillis() % 2000)/2000.0+5));
    }
}