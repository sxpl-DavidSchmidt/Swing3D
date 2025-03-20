package util;

import world.Triangle4D;

import java.util.*;
import java.util.List;

public class ClippingUtility {
    private enum VertexClipCase {
        INSIDE, OUTSIDE, BEHIND
    }

    private static VertexClipCase getVertexClipCase(Vec4 v) {
        if (Math.abs(v.z) > 1) return VertexClipCase.BEHIND;
        return (v.y * v.y > v.w * v.w) ?
                VertexClipCase.OUTSIDE : VertexClipCase.INSIDE;
    }

    private static Vec4 interpolateToViewport(Vec4 src, Vec4 dst) {
        double s_pos = (-src.y + src.w) / ((dst.y - src.y) - (dst.w - src.w));
        double s_neg = (-src.y - src.w) / ((dst.y - src.y) + (dst.w - src.w));

        if (s_pos > 0 && s_pos <= 1) {
            return Vec4.interpolateLine(src, dst, s_pos);
        } else if (s_neg > 0 && s_neg <= 1) {
            return Vec4.interpolateLine(src, dst, s_neg);
        } else {
            throw new RuntimeException("NO INTERCEPT");
        }
    }

    public static List<Triangle4D> applyPrimitiveClipping (Triangle4D triangle) {
        List<Vec4> insideVertices = new ArrayList<>();
        List<Vec4> outsideVertices = new ArrayList<>();
        for (Vec4 v : triangle.getVertices()) {
            if (getVertexClipCase(v).equals(VertexClipCase.INSIDE)) {
                insideVertices.add(v);
            } else {
                outsideVertices.add(v);
            }
        }

        if (outsideVertices.isEmpty()) {
            // implies insideVertices.size() = 3
            return List.of(triangle);
        }

        if (insideVertices.isEmpty() || outsideVertices
                .stream()
                .anyMatch( v -> getVertexClipCase(v).equals(VertexClipCase.BEHIND))
        ) {
            // case: all vertices outside
            return Collections.emptyList();
        } else if (insideVertices.size() == 1) {
            // case: two vertices outside
            Vec4 in = insideVertices.getFirst();
            Vec4 vp1 = interpolateToViewport(in, outsideVertices.get(0));
            Vec4 vp2 = interpolateToViewport(in, outsideVertices.get(1));
            return List.of(new Triangle4D(in, vp1, vp2));
        } else if (insideVertices.size() == 2) {
            // case: one vertex outside
            Vec4 in1 = insideVertices.get(0);
            Vec4 in2 = insideVertices.get(1);
            Vec4 out = outsideVertices.getFirst();

            Vec4 vp1 = interpolateToViewport(in1, out);
            Vec4 vp2 = interpolateToViewport(in2, out);

            return List.of(
                    new Triangle4D(in1, vp1, vp2),
                    new Triangle4D(in1, in2, vp2)
            );
        } else {
            throw new RuntimeException("ILLEGAL VERTEX STATE");
        }
    }
}
