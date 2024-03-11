package world;

import util.Vec3;

public class Triangle3D {
    public Vec3 v1;
    public Vec3 v2;
    public Vec3 v3;

    public Triangle3D(Vec3 vec1, Vec3 vec2, Vec3 vec3) {
        this.v1 = vec1;
        this.v2 = vec2;
        this.v3 = vec3;
    }

    public Vec3[] getVertices() {
        return new Vec3[] {v1, v2, v3};
    }
}
