package quizService.model;

import quizService.uml.BaseClass;
/**
 * Represents a system user with username, password, and role.
 * A user can be either a {@link Role#STUDENT} or a {@link Role#TEACHER}.
 * @param  role current role of user's
 * @param username user's name
 * @param password password of username
 */
public record User(Role role, String username, String password) implements BaseClass {}
