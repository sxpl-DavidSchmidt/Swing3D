package util;

import world.*;  //java.world.*??
import util.*;
import world.objects.Object3D;


public class RenderingPipeline {
    //eingabe 3D Triangle, Ausgabe 4D Triangle
    public static Triangle4D[] Projection(Triangle3D[] array3D){

        //Triangle4D[] array4D = new Triangle4D[array3D.length];

        //2n/r-l,   0   ,    0    ,    0
        //   0  , 2n/t-b,    0    ,    0
        //   0  ,   0   , -f+n/f-n, -2fn/f-n
        //   0  ,   0   ,   -1    ,    0

        //x*(2n/(r-l)) +      y*0     +        z*0      +      w*0        = x
        //    x*0      + y*(2n/(t-b)) +        z*0      +      w*0        = y
        //    x*0      +      y*0     + z*(-(f+n)/(f-n) + w*(-(2fn)/(f-n) = z
        //    x*0      +      y*0     +       z*(-1)    +      w*0        = w

        //Math.tan(theta / 2.0) * zNear
        //variablen werden am Anfang einmal festgelegt

        double n  = 1;
        double f  = 1000;
        double rt = Math.tan(Math.toRadians(45)/ 2)*n;
        //double aspectRatio =

        double xf = (2 * n) / (2 * rt); //* aspectRatio;
        double yf = (2 * n) / (2 * rt);
        double zf = -(f + n) / (f - n) + -2 * ((f * n) / (f - n));
        double wf = -1;


        Triangle4D[] projectedTriangleBuffer = new Triangle4D[array3D.length];

        for (int i = 0; i < array3D.length; i++) {
            Triangle3D tri3 = array3D[i];

            Vec4 V1 = new Vec4(
                    tri3.V1.x * xf,
                    tri3.V1.y * yf,
                    tri3.V1.z * zf,
                    tri3.V1.z * wf);
            Vec4 V2 = new Vec4(
                    tri3.V2.x * xf,
                    tri3.V2.y * yf,
                    tri3.V2.z * zf,
                    tri3.V2.z * wf);
            Vec4 V3 = new Vec4(
                    tri3.V3.x * xf,
                    tri3.V3.y * yf,
                    tri3.V3.z * zf,
                    tri3.V3.z * wf);
            projectedTriangleBuffer[i] = new Triangle4D(V1, V2, V3);
        }

        return projectedTriangleBuffer;

        /*double n  = 1;
        double f  = 1000;
        double rt = Math.tan(Math.toRadians(45)/ 2)*n;
        //für 3D und 4D triangles array erschaffen und den für 3D (der in 4D gemacht werden soll) belget mit den übergebebeb triangles
        Vec4[] vec4Array = new Vec4[3];
        Vec3[] vec3Array = new Vec3[3];

        vec3Array[0] = tri3D.V1;
        vec3Array[1] = tri3D.V2;
        vec3Array[2] = tri3D.V3;
        // 3 mal (für jeden Vertex 1 mal) jeden Wert mit der Matrix multiplizieren, die Werte in ein 4D Vertex umwandeln und in dem array speichern
        for(int i=0; i<3; i++) {

            double x = vec3Array[i].x;
            double y = vec3Array[i].y;
            double z = vec3Array[i].z;
            double w = 1;

            double dx = x * ((2 * n) / (2 * rt));
            double dy = y * ((2 * n) / (2 * rt));
            double dz = z * ((-(f + n) / (f - n)) + (-2 * (f * n) / (f - n)));
            double dw = z * (-1);

            /*double xf = (2 * n) / (2 * Math.tan(Math.toRadians(45)/ 2)*n);
            double yf = (2 * n) / (2 * Math.tan(Math.toRadians(45)/ 2)*n);
            double zf = -(f + n) / (f - n) + -2 * ((f * n) / (f - n));
            double wf = -1;


            vec4Array[i] = new Vec4(dx, dy, dz, dw);
            //vec4Array[i] = new Vec4(x*xf,y*yf, z*zf, w*wf);
        }
        //die Vertexe in ein 4D triangle umwandeln und returnen
        Triangle4D tri4 = new Triangle4D(vec4Array[0], vec4Array[1], vec4Array[2]);


        //return tri4;*/
    }

