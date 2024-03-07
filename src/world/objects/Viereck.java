package world.objects;

import util.RenderingPipeline;
import util.Vec3;
import world.Triangle3D;

public class Viereck implements Object3D{
    private Triangle3D[]localSpaceTriangles;
    public Viereck(){
        Vec3 V1 = new Vec3(-1, 1, 0);
        Vec3 V2 = new Vec3(-1, -1, 0);
        Vec3 V3 = new Vec3(1, 1, 0);
        Vec3 V4 = new Vec3(1, -1, 0);

        Triangle3D tri1 = new Triangle3D(V1, V2, V3);
        Triangle3D tri2 = new Triangle3D(V2, V3, V4);

        localSpaceTriangles = new Triangle3D[]{tri1, tri2};

    }
    @Override
    public Triangle3D[] getTriangles() {
       return RenderingPipeline.transformToWorldSpace(localSpaceTriangles,this);
    }

    @Override
    public Vec3 getOrigin() {
        return new Vec3(-5, 0, -100);
    }

    @Override
    public Vec3 getOrientation() {

        return new Vec3(1, 1, 1);
    }

    @Override
    public double getSize(){
        return 30;
    }
}
