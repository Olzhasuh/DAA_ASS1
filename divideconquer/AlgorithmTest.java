package divideconquer;

import java.util.Arrays;

public class AlgorithmTest {
    public static void main(String[] args) {
        testMergeSort();
        testQuickSort();
        testSelect();
        testClosestPair();
        System.out.println("All tests passed!");
    }

    static void testMergeSort() {
        int[] arr = {5, 2, 8, 1, 9};
        int[] expected = {1, 2, 5, 8, 9};
        Metrics m = new Metrics();
        new MergeSort().sort(arr, m);
        assert Arrays.equals(arr, expected) : "MergeSort failed";
    }

    static void testQuickSort() {
        int[] arr = {5, 2, 8, 1, 9};
        int[] expected = {1, 2, 5, 8, 9};
        Metrics m = new Metrics();
        new QuickSort().sort(arr, m);
        assert Arrays.equals(arr, expected) : "QuickSort failed";
    }

    static void testSelect() {
        int[] arr = {5, 2, 8, 1, 9};
        Metrics m = new Metrics();
        int result = new DeterministicSelect().select(arr, 2, m);
        assert result == 5 : "Select failed";
    }

    static void testClosestPair() {
        Point[] points = {
                new Point(0, 0), new Point(1, 1),
                new Point(3, 3), new Point(5, 5)
        };
        Metrics m = new Metrics();
        ClosestPair.Result result = new ClosestPair().find(points, m);
        assert result.dist > 0 : "ClosestPair failed";
    }
}