package com.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() { return name; }
    public boolean isVegetarian() { return vegetarian; }
    public int getCalories() { return calories; }
    public Type getType() { return type; }
    @Override
    public String toString() { return name; }
    public enum Type { MEAT, FISH, OTHER }
}

public class BasicStreams<T> {

    public static<T> void printItems(List<T> items) {
        for (T item : items)
            System.out.println(item);
    }

    public static void main(String[] args) {

        /*
         * Problem Statement: return names of dishes low in calories sorted by calorie count
         */
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        // without streams

        List<Dish> lowCalorieDishes = new ArrayList<>();
        for (Dish dish : menu)
            if (dish.getCalories() < 400)
                lowCalorieDishes.add(dish);

        lowCalorieDishes.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return d1.getCalories() - d2.getCalories();
            }
        });

        List<String> lowCalorieDishNames = new ArrayList<>();
        for (Dish dish : lowCalorieDishes)
            lowCalorieDishNames.add(dish.getName());

        printItems(lowCalorieDishNames);

        // with streams

        printItems(
                menu.stream()
                        .filter(dish -> dish.getCalories() < 400)
                        .sorted(Comparator.comparingInt(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(Collectors.toList())
        );

        /*
         * Problem Statement: find unique characters in a list of words
         */

        List<String> words = Arrays.asList("Hello", "World");

        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)    // convert array to stream
                .distinct()
                .forEach(System.out::println);

        IntStream.range(1, 6)
                .map(x -> x * x)
                .forEach(System.out::println);

        List<Integer> one = Arrays.asList(1,2,3);
        List<Integer> two = Arrays.asList(3,4);

        one.stream()
                .flatMap(a -> two.stream()
                        .map(b -> new int[]{a,b})
                )
                .filter(arr -> (arr[0] + arr[1]) % 3 == 0)
                .forEach(x -> System.out.println(Arrays.toString(x)));
    }

}
