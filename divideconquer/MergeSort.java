package divideconquer;

public class MergeSort {
    public void sort(int[] arr, Metrics m) {
        int[] buf = new int[arr.length];
        sort(arr, 0, arr.length - 1, buf, m);
    }

    private void sort(int[] arr, int l, int r, int[] buf, Metrics m) {
        m.enter();

        if (r - l <= 15) {
            insertionSort(arr, l, r, m);
            m.exit();
            return;
        }

        int mid = (l + r) / 2;
        sort(arr, l, mid, buf, m);
        sort(arr, mid + 1, r, buf, m);
        merge(arr, l, mid, r, buf, m);

        m.exit();
    }

    private void merge(int[] arr, int l, int m, int r, int[] buf, Metrics mt) {
        for (int i = l; i <= r; i++) buf[i] = arr[i];

        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) {
            mt.comp();
            if (buf[i] <= buf[j]) {
                arr[k++] = buf[i++];
            } else {
                arr[k++] = buf[j++];
            }
        }

        while (i <= m) arr[k++] = buf[i++];
        while (j <= r) arr[k++] = buf[j++];
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