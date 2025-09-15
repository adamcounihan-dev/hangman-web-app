package dev.adamcounihan.hangman.word;

import java.util.Locale;
import java.util.Objects;

/**
 * Represents a word in the hangman game with its metadata.
 * The text is stored in canonical uppercase form.
 */
public class Word {
    private final String text;
    private final String category;
    private final String difficulty;

    public Word(String text, String category, String difficulty) {
        this.text = Objects.requireNonNull(text, "text must not be null")
                .trim()
                .toUpperCase(Locale.ROOT);
        this.category = Objects.requireNonNull(category, "category must not be null").trim();
        this.difficulty = Objects.requireNonNull(difficulty, "difficulty must not be null").trim();

        if (this.text.isEmpty()) {
            throw new IllegalArgumentException("Word text must not be empty");
        }
        if (this.category.isEmpty()) {
            throw new IllegalArgumentException("category must not be empty");
        }
        if (this.difficulty.isEmpty()) {
            throw new IllegalArgumentException("difficulty must not be empty");
        }
    }

    /**
     * Returns the uppercase text of this word.
     * Example: "UNITED KINGDOM"
     */
    public String getText() {
        return text;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word word)) return false;
        return text.equals(word.text)
                && category.equals(word.category)
                && difficulty.equals(word.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, category, difficulty);
    }

    @Override
    public String toString() {
        return "Word{" +
                "text='" + text + '\'' +
                ", category='" + category + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}

