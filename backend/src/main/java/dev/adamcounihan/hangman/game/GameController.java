package dev.adamcounihan.hangman.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:5173") // Vite default port
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameResponse> createGame(@RequestBody CreateGameRequest request) {
        Game game = gameService.createGame(request.getDifficulty(), request.getCategory());
        return ResponseEntity.status(HttpStatus.CREATED).body(GameResponse.from(game));
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameResponse> getGame(@PathVariable String gameId) {
        Game game = gameService.getGame(gameId);
        return ResponseEntity.ok(GameResponse.from(game));
    }

    @PostMapping("/{gameId}/guesses")
    public ResponseEntity<GameResponse> makeGuess(
            @PathVariable String gameId,
            @RequestBody GuessRequest request) {
        Game game = gameService.makeGuess(gameId, request.getLetter().charAt(0));
        return ResponseEntity.ok(GameResponse.from(game));
    }
}
