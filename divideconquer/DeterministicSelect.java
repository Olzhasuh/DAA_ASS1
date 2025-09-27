package divideconquer;

import java.util.Arrays;

public class DeterministicSelect {
    public int select(int[] arr, int k, Metrics m) {
        return select(arr, 0, arr.length - 1, k, m);
    }

    private int select(int[] arr, int l, int r, int k, Metrics m) {
        m.enter();

        if (l == r) {
            m.exit();
            return arr[l];
        }

        int pivotIdx = medianOfMedians(arr, l, r, m);
        pivotIdx = partition(arr, l, r, pivotIdx, m);

        if (k == pivotIdx) {
            m.exit();
            return arr[k];
        } else if (k < pivotIdx) {
            m.exit();
            return select(arr, l, pivotIdx - 1, k, m);
        } else {
            m.exit();
            return select(arr, pivotIdx + 1, r, k, m);
        }
    }

    private int medianOfMedians(int[] arr, int l, int r, Metrics m) {
        int n = r - l + 1;
        if (n <= 5) return median5(arr, l, r);

        int groups = (n + 4) / 5;
        int[] medians = new int[groups];

        for (int i = 0; i < groups; i++) {
            int gl = l + i * 5;
            int gr = Math.min(gl + 4, r);
            medians[i] = median5(arr, gl, gr);
        }

        return select(medians, 0, groups - 1, groups / 2, m);
    }

    private int median5(int[] arr, int l, int r) {
        int[] group = Arrays.copyOfRange(arr, l, r + 1);
        Arrays.sort(group);
        return group[group.length / 2];
    }

    private int partition(int[] arr, int l, int r, int pivotIdx, Metrics m) {
        int pivot = arr[pivotIdx];
        swap(arr, pivotIdx, r);

        int i = l;
        for (int j = l; j < r; j++) {
            m.comp();
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, r);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}