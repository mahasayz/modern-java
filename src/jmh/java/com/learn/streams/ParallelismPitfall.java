package com.learn.streams;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

class Accumulator {
    public long total = 0;
    public void add(long a) { this.total += a; }
}

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelismPitfall {

    @Benchmark
    public void sideEffectParallel() {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, 10_000_000L).parallel().forEach(accumulator::add);
        System.out.println("Result : " + accumulator.total);
    }

}
