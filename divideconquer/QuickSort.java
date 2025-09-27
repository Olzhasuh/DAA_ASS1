package divideconquer;

import java.util.Random;

public class QuickSort {
    private Random rand = new Random();

    public void sort(int[] arr, Metrics m) {
        shuffle(arr);
        sort(arr, 0, arr.length - 1, m);
    }

    private void sort(int[] arr, int l, int r, Metrics m) {
        m.enter();

        while (l < r) {
            if (r - l <= 15) {
                insertionSort(arr, l, r, m);
                m.exit();
                return;
            }

            int p = partition(arr, l, r, m);

            if (p - l < r - p) {
                sort(arr, l, p - 1, m);
                l = p + 1;
            } else {
                sort(arr, p + 1, r, m);
                r = p - 1;
            }
        }

        m.exit();
    }

    private int partition(int[] arr, int l, int r, Metrics m) {
        int pivot = arr[r];
        int i = l - 1;

        for (int j = l; j < r; j++) {
            m.comp();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, r);
        return i + 1;
    }

    private void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void insertionSort(int[] arr, int l, int r, Metrics m) {
        for (int i = l + 1; i <= r; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= l) {
                m.comp();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }
}