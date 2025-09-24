package dev.adamcounihan.hangman.game;

/**
 * Request DTO for submitting a guess.
 */
public class GuessRequest {
    private String letter;

    public String getLetter() { return letter; }
    public void setLetter(String letter) { this.letter = letter; }
}
