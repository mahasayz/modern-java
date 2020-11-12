package com.learn.streams;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {

    private static final long N = 10_000_000L;

//    @Benchmark
    public long sequentialSum() {
        return Stream.iterate(1L, i -> i+1).limit(N).reduce(0L, Long::sum);
    }

//    @Benchmark
    public long iterativeSum() {
        long sum = 0;
        for (int i=1; i<=N; i++) {
            sum += i;
        }
        return sum;
    }

//    @Benchmark
    public long parallelSum() {
        /**
         * This is slow as we are allocating each sum() operation to a different thread. Hence, adding in on the unnecessary overhead.
         */
        return Stream.iterate(1L, i -> i+1).limit(N).parallel().reduce(0L, Long::sum);
    }

}
