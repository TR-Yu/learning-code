package cn.prinf.demos.junit.spring.jmh;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author YU
 * @date 2022-06-19 23:03
 */
public class MethodTest {

    @Test
    public void runBenchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MethodTest.class.getSimpleName())
                .mode(Mode.Throughput)
                .warmupIterations(5)
                .forks(0)
                .threads(5)
                .measurementIterations(5)
                .measurementTime(TimeValue.seconds(2))
                .build();
        new Runner(opt).run();
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x = Math.PI;
        ReentrantLock lock = new ReentrantLock(true);
    }

    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    @Benchmark
    public void measureUnshared(ThreadState state) {
        state.x++;
    }

    @Benchmark
    public void measureShared(BenchmarkState state) {
        state.lock.lock();
        state.x++;
        state.lock.unlock();
    }

    @Benchmark
    public void measureThreads(BenchmarkState state) {
        synchronized ((Object) state.x) {
            state.x++;
        }
    }


}
