package util;

public class Vec4 {
    public double x;
    public double y;
    public double z;
    public double w;

    public Vec4(double x,double y,double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Vec4 interpolateLine (Vec4 v1, Vec4 v2, double t) {
        return new Vec4(
                v1.x + (v2.x - v1.x) * t,
                v1.y + (v2.y - v1.y) * t,
                v1.z + (v2.z - v1.z) * t,
                v1.w + (v2.w - v1.w) * t
        );
    }

    public String toString() {
        return x + " " + y + " " + z + " " + w;
    }
}
