package quizService.model;

import quizService.uml.BaseClass;

/**
 * Represents the role of a user in the system.
 * A role defines access level and available actions.
 */
public enum Role implements BaseClass {
    /** Teacher, can manage quiz questions. */
    TEACHER,
    /** Regular student, can take quizzes. */
    STUDENT
}
