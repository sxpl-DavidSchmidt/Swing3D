package world.objects;

import util.Vec3;
import world.Triangle3D;

// alle objecte können das hier
public interface Object3D {
    public Triangle3D[] getTriangles();

    public Vec3 getOrigin();

    public Vec3 getOrientation();

    public double getSize();
}
