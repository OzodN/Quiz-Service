package quizService.uml;

/**
 * Represents a quiz component in the application.
 * <p>
 * Classes implementing this interface should define how
 * a quiz is displayed and presented to the user.
 * </p>
 */
public interface Quiz {

    /**
     * Displays the quiz to the user.
     * <p>
     * The implementation should handle showing the
     * quiz content (questions/answers) and possibly
     * manage user interaction.
     * </p>
     */
    void show();
}
