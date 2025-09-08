/**
 * Custom error class for API operations
 */
export class GameApiError extends Error {
    constructor(message, code, status, details = null) {
        super(message);
        this.name = 'GameApiError';
        this.code = code;
        this.status = status;
        this.details = details;
        this.timestamp = new Date().toISOString();
    }
}

/**
 * Transforms axios errors into standardized GameApiError
 * @param {import('axios').AxiosError} error - The axios error
 * @param {string} operation - Description of what failed (e.g., "create game")
 * @throws {GameApiError} Always throws a transformed error
 */
export const handleApiError = (error, operation) => {
    // Server responded with error status
    if (error.response) {
        const { status, data } = error.response;

        switch (status) {
            case 400:
                throw new GameApiError(
                    data?.message || 'Invalid request parameters',
                    'VALIDATION_ERROR',
                    status,
                    data
                );
            case 404:
                throw new GameApiError(
                    data?.message || 'Game not found',
                    'NOT_FOUND',
                    status,
                    data
                );
            case 409:
                throw new GameApiError(
                    data?.message || 'Invalid game state',
                    'CONFLICT',
                    status,
                    data
                );
            default:
                throw new GameApiError(
                    data?.message || `Failed to ${operation}`,
                    'SERVER_ERROR',
                    status,
                    data
                );
        }
    }

    // Request made but no response (network issue)
    if (error.request) {
        throw new GameApiError(
            'Cannot connect to game server',
            'NETWORK_ERROR',
            null,
            { originalError: error.message }
        );
    }

    // Request setup failed
    throw new GameApiError(
        `Failed to ${operation}`,
        'CLIENT_ERROR',
        null,
        { originalError: error.message }
    );
};
