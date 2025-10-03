package quizService.model;

import quizService.uml.BaseClass;

/**
 * Represents a possible answer to a quiz question.
 * Each answer contains a text and a flag indicating if it is correct.
 * @param text current question
 * @param isCorrect a flag that describes its correction
 */
public record Answer(String text, boolean isCorrect) implements BaseClass {}
