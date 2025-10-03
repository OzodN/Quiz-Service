package quizService.service;

import quizService.model.Role;
import quizService.model.User;
import quizService.repository.UserRepository;

/**
 * Service layer responsible for user-related operations.
 * <p>
 * Provides methods for registering and authenticating users.
 * Works with {@link UserRepository} for persistence.
 * Implements Singleton pattern.
 * </p>
 */
public class UserService {

    /**
     * Singleton instance of {@code UserService}.
     */
    private static final UserService instance = new UserService();

    /**
     * Reference to {@link UserRepository}.
     */
    private static final UserRepository userRepo = UserRepository.getInstance();

    /**
     * Private constructor to enforce Singleton pattern.
     */
    private UserService() {}

    /**
     * Returns the singleton instance of {@code UserService}.
     *
     * @return singleton instance
     */
    public static UserService getInstance() {
        return instance;
    }

    /**
     * Registers a new user.
     * <p>
     * Validates role, username, and password before creating a user.
     * Ensures that username is unique and password has minimum length.
     * </p>
     *
     * @param role     role of the new user ({@link Role})
     * @param username username of the new user
     * @param password password of the new user (must be at least 6 characters)
     * @return true if registered successfully, false otherwise
     */
    public final boolean register(Role role, String username, String password) {
        if (role == null || role.name().trim().isBlank() ||
            username == null || username.isBlank() ||
            password == null || password.isBlank()) {
            System.out.println("Registration failed: empty username/password");
            return false;
        }
        String trimmedUsername = username.trim();

        if (password.length() < 6) {
            System.out.println("Registration failed: password must be > 6 chars");
            return false;
        }

        if (userRepo.exists(trimmedUsername, password)) {
            System.out.println("Registration failed: username already exists");
            return false;
        }


        User newUser = new User(role, trimmedUsername, password);
        return userRepo.add(newUser);
    }

    /**
     * Logs a user into the system.
     * <p>
     * Checks username and password validity.
     * If a user is found, returns the corresponding {@link User}.
     * Otherwise, returns {@code null}.
     * </p>
     *
     * @param username username of the user
     * @param password password of the user
     * @return {@link User} if login succeeds, {@code null} otherwise
     */
    public final User login(String username, String password) {
        if (username == null || username.isBlank() ||
            password == null || password.isBlank()) {
            System.out.println("Logging In failed: empty username/password");
            return null;
        }

        String trimmedUsername = username.trim();

        for (User u : userRepo.getAll()) {
            if (userRepo.exists(trimmedUsername, password)) {
                System.out.printf("%s logged in\n", u.username());
                return u;
            }
        }
        System.out.println("Login failed for user: " + trimmedUsername);
        return null;
    }
}
