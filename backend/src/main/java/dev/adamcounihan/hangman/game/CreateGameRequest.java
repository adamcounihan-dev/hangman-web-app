package dev.adamcounihan.hangman.game;

/**
 * Request DTO for creating a new game.
 */
public class CreateGameRequest {
    private String difficulty;
    private String category;

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
