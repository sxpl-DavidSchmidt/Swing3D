package world;

import util.Vec4;

public class Triangle4D {
    public Vec4 V1;
    public Vec4 V2;
    public Vec4 V3;
    //4 vectoren belgen
    public Triangle4D(Vec4 vector1, Vec4 vector2, Vec4 vector3) {
        this.V1 = vector1;
        this.V2 = vector2;
        this.V3 = vector3;
    }
}
