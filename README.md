Explanation of the Code
This Java program is a password generator that allows the user to specify the password length and character types to include (lowercase letters, uppercase letters, numbers, and symbols). The generated password is then saved to a file (tests/output.txt).

1.Constants for Character Sets
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";
**These strings define the possible characters for the password**

2.SecureRandom for Random Generation
    private static final SecureRandom random = new SecureRandom();
//SecureRandom is used instead of Random for better randomness and security.

3.Password Generation Method
    public static String generatePassword(int length, boolean useLower, boolean useUpper, boolean useNumbers, boolean useSymbols) {
    StringBuilder charPool = new StringBuilder();
//A StringBuilder is used to collect all possible characters based on user choices.
//If no character types are selected, an exception is thrown.
        if (charPool.length() == 0) throw new IllegalArgumentException("At least one character type must be selected");
//A new password is built by randomly selecting characters from charPool.
    StringBuilder password = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
        password.append(charPool.charAt(random.nextInt(charPool.length())));
    }
    return password.toString();
//The method returns the generated password.

4.User Input and Password Generation
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter password length: ");
    int length = scanner.nextInt();
//The program prompts the user for the desired password length and character options.
    System.out.print("Include lowercase letters? (true/false): ");
    boolean useLower = scanner.nextBoolean();
//Similar prompts are used for uppercase letters, numbers, and symbols.
    String password = generatePassword(length, useLower, useUpper, useNumbers, useSymbols);
//The password is generated based on the user's preferences.
    System.out.println("Generated Password: " + password);

5.Saving the Password to a file 
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("tests/output.txt"))) {
        writer.write(password);
    } catch (IOException e) {
        System.out.println("Error writing to output file.");
    }
//The password is written to tests/output.txt using BufferedWriter.
//If an error occurs (e.g., the file cannot be created), an error message is displayed.







