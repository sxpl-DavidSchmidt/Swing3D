package world;

import util.Vec4;

public class Triangle4D {
    public Vec4 v1;
    public Vec4 v2;
    public Vec4 v3;

    public Triangle4D(Vec4 vec1, Vec4 vec2, Vec4 vec3) {
        this.v1 = vec1;
        this.v2 = vec2;
        this.v3 = vec3;
    }

    public Vec4[] getVertices() {
        return new Vec4[] {v1, v2, v3};
    }
}
