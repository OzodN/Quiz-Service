package quizService.model;

import quizService.uml.BaseClass;

/**
 * Represents a quiz question with multiple possible answers.
 */
public class Question implements BaseClass {
    private String question;
    private final Answer[] answers;
    private int answerCount;

    /**
     * Constructs a question with a given text and maximum number of answers.
     *
     * @param question      the question text
     * @param answers       array of answers to the current question 1. answer 2. answer 3. answer 4. answer
     */
    public Question(String question, Answer[] answers) {
        this.question = question;
        this.answers = answers;
    }

    /**
     * Adds a new answer to the question.
     *
     * @param answer the answer to add
     */
    public void addAnswer(Answer answer) {
        if (answerCount >= answers.length) return;
        answers[answerCount++] = answer;
    }

    public void updateAnswer(int index, Answer newAnswer) {
        if (index >= 0 && index < answerCount) {
            answers[index] = newAnswer;
        }
    }

    /**
     * Number of answers currently assigned to this question
     * @return count of answers
     */
    public int getAnswerCount() {
        return answerCount;
    }

    /**
     * The text of the question
     * @return questions
     */
    public String getQuestion() {
        return question;
    }

    /**
     * all possible answers for this question
     * @return answers
     */
    public Answer[] getAnswers() {
        return answers;
    }

    /**
     * Set a new question instead of old one
     * @param question a new one that would be set
     */
    public void setQuestion(String question) {
        this.question = question;
    }
}
