import java.util.*;

public class Program
{
    public static void main(String[] args) {
		System.out.println("Starting Program");
        int[] example = {2,47,67,3, 9, 7, 4};
        System.out.println("Is example: " + Arrays.toString(example) +" sorted: " + Arrays.toString(selectionSort(example)));
	}
   
    /** 
     * Selection Sort: 
     * 1. Iterate through entire list.
     * 2. For the first iteration find the smallest item and put it at the start of the list.
     * 3. For the remaining iterations, find the next smallest item and place it in the next position of list.
     * 4. Once you get to the end of the list return the now sorted array.
    */
    public static int[] selectionSort(int[] array) {
        // Iterate through all the items in the list. We skip the last item in the 
        // list (array.length - 1) because that one will always be in the right place
        // since by the time we get to it all the other items are sorted.
        for (int i = 0; i < array.length - 1; i ++) {
            // Choose the first element in our sublist as the lowest, will update
            // as new lowest is found
            int smallest = i;
            // Iterate through the remaining items in the list to find the next lowest.
            for (int j = i + 1; j < array.length; j++) {
                if(array[j] < array[smallest]) {
                    smallest = j;
                }
            }
            int temp = array[i];
            array[i] = array[smallest];
            array[smallest] = temp;
        }
        return array;
    }

   
}
