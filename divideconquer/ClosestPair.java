package divideconquer;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    public static class Result {
        public Point a, b;
        public double dist;
        public Result(Point a, Point b) {
            this.a = a;
            this.b = b;
            this.dist = a.dist(b);
        }
    }

    public Result find(Point[] points, Metrics m) {
        Point[] px = points.clone();
        Point[] py = points.clone();

        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        return find(px, py, 0, points.length - 1, m);
    }

    private Result find(Point[] px, Point[] py, int l, int r, Metrics m) {
        m.enter();

        int n = r - l + 1;
        if (n <= 3) {
            m.exit();
            return bruteForce(px, l, r, m);
        }

        int mid = (l + r) / 2;
        Point midPoint = px[mid];

        Point[] leftY = Arrays.stream(py)
                .filter(p -> p.x < midPoint.x || (p.x == midPoint.x && p.y < midPoint.y))
                .toArray(Point[]::new);
        Point[] rightY = Arrays.stream(py)
                .filter(p -> p.x > midPoint.x || (p.x == midPoint.x && p.y >= midPoint.y))
                .toArray(Point[]::new);

        Result left = find(px, leftY, l, mid, m);
        Result right = find(px, rightY, mid + 1, r, m);

        Result best = left.dist < right.dist ? left : right;

        Point[] strip = Arrays.stream(py)
                .filter(p -> Math.abs(p.x - midPoint.x) < best.dist)
                .toArray(Point[]::new);

        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && strip[j].y - strip[i].y < best.dist; j++) {
                m.comp();
                double d = strip[i].dist(strip[j]);
                if (d < best.dist) {
                    best = new Result(strip[i], strip[j]);
                }
            }
        }

        m.exit();
        return best;
    }

    private Result bruteForce(Point[] points, int l, int r, Metrics m) {
        Result best = new Result(points[l], points[l + 1]);
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                m.comp();
                double d = points[i].dist(points[j]);
                if (d < best.dist) {
                    best = new Result(points[i], points[j]);
                }
            }
        }
        return best;
    }
}