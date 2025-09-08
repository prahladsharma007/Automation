package AUT.utilities;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStringGenerator {

    private static final String UPPER_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String randomAlpha(int length, boolean includeUpper, boolean includeLower) {
        String alphaChars = (includeUpper ? UPPER_ALPHA : "") + (includeLower ? LOWER_ALPHA : "");
        return generateRandomString(alphaChars, length);
    }

    public static String randomNumeric(int length) {
        return generateRandomString(NUMERIC, length);
    }

    public static String randomAlphanumeric(int length, boolean includeUpper, boolean includeLower) {
        String alphaChars = (includeUpper ? UPPER_ALPHA : "") + (includeLower ? LOWER_ALPHA : "");
        return generateRandomString(alphaChars + NUMERIC, length);
    }

    private static String generateRandomString(String characters, int length) {
        if (characters.isEmpty()) {
            throw new IllegalArgumentException("Character set cannot be empty");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(RANDOM.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public static String generateRandomString() {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        // Generate the first 4 capital letters (A-Z)
        for (int i = 0; i < 4; i++) {
            char capitalLetter = (char) ('A' + random.nextInt(26));
            sb.append(capitalLetter);
        }

        // Generate the next 3 numbers (0-9)
        for (int i = 0; i < 3; i++) {
            int number = random.nextInt(10); // Generate a random number (0-9)
            sb.append(number);
        }

        // Generate the next 1 capital letter (A-Z)
        char capitalLetter = (char) ('A' + random.nextInt(26));
        sb.append(capitalLetter);

        // Generate the last 4 numbers (0-9)
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(10); // Generate a random number (0-9)
            sb.append(number);
        }

        return sb.toString();
    }
}
