package com.learn.streams;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length < THRESHOLD)
            return computeSequentially();

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();

        return leftResult + rightResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i=start; i<end; i++)
            sum += numbers[i];
        return sum;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 10_000_000L).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long result = new ForkJoinPool().invoke(task);
        System.out.println(result);
    }
}
