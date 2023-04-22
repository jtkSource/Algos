package jtk.basic.search;

import jtk.basic.sort.Heap;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by jubin on 1/2/17.
 */
public class Search {

    public static void main(String[] args) {

        Integer[] arrays = new Integer[10000];
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10000; i++) {
            arrays[i]=r.nextInt(10000);
        }
        Heap.heapSort(arrays);

        System.out.println(Arrays.toString(arrays));

        int index=binarySearch(arrays,arrays[9989]);
        System.out.println("binarySearch Index for  " + index + " :" + arrays[index]);


        index=interpolationSearch(arrays,arrays[9989]);
        System.out.println("interpolationSearch Index for  " + index + " :" + arrays[index]);


        index=interpolationSearch(arrays,arrays[8486]);
        System.out.println("interpolationSearch Index for " + index + " :" + arrays[index]);


        index=interpolationSearch(arrays,arrays[9]);
        System.out.println("interpolationSearch Index " + index + " :" + arrays[index]);
    }

    //the algorithm has O(log N) run time.
    public static int binarySearch(Integer[] items,Integer target){
        int min = 0;
        int max = items.length-1;
        int count=1;
        try {
            while (min <= max) {
                int mid = (min + max) / 2;
                if (target < items[mid])
                    max = mid - 1;
                else if (target > items[mid])
                    min = mid + 1;
                else return mid;
                count++;
            }
            return -1;
        }
        finally{
            System.out.println("Number of iterations: " + count);
        }
    }

    //If the distribution is reasonably uniform, the expected performance is O(log(log N))
    public static int interpolationSearch(Integer[] items,Integer target){
        int min=0;
        int max=items.length-1;
        int count=0;
        try {

            while (min <= max) {
                //The value is set to the current value of min plus the distance between min and max
                // when scaled by the expected fraction of the distance between values[min] and values[max]
                // where target should lie.
                int mid = min + (max - min) * (target - items[min]) / (items[max] - items[min]);
                if (target < items[mid])
                    max = mid - 1;
                else if (target > items[mid])
                    min = mid + 1;
                else return mid;
                count++;
            }

            return -1;

        }finally {
            System.out.println("Number of iterations: " + count);
        }
    }
}
