import java.util.Random;

/**
 * A utility class that creates a random array of a set length and range of the numbers
 */
public class RandomArrayUtil {

    private static final Random generator = new Random();

    public static int[] randomArray(int length, int range) {
        generator.setSeed(20);
        int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = generator.nextInt(range) * (generator.nextBoolean() ? -1 : 1);
        }

        return a;
    }
}
