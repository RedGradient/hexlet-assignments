package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {

    private final int[] numbers;

    private int max;

    MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        max = Arrays.stream(numbers).max().getAsInt();
    }
}
// END
