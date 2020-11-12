package com.practice.leetcode;

public class MaxProfit {

    public static long sum(int a, int b) {
        return (long) (Math.pow(b,2) + b - Math.pow(a,2) - a) / 2;
    }

    public static long maxValue(int[] inventory, int orders) {
        int value = 0;
        int counter = 1;

        for (int i=inventory.length-1; i>=1; i--) {
            int b = inventory[i];
            int a = inventory[i-1];
            if (counter * (b - a) > orders) {
                int totalValuesAvailable = counter * (b - a);
                int wholeValuesRequired = totalValuesAvailable / orders;
                a = b - wholeValuesRequired;
                value += counter * sum(a, b);
                value += (totalValuesAvailable % orders) * a;
                break;
            }

            value += counter * sum(a, b);
            orders -= counter * (b - a);
            counter++;
        }

        if (orders > 0) {
            int totalValuesAvailable = counter * inventory[0];
            int wholeValuesRequired = totalValuesAvailable / orders;
            int b = inventory[0];
            int a = b - wholeValuesRequired;
            value += counter * sum(a, b);
            value += (totalValuesAvailable % orders) * a;
        }

        return value;
    }

    public static void main(String[] args) {

        // 2 balls
        System.out.println(maxValue(new int[]{2,5}, 4));

    }

}
