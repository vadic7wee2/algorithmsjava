package com.algorithms;

import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    runBinarySearch();
    runQuickSort();
    runSelectionSort();
  }

  private static void runBinarySearch() {
    var items = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    var search = 7;
    System.out.println("Binary Search Example:");
    System.out.println("Array is: " + items);
    System.out.println("Index of searching element (" + search + "): is " + binarySearch(items, search));
    System.out.println();
  }

  /**
   * Array should be sorted. Take item in the middle of array and check if it is greater than item that we are looking.
   * if greater than do the same steps on sub-array of greater numbers, if smaller than on sub-array of smaller items
   */
  private static int binarySearch(List<Integer> items, Integer item) {
    var lowIdx = 0;
    var highIdx = items.size() - 1;
    while (lowIdx <= highIdx) {
      int midIdx = (lowIdx + highIdx) / 2;
      int guessItem = items.get(midIdx);
      if (guessItem == item) {
        return midIdx;
      } else if (guessItem < item) {
        lowIdx = midIdx + 1;
      } else {
        highIdx = midIdx - 1;
      }
    }
    return -1;
  }

  private static void runQuickSort() {
    Integer[] arrayToSort = { 4, 5, 3, 6, 7, 8, 11, 59, 4, 3, 52, 77, 33, 2, 1, 88, 6, 55, 4, 3, 100, 99, 101, 54, 22 };
    System.out.println("Quick sort example:");
    System.out.println("Array to sort is: " + Arrays.toString(arrayToSort));
    System.out.println("Sorted Array: " + Arrays.toString(quickSort(arrayToSort)));
    System.out.println();
  }

  /** Algorithm
   * 1. chose random element 'pivot' in the list more or less in the middle
   * 2. creates array for items less than pivot, equal to pivot and greater than pivot
   * 3. combine sub-arrays to new array [less, equal, greater] running quickSort by recursion on each sub-array
   */
  private static Integer[] quickSort(Integer[] items) {
    if (items.length <= 1) {
      return items;
    }
    int length = items.length;
    var pivot = items[length / 2];

    var less = new Integer[length];
    int lessIdx = 0;
    var equal = new Integer[length];
    var equalIdx = 0;
    var greater = new Integer[length];
    var greaterIdx = 0;

    for (Integer iterationItem : items) {
      if (iterationItem < pivot) {
        less[lessIdx] = iterationItem;
        lessIdx++;
      } else if (iterationItem > pivot) {
        greater[greaterIdx] = iterationItem;
        greaterIdx++;
      } else {
        equal[equalIdx] = iterationItem;
        equalIdx++;
      }
    }

    Integer[] lessRes = new Integer[lessIdx];
    System.arraycopy(less, 0, lessRes, 0, lessIdx);
    Integer[] lessSorted = lessIdx > 0 ? quickSort(lessRes) : lessRes;

    Integer[] equalSorted = new Integer[equalIdx];
    System.arraycopy(equal, 0, equalSorted, 0, equalIdx);

    Integer[] greaterRes = new Integer[greaterIdx];
    System.arraycopy(greater, 0, greaterRes, 0, greaterIdx);
    Integer[] greaterSorted = greaterIdx > 0 ? quickSort(greaterRes) : greaterRes;

    var result = new Integer[length];
    System.arraycopy(lessSorted, 0, result, 0, lessIdx);
    System.arraycopy(equalSorted, 0, result, lessIdx, equalIdx);
    System.arraycopy(greaterSorted, 0, result, lessIdx + equalIdx, greaterIdx);

    return result;
  }

  private static void runSelectionSort() {
    Integer[] arrayToSort = { 4, 5, 3, 6, 7, 8, 11, 59, 4, 3, 52, 77, 33, 2, 1, 88, 6, 55, 4, 3, 100, 99, 101, 54, 22 };
    System.out.println("Selection sort example:");
    System.out.println("Array to sort is: " + Arrays.toString(arrayToSort));
    System.out.println("Sorted Array: " + Arrays.toString(selectionSort(arrayToSort)));
    System.out.println();
  }

  /**
   * Algorithm:
   * 1. Find the minimum element in the array
   * 2. Put minimum element as a first one in result array
   * 3. Run selectionSort on the rest array by recursion (without minimum element) result = [minimum, recursion(sub-array)]
   */
  private static Integer[] selectionSort(Integer[] items) {
    int length = items.length;
    if (length == 1) {
      return items;
    }
    var min = items[0];
    int minIndex = 0;
    for (int i = 0; i < length; i++) {
      if (items[i] < min) {
        min = items[i];
        minIndex = i;
      }
    }
    Integer[] newItemsArray = new Integer[length - 1];
    int newItemsArrayIdx = 0;
    for (int i = 0; i < length; i++) {
      if (i != minIndex) {
        newItemsArray[newItemsArrayIdx] = items[i];
        newItemsArrayIdx++;
      }
    }

    Integer[] result = new Integer[length];
    result[0] = min;
    System.arraycopy(selectionSort(newItemsArray), 0, result, 1, newItemsArray.length);
    return result;
  }

}