    public static Triangle3D[] perspectiveDivide(Triangle4D[] array4D) {

        Triangle3D[] perspectiveDivedTriangle = new Triangle3D[array4D.length];

        for (int i = 0; i < array4D.length; i++) {

            Triangle4D tri4 = array4D[i];

            Vec3 V1 = new Vec3(
                    tri4.V1.x / tri4.V1.w,
                    tri4.V1.y / tri4.V1.w,
                    tri4.V1.z / tri4.V1.w);

            Vec3 V2 = new Vec3(
                    tri4.V2.x / tri4.V2.w,
                    tri4.V2.y / tri4.V2.w,
                    tri4.V2.z / tri4.V2.w);

            Vec3 V3 = new Vec3(
                    tri4.V3.x / tri4.V3.w,
                    tri4.V3.y / tri4.V3.w,
                    tri4.V3.z / tri4.V3.w);

            perspectiveDivedTriangle[i] = new Triangle3D(V1, V2, V3);
        }
        return perspectiveDivedTriangle;

       /* double x1 = V1.x;
        double y1 = V1.y;
        double z1 = V1.z;
        double w1 = V1.w;

        Vec3 vec1 = new Vec3(x1/w1, y1/w1, z1/w1);

        double x2 = V2.x;
        double y2 = V2.y;
        double z2 = V2.z;
        double w2 = V2.w;

        Vec3 vec2 = new Vec3(x2/w2, y2/w2, z2/w2);

        double x3 = V3.x;
        double y3 = V3.y;
        double z3 = V3.z;
        double w3 = V3.w;

        Vec3 vec3 = new Vec3(x3/w3, y3/w3, z3/w3);

        //die punkte in 3D vertexe umwandeln und diese in ein 3D triangle

        return new Triangle3D(vec1, vec2, vec3);*/

    }

    public static Triangle3D[] transformToWorldSpace(Triangle3D[] triangles, Object3D object){

        double size = object.getSize();
        Vec3 origin = object.getOrigin();
        Vec3 oriantation = object.getOrientation();
        Triangle3D[] fertig = new Triangle3D[triangles.length];

        for(int i=0; i< triangles.length; i++){
            Triangle3D tmp = triangles[i];
            Vec3 V1 = rotateVertex(tmp.V1, oriantation).scale(size).add(origin);
            Vec3 V2 = rotateVertex(tmp.V2, oriantation).scale(size).add(origin);
            Vec3 V3 = rotateVertex(tmp.V3, oriantation).scale(size).add(origin);

            fertig[i] = new Triangle3D(V1, V2, V3);
        }
        return fertig;
    }

    public static Vec3 rotateVertex(Vec3 vertex, Vec3 orientation){

        double cos_a = Math.cos(orientation.x);
        double cos_b = Math.cos(orientation.y);
        double cos_g = Math.cos(orientation.z);

        double sin_a = Math.sin(orientation.x);
        double sin_b = Math.sin(orientation.y);
        double sin_g = Math.sin(orientation.z);

        double dx = vertex.x * (cos_a * cos_b)
                + vertex.y * (cos_a * sin_b * sin_g - sin_a * cos_g)
                + vertex.z * (cos_a * sin_b * cos_g + sin_a * sin_g);
        double dy = vertex.x * (sin_a * cos_b)
                + vertex.y * (sin_a * sin_b * sin_g + cos_a * cos_g)
                + vertex.z * (sin_a * sin_b * cos_g - cos_a * sin_g);
        double dz = vertex.x * (-sin_b)
                + vertex.y * (cos_b * sin_g)
                + vertex.z * (cos_b * cos_g);

        return new Vec3(dx, dy, dz);
    }

    }
