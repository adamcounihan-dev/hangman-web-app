package dev.adamcounihan.hangman.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Response DTO for game state.
 */
public class GameResponse {
    private String gameId;
    private List<String> maskedTokens;
    private int livesRemaining;
    private String gameStatus;
    private List<String> guesses;

    public static GameResponse from(Game game) {
        GameResponse response = new GameResponse();
        response.gameId = game.getGameId();
        response.maskedTokens = game.getMaskedTokens();
        response.livesRemaining = game.getLivesRemaining();
        response.gameStatus = game.getStatus().toString();
        response.guesses = new ArrayList<>();
        game.getGuessedLetters().forEach(c -> response.guesses.add(c.toString()));
        return response;
    }

    // Getters and setters
    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }
    public List<String> getMaskedTokens() { return maskedTokens; }
    public void setMaskedTokens(List<String> maskedTokens) { this.maskedTokens = maskedTokens; }
    public int getLivesRemaining() { return livesRemaining; }
    public void setLivesRemaining(int livesRemaining) { this.livesRemaining = livesRemaining; }
    public String getGameStatus() { return gameStatus; }
    public void setGameStatus(String gameStatus) { this.gameStatus = gameStatus; }
    public List<String> getGuesses() { return guesses; }
    public void setGuesses(List<String> guesses) { this.guesses = guesses; }
}
