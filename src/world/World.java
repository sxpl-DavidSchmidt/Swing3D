package world;

import util.Vec3;
import world.objects.Object3D;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final List<Object3D> worldObjects;
    private final Camera camera;

    public World() {
        worldObjects = new ArrayList<>();
        camera = new Camera(new Vec3(0, 10, 0), new Vec3(0, 0, 0));
    }

    public void add(Object3D object3D) {
        worldObjects.add(object3D);
    }

    public List<Object3D> getWorldObjects() {
        return worldObjects;
    }

    public Camera getCamera() {
        return camera;
    }
}
