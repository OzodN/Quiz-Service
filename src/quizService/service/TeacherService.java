package quizService.service;

import quizService.model.Answer;
import quizService.model.Question;
import quizService.repository.QuestionRepository;

/**
 * Service layer for teachers to manage quiz questions.
 * <p>
 * Provides CRUD operations for {@link Question} objects,
 * including creating, updating, listing, and deleting questions.
 * </p>
 */
public class TeacherService {
    /**
     * Repository instance for storing quiz questions.
     * Uses Singleton pattern.
     */
    private static final QuestionRepository questionRepo = QuestionRepository.getInstance();

    /**
     * Displays all questions and their answers in the repository.
     * <p>
     * Each question is numbered, and each answer is listed
     * with its correctness flag.
     * </p>
     */
    public void list() {
        Question[] questions = questionRepo.listAll();
        if (questions.length == 0) {
            System.out.println("No questions available");
            return;
        }
        for (int i = 0; i < questions.length; i++) {
            Question q = questions[i];
            System.out.printf("%d. %s%n", i + 1, q.getQuestion());

            Answer[] answers = q.getAnswers();
            for (int j = 0; j < answers.length; j++) {
                Answer a = answers[j];
                System.out.printf("  %d) %s[%s]%n", j + 1, a.text(), a.isCorrect());
            }
        }
    }

    /**
     * Deletes a question by its index.
     *
     * @param index 1-based index of the question to delete
     * @return true if deleted successfully, false if no question was found
     */
    public boolean delete(int index) {
        int idx = index - 1;
        Question question = questionRepo.getQuestion(idx);

        if (question == null) {
            System.out.println("No question found with number " + idx);
            return false;
        }
        System.out.printf("Deleting question: %s%n", question.getQuestion());
        return questionRepo.remove(idx);
    }

    /**
     * Updates a question and its answers.
     *
     * @param index       1-based index of the question to update
     * @param newQuestion new text for the question
     * @param newAnswers  array of new answers for the question
     */
    public void update(int index, String newQuestion, Answer[] newAnswers) {
        int idx = index - 1;
        Question oldQuestion = questionRepo.getQuestion(idx);
        if (oldQuestion == null) {
            System.out.println("No question found with number " + idx);
            return;
        }
        if (newQuestion == null || newQuestion.isBlank() ||
            newAnswers == null || newAnswers.length == 0) {
            System.out.println("Question and Answer text cannot be empty.");
            return;
        }

        oldQuestion.setQuestion(newQuestion);

        for (Answer a : newAnswers) {
            oldQuestion.updateAnswer(idx, a);
        }

        boolean updated = questionRepo.update(idx, oldQuestion);
        System.out.println(updated
                ? "Question updated successfully."
                : "Failed to update question.");
    }

    /**
     * Adds a new question with its answers.
     *
     * @param question the {@link Question} to add
     * @param answers  array of {@link Answer} objects for the question
     */
    public void add(Question question, Answer[] answers) {
        if (question == null || answers == null || answers.length == 0) {
            System.out.println("Question or answer cannot be empty or null.");
            return;
        }
        for (Question q : questionRepo.listAll()) {
            if (q.equals(question)) {
                System.out.println("Such a question is already exists.");
                return;
            }
        }
        for (Answer a : answers) {
            question.addAnswer(a);
        }
        questionRepo.add(question);
    }
}
