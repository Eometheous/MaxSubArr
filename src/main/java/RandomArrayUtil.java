import java.util.Random;

public class RandomArrayUtil {

    private static final Random generator = new Random();

    public static int[] randomArray(int length, int size) {
        generator.setSeed(20);
        int[] a = new int[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = generator.nextInt(size) * (generator.nextBoolean() ? -1 : 1);
        }

        return a;
    }
}
