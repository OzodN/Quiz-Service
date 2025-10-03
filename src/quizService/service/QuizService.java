package quizService.service;

import quizService.model.Answer;
import quizService.model.Question;
import quizService.model.User;
import quizService.model.QuizResult;
import quizService.repository.QuestionRepository;

/**
 * Service layer responsible for quiz execution.
 * <p>
 * Handles the logic of starting a quiz for a given user,
 * verifying answers, and producing {@link QuizResult}.
 * </p>
 */
public class QuizService {
    /**
     * Repository for storing and retrieving quiz questions.
     */
    private final QuestionRepository questionRepository;

    /**
     * Constructs a new {@code QuizService} with a provided {@link QuestionRepository}.
     *
     * @param questionRepository repository instance
     */
    public QuizService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Starts a quiz session for a user.
     * <p>
     * Iterates through all available questions, validates chosen answers,
     * and counts how many were correct.
     * </p>
     *
     * @param user          the user who is taking the quiz
     * @param chosenAnswers array of chosen indices (1-based) for each question
     * @return {@link QuizResult} object containing the user, total questions, and correct answers
     */
    public QuizResult startQuiz(User user, int[] chosenAnswers) {
        Question[] questions = questionRepository.listAll();
        int questionCount = (questions == null) ? 0 : questions.length;

        if (questionCount == 0) {
            return new QuizResult(user, 0, 0);
        }

        int correctCount = 0;
        for (int i = 0; i < questionCount; i++) {
            Question q = questions[i];
            if (q == null) continue;

            int chosenIndex = chosenAnswers[i]; // Index chosen by user
            Answer[] answers = q.getAnswers();

            if (answers != null
                && chosenIndex > 0
                && chosenIndex <= q.getAnswerCount()
                && answers[chosenIndex - 1] != null
                && answers[chosenIndex - 1].isCorrect()) {
                correctCount++;
            }
        }

        return new QuizResult(user, correctCount, questionCount);
    }
}
