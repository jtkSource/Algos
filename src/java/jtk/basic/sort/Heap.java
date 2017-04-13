package jtk.basic.sort;

/**
 * Created by jubin on 30/1/17.
 */
public class Heap {

    /**
     * To build the initial heap, the algorithm adds each item to a growing heap.
     * Each time it adds an item, it places the item at the end of the tree and swaps the item upward until the tree is again a heap.
     * Because the tree is a complete binary tree, it is O(log N) levels tall, so pushing the item up through the tree can take,
     * at most, O(log N) steps. The algorithm performs this step of adding an item and restoring the heap property N times,
     * so the total time to build the initial heap is O(N log N).
     * @param items
     * @return
     */
    public static Integer[] createHeap(Integer[] items){

        for (int i = 0; i < items.length; i++) {
            int index=i;
            while (index!=0){
                int parent = (index-1)/2;
                if(items[index]<=items[parent])
                    break;
                int temp = items[index];
                items[index]=items[parent];
                items[parent]=temp;
                index=parent;
            }
        }
        return items;
    }

    /**
     *To finish sorting, the algorithm removes each item from the heap and then restores the heap property.
     * It does that by swapping the last item in the heap and the root node, and then swapping the new root down through
     * the tree until the heap property is restored. The tree is O(log N) levels tall, so this can take up to O(log N) time.
     * The algorithm repeats this step N times, so the total number of steps required is O(N log N).
     *
     * @param item
     * @param count - This algorithm takes as a parameter the size of the tree,
     *              so it can find the location where the heap ends within the array.
     * @return
     */
    public static int removeTopItem(Integer item[], int count){
        Integer result = item[0];
        item[0]=item[count-1];// add the last element to the root to have it sorted by the tree
        int index=0;
        while(true){
            int child1=2*index+1;
            int child2=2*index+2;
            if(child1>=count)
                child1=index;
            if(child2>=count)
                child2=index;
            if((item[index]>=item[child1]) && (item[index]>=item[child2]))
                break;
            int swap_child;
            if(item[child1]>item[child2])
                swap_child=child1;
            else
                swap_child = child2;

            int temp = item[index];
            item[index]=item[swap_child];
            item[swap_child]=temp;
            index=swap_child;
        }return result;
    }

    /**
     * This algorithm starts by turning the array of values into a heap. It then repeatedly removes the top item,
     * which is the largest, and moves it to the end of the heap.
     * It reduces the number of items in the heap and restores the heap property,
     * leaving the newly positioned item at the end of the heap in its proper sorted order.
     * Adding the time needed to build the initial heap and the time to finish sorting gives a total time of
     * O(N log N) + O(N log N) = O(N log N)
     * @param items
     * @return
     */
    public static Integer[] heapSort(Integer[] items){
        Integer[] heap = createHeap(items);
        for (int i = 0 ; i < items.length; i++) {
            int max = removeTopItem(items,items.length-i);
            items[(items.length-1)-i]=max; //add largest element to end of the array
        }
        return items;
    }
}
