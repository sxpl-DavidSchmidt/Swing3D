package world.objects;

import util.Vec3;
import world.Triangle3D;

import java.util.LinkedList;
import java.util.List;

public class Orientation extends Object3D {
    public Orientation() {
        super(new Vec3(0, 0, 0), new Vec3(0, 0, 0), 1);
    }

    @Override
    protected void initializeLocalSpaceTriangles() {
        List<Triangle3D> triangles = new LinkedList<>();
        int size = 50;
        double scale = 4;

        Vec3 xOff = new Vec3(scale, 0, 0);
        Vec3 yOff = new Vec3(0, 0, scale);
        Vec3 xyOff = new Vec3(scale, 0, scale);

        for(int i = -size/2; i < size/2; i++) {
            for (int j = -size/2; j < size/2; j++) {
                Vec3 origin = new Vec3(i * scale, 0, j * scale);
                Vec3 originXOff = origin.add(xOff);
                Vec3 originYOff = origin.add(yOff);
                Vec3 originXYOff = origin.add(xyOff);

                triangles.add(new Triangle3D(origin, originXOff, originYOff));
                triangles.add(new Triangle3D(originXYOff, originXOff, originYOff));
            }
        }

        localSpaceTriangles = triangles.toArray(new Triangle3D[0]);
    }
}
