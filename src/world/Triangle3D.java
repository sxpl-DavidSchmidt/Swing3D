package world;

import util.Vec3;

public class Triangle3D {
    public Vec3 V1;
    public Vec3 V2;
    public Vec3 V3;
    //3 vectoren belgen
    public Triangle3D(Vec3 vector1, Vec3 vector2, Vec3 vector3) {
        this.V1 = vector1;
        this.V2 = vector2;
        this.V3 = vector3;
    }
}
