package quizService.repository;

import quizService.model.User;

/**
 * Repository for storing and managing {@link User} objects.
 * <p>
 * Provides CRUD operations for users without using collections.
 * Uses an array-based storage that grows dynamically when needed.
 * This repository follows the Singleton pattern.
 * </p>
 */
public class UserRepository {

    /**
     * Singleton instance of {@code UserRepository}.
     */
    private static final UserRepository instance = new UserRepository();

    /**
     * Internal array of users.
     */
    private User[] users = new User[10];

    /**
     * Current number of stored users.
     */
    private int count = 0;

    /**
     * Returns the single instance of this repository.
     *
     * @return singleton {@code UserRepository} instance
     */
    public synchronized static UserRepository getInstance() {
        return instance;
    }

    /**
     * Ensures the internal array has enough capacity.
     * Doubles the size if the array is full.
     */
    private void ensureCapacity() {
        if (count < users.length) return;
        User[] newArr = new User[users.length * 2];
        System.arraycopy(users, 0, newArr, 0, users.length);
        users = newArr;
    }

    /**
     * Checks if a user exists with given username and password.
     *
     * @param username username to check
     * @param password password to check
     * @return true if such user exists, false otherwise
     */
    public boolean exists(String username, String password) {
        for (int i = 0; i < count; i++) {
            User u = users[i];
            if (u != null && u.username().equals(username) && u.password().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new user to the repository.
     *
     * @param user {@link User} to add
     * @return true if added successfully,
     *         false if a user with same username and password already exists
     */
    public boolean add(User user) {
        if (exists(user.username(), user.password())) return false;
        ensureCapacity();
        users[count++] = user;
        return true;
    }

    /**
     * Removes a user by username.
     *
     * @param username username of the user to remove
     * @return true if removed successfully, false if not found
     */
    public boolean remove(String username) {
        for (int i = 0; i < count; i++) {
            if (users[i] != null && users[i].username().equals(username)) {
                for (int j = i; j < count -1 ; j++) {
                    users[j] = users[j + 1];
                }
                users[--count] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a user by username.
     *
     * @param username username of the user
     * @return {@link User} if found, otherwise null
     */
    public User findByUsername(String username) {
        for (int i = 0; i < count; i++) {
            if (users[i] != null && users[i].username().equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    /**
     * Returns all users as a new array copy.
     *
     * @return copy of stored users
     */
    public User[] getAll() {
        User[] copy = new User[count];
        System.arraycopy(users, 0, copy, 0, count);
        return copy;
    }
}
