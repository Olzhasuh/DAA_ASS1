package divideconquer;

import java.util.Arrays;
import java.util.Random;

public class Benchmark {
    static Random rand = new Random();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Use: java Benchmark <algorithm> <size>");
            return;
        }

        String algo = args[0];
        int size = Integer.parseInt(args[1]);
        Metrics m = new Metrics();

        switch (algo) {
            case "mergesort":
                int[] a1 = randomArray(size);
                new MergeSort().sort(a1, m);
                break;

            case "quicksort":
                int[] a2 = randomArray(size);
                new QuickSort().sort(a2, m);
                break;

            case "select":
                int[] a3 = randomArray(size);
                new DeterministicSelect().select(a3, size/2, m);
                break;

            case "closest":
                Point[] points = randomPoints(size);
                new ClosestPair().find(points, m);
                break;
        }

        System.out.printf("N: %d, Comparisons: %d, MaxDepth: %d\n",
                size, m.comparisons, m.maxDepth);
    }

    static int[] randomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(n * 10);
        return arr;
    }

    static Point[] randomPoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        return points;
    }
}