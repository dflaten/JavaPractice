import java.util.*;

public class Program
{
    public static void main(String[] args) {
		System.out.println("Starting Program");
        int[] example = {2,47,67,3, 9, 7, 4};
        System.out.println("Is example: " + Arrays.toString(example) +" sorted: " + Arrays.toString(quickSort(example)));
	}
   
    public static int[] quickSort(int[] array) {
        quickSortRec(array, 0, array.length - 1);
        return array;
    }

    private static void quickSortRec(int[] array, int left, int right) {
       if (left < right) {
    int pivot = partition(array, left, right);
  

  quickSortRec(array, left, pivot - 1);
  quickSortRec(array, pivot + 1, right);
       }
}

private static int partition( int[] array, int left, int right) {
  int pivot = array[right];
   System.out.println("Pivot: " + pivot);
  int i = left - 1;
  for (int j = left; j < right; j++) {
    if (array[j] <= pivot) {
      i ++;
      System.out.println("i: " + i);
      System.out.println("Swapping " + array[i] + " And " + array[j]);
      int swapTemp = array[i];
      array[i]  = array[j];
      array[j] = swapTemp;
    }
  }
  System.out.println("Final Swap: " + array[i+1] + " AND: " +  array[right]);
  int swapTemp = array[i+1];
  array[i+1] = array[right];
  array[right] = swapTemp;
System.out.println("Array Current: " + Arrays.toString(array));
System.out.println("Final i: (before + 1) " + i);
  return i + 1;
}
