package quizService.ui;

import quizService.model.Answer;
import quizService.model.Question;
import quizService.service.TeacherService;
import quizService.util.InputUtil;
import quizService.uml.Menu;

import java.util.Scanner;

/**
 * The {@code TeacherMenu} class provides the console-based menu system
 * for teachers to manage quiz content.
 * <p>
 * Teachers can:
 * <ul>
 *   <li>Add new questions with multiple answers</li>
 *   <li>Update existing questions</li>
 *   <li>Delete questions</li>
 *   <li>List all stored questions</li>
 * </ul>
 * </p>
 */
public class TeacherMenu implements Menu {
    /**
     * Shared scanner instance for reading input directly from the console.
     */
    private static final Scanner sc = InputUtil.getScanner();
    /**
     * Utility class instance for handling validated user input.
     */
    private static final InputUtil inputUtil = InputUtil.getInstance();
    /**
     * Service layer for managing quiz questions on behalf of the teacher.
     */
    private static final TeacherService teacherService = new TeacherService();

    /**
     * Starts the teacher menu interaction loop.
     * This method delegates to {@link #teacherMenu()}.
     */
    @Override
    public void start() {
        teacherMenu();
    }

    /**
     * Displays the teacher menu options in a loop until the user exits.
     * Options:
     * <ul>
     *   <li>Add a question</li>
     *   <li>Update an existing question</li>
     *   <li>Delete a question</li>
     *   <li>Display all questions</li>
     *   <li>Exit the menu</li>
     * </ul>
     */
    private void teacherMenu() {
        while (true) {
            System.out.print("""
                    
                    ==== TEACHER MENU ====\
                    
                    1. Add question\
                    
                    2. Update task\
                    
                    3. Delete task\
                    
                    4. display all tasks\
                    
                    0. Exit\
                    
                    Choose: \s""");

            int input = inputUtil.getIntInput();

            switch (input) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> delete();
                case 4 -> list();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input. Try again.");
            }
        }
    }

    /**
     * Displays all existing questions stored in the system.
     */
    private static void list() {
        teacherService.list();
    }


    /**
     * Deletes a question by its index.
     * <p>
     * Prompts the teacher for the index of the question to be removed,
     * and calls the {@link TeacherService#delete(int)} method.
     * </p>
     * If deletion succeeds, a success message is displayed; otherwise,
     * a failure message is shown.
     */
    private static void delete() {
        System.out.println("Enter question index: ");
        int idx = inputUtil.getIntInput();
        System.out.println(teacherService.delete(idx)
                ? "Question deleted successfully."
                : "Failed to delete question.");
    }

    /**
     * Updates an existing question by its index.
     * <p>
     * Prompts the teacher for:
     * <ul>
     *   <li>Index of the question to update</li>
     *   <li>New question text</li>
     *   <li>New set of answers</li>
     * </ul>
     * </p>
     * Calls the {@link TeacherService#update(int, String, Answer[])} method.
     */
    private void update() {
        System.out.println("Enter question index: ");
        int idx = inputUtil.getIntInput();
        System.out.println("Enter new question: ");
        String newQuestion = sc.nextLine();
        Answer[] newAnswers = inputAnswers();
        teacherService.update(idx, newQuestion, newAnswers);
    }

    /**
     * Adds a new question to the system.
     * <p>
     * Prompts the teacher for:
     * <ul>
     *   <li>Question text</li>
     *   <li>Four possible answers, each with correctness flag</li>
     * </ul>
     * </p>
     * Calls the {@link TeacherService#add(Question, Answer[])} method.
     */
    private void add() {
        System.out.print("Enter a question: ");
        String question = sc.nextLine();
        Answer[] answers = inputAnswers();
        teacherService.add(new Question(question, answers), answers);
    }

    /**
     * Reads four possible answers from the console.
     * <p>
     * For each answer, the teacher provides:
     * <ul>
     *   <li>Answer text</li>
     *   <li>A boolean indicating if the answer is correct</li>
     * </ul>
     * </p>
     *
     * @return an array of four {@link Answer} objects
     */
    private static Answer[] inputAnswers() {
        Answer[] newAnswers = new Answer[4];
        System.out.println("Enter an answers and its correction (answer true/false) ");
        for (int i = 0; i < newAnswers.length; i++) {
            System.out.printf("%d. Answer: ", i+1);
            String answer = sc.nextLine();
            System.out.print("   Correct (true/false): ");
            boolean isCorrect = sc.nextBoolean();
            sc.nextLine();
            newAnswers[i] = new Answer(answer, isCorrect);
        }
        return newAnswers;
    }
}
