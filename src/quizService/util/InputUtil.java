package quizService.util;

import java.util.Scanner;

/**
 * Utility class for handling user input in the console.
 * <p>
 * Provides helper methods for reading integers and strings
 * safely, ensuring validation and avoiding common input errors.
 * </p>
 *
 * <p>
 * This class is implemented as a singleton with a shared
 * {@link Scanner} instance to avoid conflicts and resource leaks.
 * </p>
 */
public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InputUtil instance = new InputUtil();

    private InputUtil() {}


    /**
     * Returns the shared {@link Scanner} instance.
     *
     * @return the global scanner
     */
    public synchronized static Scanner getScanner() {
        return scanner;
    }

    /**
     * Returns the singleton instance of {@code InputUtil}.
     *
     * @return the singleton instance
     */
    public synchronized static InputUtil getInstance() {
        return instance;
    }

    /**
     * Reads an integer from the console with validation.
     * If the input is not a number, prompts the user until
     * a valid integer is entered.
     *
     * @return the validated integer value
     */
    public int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    /**
     * Prompts the user with a message and reads a string input.
     *
     * @param message the prompt message
     * @return the user input string
     */
    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Reads an integer within a specified range [min, max].
     * Keeps prompting the user until a valid number is entered.
     *
     * @param message the prompt message
     * @param min     the minimum allowed value
     * @param max     the maximum allowed value
     * @return the validated integer within the range
     */
    public static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.printf("Enter a number between %d and %d\n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
