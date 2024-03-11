package world;

import util.Vec3;

public class Camera {
    private Vec3 position;
    private Vec3 orientation;

    public Camera(Vec3 position, Vec3 orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Camera() {
        this(new Vec3(0, 0, 0), new Vec3(0, 0, 0));
    }

    public Vec3 getPosition() {
        return position;
    }

    public Vec3 getOrientation() {
        return orientation;
    }
}
