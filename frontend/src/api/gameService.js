import axios from 'axios';
import config from '../config';
import { handleApiError } from './errors';

const api = axios.create({
    baseURL: `${config.BACKEND_URL}/api`,
    timeout: config.API_TIMEOUT,
    headers: config.headers
});

/**
 * Creates a new hangman game
 * @param {'easy'|'medium'|'hard'} difficulty - Difficulty level
 * @param {'animals'|'countries'|'food'|'all'} category - Word category
 * @returns {Promise<{
 *   gameId: string,           // Unique identifier for the game session
 *   maskedWord: string,        // Initial masked word (e.g., "_ _ _ _")
 *   livesRemaining: number,    // Starting lives
 *   gameStatus: 'IN_PROGRESS'  // New games always start as IN_PROGRESS
 * }>}
 * @throws {GameApiError} Structured error with code, status, and message
 */
export const createNewGame = async (difficulty, category) => {
    try {
        const response = await api.post('/games', { difficulty, category });
        return response.data;
    } catch (error) {
        handleApiError(error, 'create new game');
    }
};

/**
 * Fetches the current state of a hangman game
 * @param {string} gameId - The ID of the game to fetch
 * @returns {Promise<{
 *   gameId: string,                        // Game identifier
 *   maskedWord: string,                    // Current word state (e.g., "E _ E P H A N T")
 *   livesRemaining: number,                // Lives left
 *   gameStatus: 'IN_PROGRESS'|'WON'|'LOST', // Current game status
 *   guesses: string[]                      // Array of guessed letters (e.g., ["E", "A", "X"])
 * }>}
 * @throws {GameApiError} Structured error with code, status, and message
 */
export const getGameState = async (gameId) => {
    try {
        const response = await api.get(`/games/${gameId}`);
        return response.data;
    } catch (error) {
        handleApiError(error, 'fetch game state');
    }
};

/**
 * Submits a guess for a specific hangman game
 * @param {string} gameId - The ID of the game
 * @param {string} letter - The guessed letter (single character, case-insensitive)
 * @returns {Promise<{
 *   gameId: string,                        // Game identifier
 *   maskedWord: string,                    // Updated word state after guess
 *   livesRemaining: number,                // Updated lives (decrements if wrong)
 *   gameStatus: 'IN_PROGRESS'|'WON'|'LOST', // Updated status (may change to WON/LOST)
 *   guesses: string[]                      // Updated array including new guess
 * }>}
 * @throws {GameApiError} Structured error with code, status, and message
 */
export const makeGuess = async (gameId, letter) => {
    try {
        const response = await api.post(`/games/${gameId}/guesses`, { letter });
        return response.data;
    } catch (error) {
        handleApiError(error, 'submit guess');
    }
};
