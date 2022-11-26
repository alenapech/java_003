package com.alenapech;

import java.util.*;

public class Main {

    private static final int OPERATION_COUNT = 10000;

    public static void main(String[] args) {
        /* 1. merge sort algorithm */
        System.out.println(Arrays.toString(mergeSort(new int[]{1, 7, 5, 4, 5, 2, 7})));
        /* 2. show 2 arrays with the largest and smallest numbers */
        printLargestSmallestSubarrays();
        /* 3. list tests */
        listTest();
    }

    private static void listTest() {
        List<Integer> arrayList = new ArrayList();
        List<Integer> linkedList = new LinkedList();
        System.out.println("ArrayList getTimeMsOfInsert: " + getTimeMsOfInsert(arrayList));
        System.out.println("ArrayList getTimeMsOfGet: " + getTimeMsOfGet(arrayList));
        System.out.println("ArrayList getTimeMsOfSet: " + getTimeMsOfSet(arrayList));
        System.out.println("ArrayList getTimeMsOfDelete: " + getTimeMsOfDelete(arrayList));

        System.out.println("LinkedList getTimeMsOfInsert: " + getTimeMsOfInsert(linkedList));
        System.out.println("LinkedList getTimeMsOfGet: " + getTimeMsOfGet(linkedList));
        System.out.println("LinkedList getTimeMsOfSet: " + getTimeMsOfSet(linkedList));
        System.out.println("LinkedList getTimeMsOfDelete: " + getTimeMsOfDelete(linkedList));
    }

    private static long getTimeMsOfInsert(List list) {
        Date start = new Date();
        for(int i = 0; i <= OPERATION_COUNT - 1; i++) {
            list.add(i);
        }
        return (new Date()).getTime() - start.getTime();
    }

    private static long getTimeMsOfGet(List list) {
        Date start = new Date();
        for(int i = OPERATION_COUNT - 1; i >= 0; i--) {
            list.get(i);
        }
        return (new Date()).getTime() - start.getTime();
    }

    private static long getTimeMsOfSet(List list) {
        Date start = new Date();
        for(int i = 0; i <= OPERATION_COUNT - 1; i++) {
            list.set(i, i+1);
        }
        return (new Date()).getTime() - start.getTime();
    }

    private static long getTimeMsOfDelete(List list) {
        Date start = new Date();
        for(int i = OPERATION_COUNT - 1; i >= 0; i--) {
            list.remove(i);
        }
        return (new Date()).getTime() - start.getTime();
    }

    private static void printLargestSmallestSubarrays() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert 20 digits using space as delimiter");
        int[] arr = Arrays.stream(input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sortedArr = mergeSort(arr);
        int[] a = new int[sortedArr.length / 2];
        int[] b = new int[sortedArr.length - a.length];
        System.arraycopy(sortedArr, 0, a, 0, a.length);
        System.arraycopy(sortedArr, a.length, b, 0, b.length);
//        System.out.println("The smallest numbers: " + Arrays.toString(a));
//        System.out.println("The biggest numbers: " + Arrays.toString(b));
        System.out.println("The smallest numbers");
        Arrays.stream(a).forEach(System.out::println);
        System.out.println("The biggest numbers");
        Arrays.stream(b).forEach(System.out::println);
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length == 1)
            return arr; // input array is considered to be sorted in case of 1-value array

        int[] a = new int[arr.length / 2]; // split input array in the middle
        int[] b = new int[arr.length - a.length];
        System.arraycopy(arr, 0, a, 0, a.length);
        System.arraycopy(arr, a.length, b, 0, b.length);

        return mergeSortedArrays(mergeSort(a), mergeSort(b)); // call merging of sorted arrays but first we need to split all sub-arrays till the 1-value arrays
    }

    private static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length]; // result array should have sum of merged arrays
        int j = 0;
        int i = 0;
        int k = 0;

        while(i < a.length && k < b.length) { // till we don't reach the end of the one of the input arrays
            if (a[i] <= b[k]) { // if a.value less or equal b.value then put a.value in the result array and increment 'a' array pointer (i)
                result[j] = a[i];
                i++;
            } else {
                result[j] = b[k]; // if b.value less then a.value then put b.value in the result array and increment 'b' array pointer (k)
                k++;
            }
            j++; // increment result pointer
        }
        // we need to put the rest of the a/b arrays in the result as is
        if (i <= a.length - 1) {
            System.arraycopy(a, i, result, j, a.length - i);
        }
        if (k <= b.length - 1) {
            System.arraycopy(b, k, result, j, b.length - k);
        }

        return result;
    }

}
