package quizService.ui;

import quizService.model.Question;
import quizService.model.Answer;
import quizService.model.User;
import quizService.repository.QuestionRepository;
import quizService.service.QuizService;
import quizService.util.InputUtil;
import quizService.uml.Menu;

import java.util.Scanner;

/**
 * The {@code StudentMenu} class provides the console-based menu system
 * for students to interact with the quiz application.
 * <p>
 * Students can:
 * <ul>
 *   <li>Start and take quizzes</li>
 *   <li>View questions and multiple-choice answers</li>
 *   <li>Submit their answers and view results</li>
 * </ul>
 * </p>
 */
public class StudentMenu implements Menu {
    /**
     * Shared scanner instance for reading user input.
     */
    private static final Scanner sc = InputUtil.getScanner();
    /**
     * Utility class for handling validated input operations.
     */
    private static final InputUtil inputUtil = InputUtil.getInstance();
    /**
     * Repository instance for accessing stored questions.
     */
    private static final QuestionRepository questionRepository = QuestionRepository.getInstance();
    /**
     * Service responsible for running quizzes and calculating results.
     */
    private static final QuizService quizService = new QuizService(questionRepository);
    /**
     * The currently logged-in user associated with this menu.
     */
    private final User currentUser;

    /**
     * Constructs a {@code StudentMenu} for the given user.
     *
     * @param user the authenticated student user
     */
    public StudentMenu(User user) {
        this.currentUser = user;
    }

    /**
     * Starts the student menu interaction loop.
     * This method delegates to {@link #studentMenu()}.
     */
    @Override
    public void start() {
        studentMenu();
    }

    /**
     * Displays the student menu options in a loop until the user exits.
     * Options:
     * <ul>
     *   <li>Start Quiz</li>
     *   <li>Exit</li>
     * </ul>
     */
    private void studentMenu() {
        while (true) {
            System.out.println("""
                    
                    ==== STUDENT MENU ====\
                    
                    1. Start Quiz\
                    
                    0. Exit\
                    
                    Choose: \s""");

            switch (inputUtil.getIntInput()) {
                case 1 -> runQuiz();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    /**
     * Runs the quiz for the current user.
     * <p>
     * The method:
     * <ol>
     *   <li>Retrieves all available questions from the repository</li>
     *   <li>Prompts the student to answer each question</li>
     *   <li>Collects chosen answers</li>
     *   <li>Submits answers to the {@link QuizService}</li>
     *   <li>Displays the result</li>
     * </ol>
     * </p>
     */
    private void runQuiz() {
        Question[] questions = questionRepository.listAll();
        if (questions == null || questions.length == 0) {
            System.out.println("No questions yet.");
            return;
        }
        int[] chosenAnswers = new int[questions.length];
        System.out.println("=== Test is started for " + currentUser.username() + " ===");
        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            if (q == null) continue;
            System.out.printf("\n%d. %s\n", (i + 1), q.getQuestion());
            Answer[] answers = q.getAnswers();
            int answerCount = q.getAnswerCount();
            for (int j = 0; j < answerCount; j++) {
                Answer a = answers[j];
                String answerText = (a != null) ? a.text() : "(empty answer)";
                System.out.printf(" %d. %s\n", (j + 1), answerText);
            }
            chosenAnswers[i] = InputUtil.readInt("Choose option: ", 1, answerCount);
        }
        System.out.println(quizService.startQuiz(currentUser, chosenAnswers));
    }
}
