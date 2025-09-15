package dev.adamcounihan.hangman.game;

import dev.adamcounihan.hangman.word.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a single game session of hangman.
 */
public class Game {
    private static final int DEFAULT_LIVES = 6;

    private final String gameId;
    private final Word word;
    private final Set<Character> guessedLetters; // insertion-ordered
    private int livesRemaining;
    private GameStatus status;

    public enum GameStatus {
        IN_PROGRESS,
        WON,
        LOST
    }

    public Game(String gameId, Word word) {
        this.gameId = Objects.requireNonNull(gameId, "gameId must not be null").trim();
        this.word = Objects.requireNonNull(word, "word must not be null");
        this.guessedLetters = new LinkedHashSet<>();
        this.livesRemaining = DEFAULT_LIVES;
        this.status = GameStatus.IN_PROGRESS;

        if (this.gameId.isEmpty()) {
            throw new IllegalArgumentException("gameId must not be empty");
        }
    }

    /**
     * Process a letter guess.
     *
     * @return true if the guess was correct, false otherwise
     * @throws IllegalStateException    if the game is already finished
     * @throws IllegalArgumentException if the guess is invalid or already made
     */
    public boolean makeGuess(char letter) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already finished");
        }

        char upper = Character.toUpperCase(letter);
        // Validate only ASCII A-Z are allowed
        if (upper < 'A' || upper > 'Z') {
            throw new IllegalArgumentException("Guess must be an ASCII A–Z letter");
        }

        if (guessedLetters.contains(upper)) {
            throw new IllegalArgumentException("Letter already guessed: " + upper);
        }

        guessedLetters.add(upper);

        boolean correct = word.getText().indexOf(upper) >= 0;

        if (!correct) {
            livesRemaining--;
            if (livesRemaining <= 0) {
                status = GameStatus.LOST;
            }
        } else if (isWordComplete()) {
            status = GameStatus.WON;
        }

        return correct;
    }

    /**
     * Gets the word with unguessed letters as underscores, returned as a list.
     * Letters appear as uppercase, spaces are preserved.
     * Example: "UNITED KINGDOM" with guesses ['I','N','G'] returns
     * ["_","N","I","T","E","D"," ","K","I","N","G","D","O","M"]
     *
     * @return unmodifiable list of single-character strings
     */
    public List<String> getMaskedTokens() {
        List<String> tokens = new ArrayList<>();
        String wordText = word.getText();

        for (char c : wordText.toCharArray()) {
            if (!Character.isLetter(c)) {
                tokens.add(String.valueOf(c));
            } else {
                if (guessedLetters.contains(c)) {
                    tokens.add(String.valueOf(c));
                } else {
                    tokens.add("_");
                }
            }
        }

        return Collections.unmodifiableList(tokens);
    }

    private boolean isWordComplete() {
        String wordText = word.getText();
        for (char c : wordText.toCharArray()) {
            if (Character.isLetter(c) && !guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    // Getters
    public String getGameId() {
        return gameId;
    }

    public Word getWord() {
        return word;
    }

    /**
     * Returns an unmodifiable view of guessed letters in insertion order.
     */
    public Set<Character> getGuessedLetters() {
        return Collections.unmodifiableSet(guessedLetters);
    }

    public int getLivesRemaining() {
        return livesRemaining;
    }

    public GameStatus getStatus() {
        return status;
    }
}
