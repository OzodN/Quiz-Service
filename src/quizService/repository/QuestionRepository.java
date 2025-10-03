package quizService.repository;

import quizService.model.Question;

/**
 * Repository for storing and managing {@link Question} objects.
 * <p>
 * Implements a simple in-memory storage using an array.
 * Automatically resizes the array if capacity is exceeded.
 * This repository follows the Singleton pattern.
 * </p>
 */
public class QuestionRepository {
    /**
     * Singleton instance of {@code QuestionRepository}.
     */
    private static final QuestionRepository instance = new QuestionRepository();
    /**
     * Internal array of questions.
     */
    private Question[] questions = new Question[10];
    /**
     * Current number of stored questions.
     */
    private int count = 0; // current amount of questions

    /**
     * Private constructor to enforce Singleton pattern.
     */
    private QuestionRepository(){}

    /**
     * Returns the single instance of this repository.
     *
     * @return singleton {@code QuestionRepository} instance
     */
    public synchronized static QuestionRepository getInstance() {
        return instance;
    }

    /**
     * Checking and expending an array if it's full.
     */
    private void ensureCapacity() {
        if (count < questions.length) return;
        Question[] newArr = new Question[questions.length * 2];
        System.arraycopy(questions, 0, newArr, 0, questions.length);
        questions = newArr;
    }

    /**
     * Addict a new question.
     *
     * @param question object of question
     * @return true If added successfully
     */
    public boolean add(Question question) {
        ensureCapacity();
        questions[count++] = question;
        return true;
    }

    /**
     * Delete question by Index.
     *
     * @param index index (0-based)
     * @return true if deleted successfully.
     */
    public boolean remove(int index) {
        if (index < 0 || index >= count) return false;
        for (int i = index; i < count - 1; i++) {
            questions[i] = questions[i + 1];
        }
        questions[--count] = null;
        return true;
    }

    /**
     * Get a question by index.
     *
     * @param index index (0-based)
     * @return Question or null if index is invalid
     */
    public Question getQuestion(int index) {
        if (index < 0 || index >= count) return null;
        return questions[index];
    }

    /**
     * Update a question by index.
     *
     * @param index    index (0-based)
     * @param question new question
     * @return true if updated successfully.
     */
    public boolean update(int index, Question question) {
        if (index < 0 || index >= count) return false;
        questions[index] = question;
        return true;
    }

    /**
     * Display all questions as an array.
     * Important: Array is copied in order to not crush inner statement.
     *
     * @return array of questions
     */
    public Question[] listAll() {
        Question[] copy = new Question[count];
        System.arraycopy(questions, 0, copy, 0, count);
        return copy;
    }

    /**
     * @return amount of questions in repository
     */
    public int size() {
        return count;
    }
}
