package world;

import util.Vec3;
import world.pathmanagers.BasicPath;
import world.pathmanagers.PathManager;
import world.pathmanagers.PathUtility;

public class Camera {
    private final PathManager positionManager;
    private final PathManager orientationManager;

    public Camera(Vec3 position, Vec3 orientation) {
        this(new BasicPath(position), new BasicPath(orientation));
    }

    public Camera(PathManager positionManager, PathManager orientationManager) {
        this.positionManager = positionManager;
        this.orientationManager = orientationManager;
    }

    public Vec3 getPosition() {
        return positionManager.getCurrentPosition(
                PathUtility.getSyncedAnimationProgression()
        );
    }

    public Vec3 getOrientation() {
        return orientationManager.getCurrentPosition(
                PathUtility.getSyncedAnimationProgression()
        );
    }
}
