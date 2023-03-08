package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private final int[] numbers;

    private int min;

    MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        min = Arrays.stream(numbers).min().getAsInt();
    }
}
// END
