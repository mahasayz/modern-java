package com.practice.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SecondLargest {

    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        PriorityQueue<Integer> heap = new PriorityQueue<>(2);

        for (int i=0; i<arr.length; i++) {
            if (heap.size() < 2)
                heap.add(arr[i]);
            else {
                int topElement = heap.peek();
                if (topElement < arr[i]) {
                    heap.remove();
                    heap.add(arr[i]);
                }

            }
        }

        System.out.println(heap.peek());

    }

}
