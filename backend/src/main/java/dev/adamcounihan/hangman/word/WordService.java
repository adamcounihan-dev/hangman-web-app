package dev.adamcounihan.hangman.word;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Service for loading and selecting words from JSON data.
 */
@Service
public class WordService {
    private final Map<String, Map<String, List<Word>>> wordsByCategory = new HashMap<>();
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void loadWords() {
        try {
            ClassPathResource resource = new ClassPathResource("words.json");
            JsonNode root = objectMapper.readTree(resource.getInputStream());
            JsonNode categories = root.get("categories");

            Iterator<String> categoryNames = categories.fieldNames();
            while (categoryNames.hasNext()) {
                String category = categoryNames.next();
                JsonNode difficulties = categories.get(category);

                Map<String, List<Word>> wordsByDifficulty = new HashMap<>();

                Iterator<String> difficultyNames = difficulties.fieldNames();
                while (difficultyNames.hasNext()) {
                    String difficulty = difficultyNames.next();
                    List<Word> words = new ArrayList<>();

                    JsonNode wordArray = difficulties.get(difficulty);
                    for (JsonNode wordNode : wordArray) {
                        String text = wordNode.asText();
                        words.add(new Word(text, category, difficulty));
                    }

                    wordsByDifficulty.put(difficulty, words);
                }

                wordsByCategory.put(category, wordsByDifficulty);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load words from JSON", e);
        }
    }

    /**
     * Gets a random word based on difficulty and category.
     * If category is "all", selects from a random category.
     *
     * @param difficulty the difficulty level (easy, medium, hard)
     * @param category the category name or "all" for random
     * @return a random Word matching the criteria
     * @throws IllegalArgumentException if difficulty or category is invalid
     */
    public Word getRandomWord(String difficulty, String category) {
        if ("all".equalsIgnoreCase(category)) {
            List<String> availableCategories = new ArrayList<>(wordsByCategory.keySet());
            if (availableCategories.isEmpty()) {
                throw new IllegalStateException("No categories available");
            }
            category = availableCategories.get(random.nextInt(availableCategories.size()));
        }

        Map<String, List<Word>> categoryWords = wordsByCategory.get(category.toLowerCase());
        if (categoryWords == null) {
            throw new IllegalArgumentException("Invalid category: " + category);
        }

        List<Word> words = categoryWords.get(difficulty.toLowerCase());
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }

        return words.get(random.nextInt(words.size()));
    }
}
