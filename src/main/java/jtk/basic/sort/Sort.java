package jtk.basic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by jubin on 29/1/17.
 */
public class Sort {

    public static void main(String[] args) {

        Integer[] arrays = getRandomIntegers();
        long start = System.currentTimeMillis();
        System.out.println("insertion arrays = " + Arrays.toString(insertionSort(arrays)));
        long end = System.currentTimeMillis();
        System.out.println("insertion time (ms): " + (end - start));

        arrays = getRandomIntegers();
        start = System.currentTimeMillis();
        System.out.println("selection arrays = " + Arrays.toString(selecionsort(arrays)));
        end = System.currentTimeMillis();
        System.out.println("selection time (ms): " + (end - start));


        arrays = getRandomIntegers();
        start = System.currentTimeMillis();
        System.out.println("bubble sorted arrays = " + Arrays.toString(bubblesort(arrays)));
        end = System.currentTimeMillis();
        System.out.println("bubble time (ms): " + (end - start));

        arrays = getRandomIntegers();
        start = System.currentTimeMillis();
        System.out.println("heap sorted arrays = " + Arrays.toString(Heap.heapSort(arrays)));
        end = System.currentTimeMillis();
        System.out.println("heap time (ms): " + (end - start));

        arrays = getRandomIntegers();
        start = System.currentTimeMillis();
        QuickSort.quicksort(arrays);
        System.out.println("quick sort arrays = " + Arrays.toString(arrays));
        end = System.currentTimeMillis();
        System.out.println("quick time (ms): " + (end - start));

        arrays = getRandomIntegers();
        start = System.currentTimeMillis();
        mergeSort(arrays, new Integer[arrays.length], 0, arrays.length - 1);
        System.out.println("merge sort arrays = " + Arrays.toString(arrays));
        end = System.currentTimeMillis();
        System.out.println("merge time (ms): " + (end - start));

        arrays = getRandomIntegers();
        start = System.currentTimeMillis();
        countingSort(arrays, arrays.length);
        System.out.println("counting sort  = " + Arrays.toString(arrays));
        end = System.currentTimeMillis();
        System.out.println("counting time (ms): " + (end - start));

    }

    private static Integer[] getRandomIntegers() {
        Integer[] arrays = new Integer[10000];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10000; i++) {
            arrays[i] = r.nextInt(10000);
        }
        return arrays;
    }

    /**
     * Counting sort is a sorting technique based on keys between a specific range.
     * It works by counting the number of objects having distinct key values (kind of hashing).
     * Then doing some arithmetic to calculate the position of each object in the output sequence.
     * Time Complexity: O(n+k) where n is the number of elements in input array and k is the range of input.
     * Auxiliary Space: O(n+k)
     *
     * @param items
     * @param max_value
     */

    public static void countingSort(Integer[] items, int max_value) {
        Integer counts[] = new Integer[max_value];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        for (int i = 0; i <= items.length - 1; i++) {
            counts[items[i]] = counts[items[i]] + 1;
        }
        int index = 0;
        for (int i = 0; i < max_value; i++) {
            for (int j = 0; j < counts[i]; j++) {
                items[index] = i;
                index++;
            }
        }
    }

    public static void mergeSort(Integer[] items, Integer[] scratch, int start, int end) {

        if (start == end) return;
        int mid = (start + end) / 2;
        mergeSort(items, scratch, start, mid);
        mergeSort(items, scratch, mid + 1, end);

        int left_index = start;
        int right_index = mid + 1;
        int scratch_index = left_index;

        while ((left_index <= mid) && (right_index <= end)) {
            if (items[left_index] <= items[right_index]) {
                scratch[scratch_index] = items[left_index];
                left_index++;
            } else {
                scratch[scratch_index] = items[right_index];
                right_index++;
            }
            scratch_index++;
        }

        for (int i = left_index; i <= mid; i++) {
            scratch[scratch_index] = items[i];
            scratch_index++;
        }
        for (int i = right_index; i <= end; i++) {
            scratch[scratch_index] = items[i];
            scratch_index++;
        }

        for (int i = start; i <= end; i++) {
            items[i] = scratch[i];
        }
    }

    public static Integer[] bubblesort(Integer[] items) {//O(n^2)
        boolean notSorted = true;
        int i = 0;
        while (notSorted) {
            i++;
            notSorted = false;
            if (i % 2 == 0)
                notSorted = downwardpass(items, notSorted);
            else
                notSorted = upwardpass(items, notSorted);
        }
        return items;
    }

    private static boolean upwardpass(Integer[] items, boolean notSorted) {
        for (int i = items.length - 1; i > 0; i--) {
            if (items[i] < items[i - 1]) {
                int temp = items[i];
                items[i] = items[i - 1];
                items[i - 1] = temp;
                notSorted = true;

            }
        }
        return notSorted;
    }

    private static boolean downwardpass(Integer[] items, boolean notSorted) {
        for (int i = 1; i < items.length; i++) {
            if (items[i] < items[i - 1]) {
                int temp = items[i];
                items[i] = items[i - 1];
                items[i - 1] = temp;
                notSorted = true;
            }

        }
        return notSorted;
    }

    public static Integer[] selecionsort(Integer[] items) { //o(log(n^2)
        for (int i = 0; i < items.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < items.length; j++) {
                if (items[j] < items[index])
                    index = j;
            }
            int smallerNumber = items[index];
            items[index] = items[i];
            items[i] = smallerNumber;

        }
        return items;
    }

    public static Integer[] insertionSort(Integer[] arr) { //o(log(n^2)
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
