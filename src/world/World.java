package world;

import util.Vec3;
import world.objects.Object3D;
import world.objects.Orientation;
import world.objects.Pyramid;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Object3D> worldObjects;
    private Camera camera;

    public World() {
        worldObjects = new ArrayList<>();
        camera = new Camera();
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
