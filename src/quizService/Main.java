package quizService;

import quizService.ui.MainMenu;

/**
 * Entry point for the Quiz Service application.
 * <p>
 * The application starts by displaying the {@link MainMenu},
 * where users can register, log in, and navigate based on their roles
 * (Teacher or Student).
 * </p>
 */
public class Main {
    /**
     * The main method that launches the Quiz Service application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new MainMenu().start();
    }
}
