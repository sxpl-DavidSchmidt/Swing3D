package world.objects;

import util.RenderingPipeline;
import util.Vec3;
import world.Triangle3D;

public class Object3D {
    protected double size;
    protected Vec3 position;
    protected Vec3 orientation;
    protected Triangle3D[] localSpaceTriangles;

    public Object3D(Vec3 position, Vec3 orientation, double size) {
        this.position = position;
        this.orientation = orientation;
        this.size = size;
        this.initializeLocalSpaceTriangles();
    }

    public Object3D() {
        this(new Vec3(0, 0, 0), new Vec3(0, 0, 0), 1);
    }

    protected void initializeLocalSpaceTriangles() {
        this.localSpaceTriangles = new Triangle3D[0];
    }

    public Triangle3D[] getTriangles() {
        return RenderingPipeline.transformLocalSpace(localSpaceTriangles, this);
    }

    public Vec3 getPosition() {
        return position;
    }

    public Vec3 getOrientation() {
        return orientation;
    }

    public double getSize() {
        return size;
    }
}
