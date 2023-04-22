package jtk.algo.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Fib {

    private static final Logger log = LoggerFactory.getLogger(Fib.class);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21...


    public static int fib(int n){
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static HashMap<Integer, Integer> cache = new HashMap<>();
    public static int fibMem(int n){
        if (n < 2) {
            return n;
        }
        int n1 = cache.computeIfAbsent(n-1,integer -> fibMem(integer));
        int n2 = cache.computeIfAbsent(n-2,integer -> fibMem(integer));
        return n1 + n2;
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        IntStream.range(0,40)
                .forEach(value -> {
                    log.info("fib({}): {}",value, fib(value));
                });
        log.info("Time: {}ms", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        IntStream.range(0,40)
                .forEach(value -> {
                    log.info("fib({}): {}",value, fibMem(value));
                });
        log.info("Time: {}ms", System.currentTimeMillis() - start);
        log.info("Cache size {}", cache.size());
    }
}
