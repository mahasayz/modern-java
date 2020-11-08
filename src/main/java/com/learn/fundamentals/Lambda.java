package com.learn.fundamentals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

enum Color {
    RED, GREEN;
}

class Apple {
    private int weight;
    private Color color;
    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() { return weight; }
    public Color getColor() { return color; }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}

public class Lambda {

    public static void printApples(List<Apple> apples) {
        for (Apple apple : apples)
            System.out.println(apple);
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple(175, Color.RED),
                new Apple(155, Color.RED),
                new Apple(55, Color.GREEN)
        );

        // sorting traditional w/o lambdas

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight() - a2.getWeight();
            }
        });

        System.out.println("=== ASCENDING ORDER ===");
        printApples(apples);

        // sorting with lambdas

        apples.sort((Apple a1, Apple a2) -> a2.getWeight() - a1.getWeight());

        System.out.println("=== DESCENDING ORDER ===");
        printApples(apples);
    }

}
