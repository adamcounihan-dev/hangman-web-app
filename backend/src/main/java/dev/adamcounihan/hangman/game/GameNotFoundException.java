package dev.adamcounihan.hangman.game;

/**
 * Exception thrown when a requested game cannot be found.
 */
public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String message) {
        super(message);
    }
}
