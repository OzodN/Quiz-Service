package quizService.model;

/**
 * Represents results of the quiz
 *
 * @param user a student taking a quiz
 * @param correctAnswers amount of correct answers
 * @param totalQuestions all questions student answered
 */
public record QuizResult(User user, int correctAnswers, int totalQuestions) {

    @Override
    public String toString() {
        return String.format(
                "\n=== Test results for %s ===\nCorrect answers: %d / %d\n=== Test finished ===",
                user.username(), correctAnswers, totalQuestions
        );
    }
}
