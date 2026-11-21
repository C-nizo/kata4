package software.ulpgc.katas.architecture.domain;

import java.util.stream.IntStream;

public record DivisorsResult(int number, int[] divisors) {
    public static DivisorsResult calculate(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("El valor debe ser mayor que cero.");
        }

        int[] divisors = IntStream.rangeClosed(1, number)
                .filter(i -> number % i == 0)
                .toArray();

        return new DivisorsResult(number, divisors);
    }
}
