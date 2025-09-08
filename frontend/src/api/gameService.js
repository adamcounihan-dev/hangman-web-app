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
 * @returns {Promise<{gameId: string, maskedWord: string, livesRemaining: number, gameStatus: string}>}
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
 * @returns {Promise<{gameId: string, maskedWord: string, livesRemaining: number, gameStatus: string, guesses: string[]}>}
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
 * @param {string} letter - The guessed letter
 * @returns {Promise<{gameId: string, maskedWord: string, livesRemaining: number, gameStatus: string, guesses: string[]}>}
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
