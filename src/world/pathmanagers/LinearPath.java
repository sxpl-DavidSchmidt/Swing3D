package world.pathmanagers;

import util.Vec3;

public class LinearPath implements PathManager {
    private final Vec3 startVector;
    private final Vec3 endVector;

    public LinearPath(Vec3 startVector, Vec3 endVector) {
        this.startVector = startVector;
        this.endVector = endVector;
    }

    @Override
    public Vec3 getCurrentPosition(double t) {
        return new Vec3(
                startVector.x + (endVector.x - startVector.x) * t,
                startVector.y + (endVector.y - startVector.y) * t,
                startVector.z + (endVector.z - startVector.z) * t
        );
    }
}
