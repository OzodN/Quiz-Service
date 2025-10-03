package quizService.service;

import quizService.model.Role;
import quizService.model.User;

/**
 * Authentication service for handling user registration and login.
 * <p>
 * Serves as a façade over {@link UserService}, providing
 * simplified access to authentication functionality.
 * </p>
 */
public class AuthService {

    /**
     * Reference to {@link UserService} singleton.
     */
private static final UserService userService = UserService.getInstance();

    /**
     * Registers a new user in the system.
     *
     * @param role     role of the new user ({@link Role})
     * @param username username of the new user
     * @param password password of the new user
     * @return true if successfully registered, false otherwise
     */
    public final boolean register(Role role, String username, String password){
        return userService.register(role, username, password);
    }

    /**
     * Attempts to log a user into the system.
     *
     * @param username username of the user
     * @param password password of the user
     * @return {@link User} if login is successful, otherwise {@code null}
     */
    public final User login(String username, String password){
        return userService.login(username, password);
    }

}
