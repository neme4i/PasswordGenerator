import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length, boolean useLower, boolean useUpper, boolean useNumbers, boolean useSymbols) {
        StringBuilder charPool = new StringBuilder();
        if (useLower) charPool.append(LOWERCASE);
        if (useUpper) charPool.append(UPPERCASE);
        if (useNumbers) charPool.append(NUMBERS);
        if (useSymbols) charPool.append(SYMBOLS);

        if (charPool.length() == 0) throw new IllegalArgumentException("At least one character type must be selected");

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password length: ");
        int length = scanner.nextInt();
        System.out.print("Include lowercase letters? (true/false): ");
        boolean useLower = scanner.nextBoolean();
        System.out.print("Include uppercase letters? (true/false): ");
        boolean useUpper = scanner.nextBoolean();
        System.out.print("Include numbers? (true/false): ");
        boolean useNumbers = scanner.nextBoolean();
        System.out.print("Include symbols? (true/false): ");
        boolean useSymbols = scanner.nextBoolean();

        String password = generatePassword(length, useLower, useUpper, useNumbers, useSymbols);
        System.out.println("Generated Password: " + password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tests/output.txt"))) {
            writer.write(password);
        } catch (IOException e) {
            System.out.println("Error writing to output file.");
        }
    }
}
