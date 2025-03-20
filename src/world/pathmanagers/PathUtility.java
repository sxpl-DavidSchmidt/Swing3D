package world.pathmanagers;

public class PathUtility {
    public static double getSyncedAnimationProgression () {
        return System.currentTimeMillis() % 10000 / 10000.0;
    }
}
