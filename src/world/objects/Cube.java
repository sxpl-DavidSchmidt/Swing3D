package world.objects;

import util.Vec3;
import world.Triangle3D;

public class Cube extends Object3D {
    public Cube(Vec3 position, Vec3 orientation, double size) {
        super(position, orientation, size);
    }

    @Override
    protected void initializeLocalSpaceTriangles() {
        Vec3 v1 = new Vec3(-1, -1, -1);
        Vec3 v2 = new Vec3(1, -1, -1);
        Vec3 v3 = new Vec3(-1, 1, -1);
        Vec3 v4 = new Vec3(1, 1, -1);
        Vec3 v5 = new Vec3(-1, -1, 1);
        Vec3 v6 = new Vec3(1, -1, 1);
        Vec3 v7 = new Vec3(-1, 1, 1);
        Vec3 v8 = new Vec3(1, 1, 1);

        localSpaceTriangles = new Triangle3D[] {
                new Triangle3D(v1, v2, v4),
                new Triangle3D(v1, v3, v4),
                new Triangle3D(v5, v6, v7),
                new Triangle3D(v6, v7, v8),
                new Triangle3D(v1, v3, v5),
                new Triangle3D(v3, v5, v7),
                new Triangle3D(v2, v6, v8),
                new Triangle3D(v2, v4, v8),
                new Triangle3D(v4, v3, v7),
                new Triangle3D(v4, v7, v8),
                new Triangle3D(v1, v5, v6),
                new Triangle3D(v1, v2, v6),
        };
    }
}
