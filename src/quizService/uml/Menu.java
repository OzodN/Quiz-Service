package quizService.uml;

/**
 * Represents a generic menu in the application.
 * <p>
 * Classes that implement this interface define their own
 * menu structure and logic, which is started by calling {@link #start()}.
 * </p>
 */
public interface Menu {

    /**
     * Starts the menu loop or action.
     * <p>
     * The implementation should display menu options
     * and handle user input until the menu is exited.
     * </p>
     */
    void start();
}
