package world.pathmanagers;

import util.Vec3;

public class BasicPath implements PathManager {
    public Vec3 position;

    public BasicPath(Vec3 position) {
        this.position = position;
    }

    @Override
    public Vec3 getCurrentPosition(double t) {
        return position;
    }
}
