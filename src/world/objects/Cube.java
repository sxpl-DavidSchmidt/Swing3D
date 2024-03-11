package world.objects;

import util.RenderingPipeline;
import util.Vec3;
import world.Triangle3D;

public class Cube extends Object3D {
    public Cube(Vec3 position, Vec3 orientation, double size) {
        super(position, orientation, size);
    }

    public Cube() {
        super();
    }

    @Override
    protected void initializeLocalSpaceTriangles() {
        Vec3 v0 = new Vec3(-1, -1, -1); // VLU
        Vec3 v1 = new Vec3(1, -1, -1);  // VRU
        Vec3 v2 = new Vec3(-1, 1, -1);  // VLO
        Vec3 v3 = new Vec3(1, 1, -1);   // VRO

        Vec3 v4 = new Vec3(-1, -1, 1);  // HLU
        Vec3 v5 = new Vec3(1, -1, 1);   // HRU
        Vec3 v6 = new Vec3(-1, 1, 1);   // HLO
        Vec3 v7 = new Vec3(1, 1, 1);    // HRO

        localSpaceTriangles = new Triangle3D[] {
                // Back
                new Triangle3D(v0, v1, v3),
                new Triangle3D(v0, v1, v2)
        };
    }
}
