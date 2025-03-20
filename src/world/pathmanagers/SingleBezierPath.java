package world.pathmanagers;

import util.Vec3;

public class SingleBezierPath implements PathManager {
    private final Vec3 v1;
    private final Vec3 v2;
    private final Vec3 v3;

    public SingleBezierPath(Vec3 startPosition, Vec3 curve, Vec3 endPosition) {
        this.v1 = startPosition;
        this.v2 = curve;
        this.v3 = endPosition;
    }

    @Override
    public Vec3 getCurrentPosition(double t) {
        return v1.scale(
                (1-t)*(1-t)
        ).add(
                v2.scale(2 * t * (1 - t))
        ).add(
                v3.scale(t*t)
        );
    }
}
