/**
 * Gets the backend URL from environment or defaults to localhost
 * @returns {string} The backend API URL
 * @throws {Error} If VITE_BACKEND_URL is not set in production
 */
function getBackendUrl() {
    const url = import.meta.env.VITE_BACKEND_URL;
    if (import.meta.env.PROD && !url) {
        throw new Error('VITE_BACKEND_URL must be set in production');
    }
    return url || 'http://localhost:8080';
}

/**
 * API client configuration
 * @readonly
 */
const config = {
    BACKEND_URL: getBackendUrl(), // API server URL
    API_TIMEOUT: 10000, // Request timeout in milliseconds
    headers: {
        'Content-Type': 'application/json',
    }
};

export default Object.freeze(config);
