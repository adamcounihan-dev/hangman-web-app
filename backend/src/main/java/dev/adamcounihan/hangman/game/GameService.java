package dev.adamcounihan.hangman.game;

import dev.adamcounihan.hangman.word.Word;
import dev.adamcounihan.hangman.word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service for managing hangman game sessions.
 */
@Service
public class GameService {
    private final WordService wordService;
    private final Map<String, Game> activeGames = new ConcurrentHashMap<>();

    @Autowired
    public GameService(WordService wordService) {
        this.wordService = wordService;
    }

    /**
     * Creates a new game with the specified difficulty and category.
     *
     * @param difficulty the difficulty level
     * @param category the word category
     * @return the newly created game
     */
    public Game createGame(String difficulty, String category) {
        String gameId = UUID.randomUUID().toString();
        Word word = wordService.getRandomWord(difficulty, category);
        Game game = new Game(gameId, word);
        activeGames.put(gameId, game);
        return game;
    }

    /**
     * Gets an existing game by its ID.
     *
     * @param gameId the game identifier
     * @return the game
     * @throws GameNotFoundException if game doesn't exist
     */
    public Game getGame(String gameId) {
        Game game = activeGames.get(gameId);
        if (game == null) {
            throw new GameNotFoundException("Game not found with ID: " + gameId);
        }
        return game;
    }

    /**
     * Processes a letter guess for a game.
     *
     * @param gameId the game identifier
     * @param letter the guessed letter
     * @return the updated game state
     * @throws GameNotFoundException if game doesn't exist
     */
    public Game makeGuess(String gameId, char letter) {
        Game game = getGame(gameId);
        game.makeGuess(letter);  // Domain model handles the logic
        return game;
    }
}
