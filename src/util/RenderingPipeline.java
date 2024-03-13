package util;

import world.Camera;
import world.Triangle3D;
import world.Triangle4D;
import world.objects.Object3D;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RenderingPipeline {
    public static Triangle3D[] runPipeline(
            Triangle3D[] triangles, Camera camera,
            double zNear, double zFar, double fov, double aspectRatio) {
        Triangle3D[] cameraSpaceTriangles = applyCameraTransform(triangles, camera);
        Triangle4D[] projectedTriangles = applyProjectionMatrix(cameraSpaceTriangles, zNear, zFar, fov, aspectRatio);
        Triangle4D[] clippedTriangles = applyClipping(projectedTriangles);
        return applyPerspectiveDivide(clippedTriangles);
    }

    public static Triangle3D[] applyCameraTransform(Triangle3D[] triangles, Camera camera) {
        // Mit -1 skaliert, um aus addition subtraktion zu machen
        Vec3 position = camera.getPosition().scale(-1);
        // Mit -1 skaliert, um Vertices entgegen der Kamerarotation zu drehen
        Vec3 orientation = camera.getOrientation().scale(-1);

        Triangle3D[] cameraSpaceTriangles = triangles.clone();
        for (int i = 0; i < cameraSpaceTriangles.length; i++) {
            Vec3[] vertices = cameraSpaceTriangles[i].getVertices();
            cameraSpaceTriangles[i] = new Triangle3D(
                    vertices[0].add(position),
                    vertices[1].add(position),
                    vertices[2].add(position)
            );
        }

        cameraSpaceTriangles = rotate(cameraSpaceTriangles, orientation);
        return cameraSpaceTriangles;
    }

    public static Triangle4D[] applyProjectionMatrix(
            Triangle3D[] triangles,
            double zNear, double zFar, double fov, double aspectRatio) {
        double rt = Math.tan(Math.toRadians(fov)/ 2)*zNear;

        double xf = zNear / rt * aspectRatio;
        double yf = zNear / rt;
        double zf = 2 / (zFar - zNear);
        double zs = (zFar+zNear)/(zFar-zNear);
        double wf = -1;

        Triangle4D[] projectedTriangles = new Triangle4D[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            Triangle3D tri3 = triangles[i];
            Vec4 v1 = new Vec4(
                    tri3.v1.x * xf,
                    tri3.v1.y * yf,
                    tri3.v1.z * zf - zs,
                    tri3.v1.z * wf);
            Vec4 v2 = new Vec4(
                    tri3.v2.x * xf,
                    tri3.v2.y * yf,
                    tri3.v2.z * zf - zs,
                    tri3.v2.z * wf);
            Vec4 v3 = new Vec4(
                    tri3.v3.x * xf,
                    tri3.v3.y * yf,
                    tri3.v3.z * zf - zs,
                    tri3.v3.z * wf);

            projectedTriangles[i] = new Triangle4D(v1, v2, v3);
        }

        return projectedTriangles;
    }

    public static Triangle4D[] applyClipping(Triangle4D[] triangles) {
        List<Triangle4D> clippedTriangleList = new LinkedList<>();
        for (Triangle4D triangle: triangles) {
            boolean doClip = false;
            for (Vec4 vertex: triangle.getVertices()) {
                double x = vertex.x;
                double y = vertex.y;
                double z = vertex.z;
                double w = vertex.w * 2;

                if (Math.abs(x) > Math.abs(w)) doClip = true;
                if (Math.abs(y) > Math.abs(w)) doClip = true;
                if (Math.abs(z) > Math.abs(1)) doClip = true;
            }

            if (!doClip) clippedTriangleList.add(triangle);
        }

        return clippedTriangleList.toArray(new Triangle4D[0]);
    }

    public static Triangle3D[] applyPerspectiveDivide(Triangle4D[] triangles) {
        Triangle3D[] perspectiveDivedTriangle = new Triangle3D[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            Triangle4D tri4 = triangles[i];
            Vec3 v1 = new Vec3(
                    tri4.v1.x / tri4.v1.w,
                    tri4.v1.y / tri4.v1.w,
                    tri4.v1.z / tri4.v1.w);

            Vec3 v2 = new Vec3(
                    tri4.v2.x / tri4.v2.w,
                    tri4.v2.y / tri4.v2.w,
                    tri4.v2.z / tri4.v2.w);

            Vec3 v3 = new Vec3(
                    tri4.v3.x / tri4.v3.w,
                    tri4.v3.y / tri4.v3.w,
                    tri4.v3.z / tri4.v3.w);

            perspectiveDivedTriangle[i] = new Triangle3D(v1, v2, v3);
        }

        return perspectiveDivedTriangle;
    }

    public static Triangle3D[] transformLocalSpace(Triangle3D[] triangles, Object3D object) {
        Vec3 orientation = object.getOrientation();
        Vec3 position = object.getPosition();
        double size = object.getSize();

        boolean doPositionTransform = !(position.x == 0 && position.y == 0 && position.z == 0);
        boolean doOrientationTransform = !(orientation.x == 0 && orientation.y == 0 && orientation.z == 0);

        if (!doPositionTransform && !doOrientationTransform) return triangles;

        Triangle3D[] localSpaceTriangles;
        if (doOrientationTransform) {
            localSpaceTriangles = rotate(triangles, orientation);
        } else {
            localSpaceTriangles = triangles.clone();
        }

        if (doPositionTransform) {
            for (int i = 0; i < localSpaceTriangles.length; i++) {
                Vec3[] vertices = localSpaceTriangles[i].getVertices();
                localSpaceTriangles[i] = new Triangle3D(
                        vertices[0].scale(size).add(position),
                        vertices[1].scale(size).add(position),
                        vertices[2].scale(size).add(position)
                );
            }
        } else {
            for (int i = 0; i < localSpaceTriangles.length; i++) {
                Vec3[] vertices = localSpaceTriangles[i].getVertices();
                localSpaceTriangles[i] = new Triangle3D(
                        vertices[0].scale(size),
                        vertices[1].scale(size),
                        vertices[2].scale(size)
                );
            }
        }

        return localSpaceTriangles;
    }

    private static Triangle3D[] rotate(Triangle3D[] triangles, Vec3 orientation) {
        if (orientation.x == 0 && orientation.y == 0 && orientation.z == 0)
            return triangles;

        Triangle3D[] rotatedTriangles = new Triangle3D[triangles.length];
        double[] sin = new double[] {
                Math.sin(orientation.z * Math.PI),
                Math.sin(orientation.y * Math.PI),
                Math.sin(orientation.x * Math.PI)};

        double[] cos = new double[] {
                Math.cos(orientation.z * Math.PI),
                Math.cos(orientation.y * Math.PI),
                Math.cos(orientation.x * Math.PI)};

        for (int i = 0; i < triangles.length; i++) {
            Vec3[] vertices = triangles[i].getVertices();
            Vec3 v0 = rotateVertex(vertices[0], sin, cos);
            Vec3 v1 = rotateVertex(vertices[1], sin, cos);
            Vec3 v2 = rotateVertex(vertices[2], sin, cos);
            rotatedTriangles[i] = new Triangle3D(v0, v1, v2);
        }

        return rotatedTriangles;
    }

    private static Vec3 rotateVertex(Vec3 vertex, double[] sin, double[] cos) {
        double dx = vertex.x * (cos[0] * cos[1])
                + vertex.y * (cos[0] * sin[1] * sin[2] - sin[0] * cos[2])
                + vertex.z * (cos[0] * sin[1] * cos[2] + sin[0] * sin[2]);
        double dy = vertex.x * (sin[0] * cos[1])
                + vertex.y * (sin[0] * sin[1] * sin[2] + cos[0] * cos[2])
                + vertex.z * (sin[0] * sin[1] * cos[2] - cos[0] * sin[2]);
        double dz = vertex.x * (-sin[1])
                + vertex.y * (cos[1] * sin[2])
                + vertex.z * (cos[1] * cos[2]);
        return new Vec3(dx, dy, dz);
    }
}
