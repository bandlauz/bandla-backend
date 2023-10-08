package uz.nazarovctrl.bandla.util;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public static String generateSmsCode() {
        return String.valueOf(random.nextInt(1000, 9999));
    }
}
