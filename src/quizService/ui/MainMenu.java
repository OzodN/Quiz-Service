package quizService.ui;

import quizService.service.AuthService;
import quizService.model.Role;
import quizService.model.User;
import quizService.util.InputUtil;
import quizService.uml.Menu;

import java.util.Scanner;

/**
 * The {@code MainMenu} class provides the entry point for the quiz system.
 * <p>
 * It handles user authentication and role-based navigation:
 * <ul>
 *   <li>Registration of new users (Teacher/Student)</li>
 *   <li>Login of existing users</li>
 *   <li>Redirects Teachers to {@link TeacherMenu}</li>
 *   <li>Redirects Students to {@link StudentMenu}</li>
 * </ul>
 * </p>
 */
public class MainMenu implements Menu {
    /**
     * Shared scanner instance for reading input directly from the console.
     */
    private static final Scanner sc = InputUtil.getScanner();
    /**
     * Utility class for validated user input operations.
     */
    private static final InputUtil inputUtil = InputUtil.getInstance();
    /**
     * Service responsible for authentication and user management.
     */
    private static final AuthService authService = new AuthService();
    /**
     * Menu handler for teachers. Invoked when a logged-in user has the role {@link Role#TEACHER}.
     */
    private static final TeacherMenu teacherMenu = new TeacherMenu();

    /**
     * Starts the main menu loop by invoking {@link #mainMenu()}.
     */
    @Override
    public void start() {
        mainMenu();
    }

    /**
     * Displays the main menu options in a loop until exit.
     * Options:
     * <ul>
     *   <li>1 - Registration</li>
     *   <li>2 - Login</li>
     *   <li>0 - Exit</li>
     * </ul>
     */
    private static void mainMenu() {
        while (true) {
            System.out.print("""
                    
                    ==== MAIN MENU ====\
                    
                    1. Registration\
                    
                    2. Login\
                    
                    0. Exit\
                    
                    Choose: \s""");

            switch (inputUtil.getIntInput()) {
                case 1 -> register();
                case 2 -> login();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option. try again.");
            }
        }
    }

    /**
     * Handles user login.
     * <p>
     * Prompts the user for username and password, and authenticates
     * using {@link AuthService#login(String, String)}.
     * </p>
     * <p>
     * On successful login:
     * <ul>
     *   <li>If role = {@link Role#TEACHER} → launches {@link TeacherMenu}</li>
     *   <li>If role = {@link Role#STUDENT} → launches {@link StudentMenu}</li>
     * </ul>
     * </p>
     * If authentication fails, displays an error message.
     */
    private static void login() {
        String username = inputUtil.prompt("Enter username: ");
        String password = inputUtil.prompt("Enter password: ");
        User user = authService.login(username, password);
        if (user == null) {
            System.out.println("Invalid credentials. Try again.");
            return;
        }
        System.out.println("Welcome " + user.username());
        System.out.println("Role: " + user.role());
        if (user.role().equals(Role.TEACHER)) teacherMenu.start();
        if (user.role().equals(Role.STUDENT)) new StudentMenu(user).start();
    }

    /**
     * Handles user registration.
     * <p>
     * Prompts the user for role (Teacher/Student), username, and password.
     * Calls {@link AuthService#register(Role, String, String)} to create the new account.
     * </p>
     * If registration is successful:
     * <ul>
     *   <li>Displays a success message</li>
     *   <li>Redirects user to {@link #postRegistrationMenu()}</li>
     * </ul>
     * If registration fails, offers retry or cancellation.
     */
    private static void register() {
        while (true) {
            System.out.print("Role TEACHER/STUDENT: ");
            String roleStr = sc.nextLine().trim().toUpperCase();
            Role role;
            if (roleStr.equalsIgnoreCase(Role.TEACHER.name())) {
                role = Role.TEACHER;
            } else if (roleStr.equalsIgnoreCase(Role.STUDENT.name())) {
                role = Role.STUDENT;
            } else {
                System.out.println("Invalid Role. It must be TEACHER or STUDENT");
                continue;
            }
            String username = inputUtil.prompt("Enter username: ");
            String password = inputUtil.prompt("Enter password: ");
            if (authService.register(role, username, password)) {
                System.out.println(roleStr.toUpperCase() + " registered successfully.");
                postRegistrationMenu();
                return;
            }
            System.out.print("Registration failed. Try again? (Y/N): ");
            if (!sc.nextLine().equalsIgnoreCase("Y")) {
                System.out.println("Registration cancelled.");
                return;
            }
        }
    }

    /**
     * Post-registration options menu.
     * <p>
     * After a successful registration, the user can:
     * <ul>
     *   <li>Login immediately</li>
     *   <li>Exit the program</li>
     * </ul>
     * </p>
     */
    private static void postRegistrationMenu() {
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("1. Login");
            System.out.println("0. Exit");
            switch (sc.nextLine()) {
                case "1" -> {
                    login();
                    return;
                }
                case "0" -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
