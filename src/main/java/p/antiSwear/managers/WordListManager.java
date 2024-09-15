package p.antiSwear.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import p.antiSwear.AntiSwear;

import java.io.*;
import java.util.List;

public class WordListManager {
    private final AntiSwear plugin;
    private final File wordFile;
    private final File csvFile;
    private YamlConfiguration wordConfig;

    public WordListManager(AntiSwear plugin) {
        this.plugin = plugin;
        this.wordFile = new File(plugin.getDataFolder(), "words.yml");
        this.csvFile = new File(plugin.getDataFolder(), "banned_words.csv");
        loadWordList();
    }

    public void loadWordList() {
        if (!wordFile.exists()) {
            plugin.saveResource("words.yml", false);
        }
        wordConfig = YamlConfiguration.loadConfiguration(wordFile);

        // Load words from CSV if it exists
        if (csvFile.exists()) {
            loadWordsFromCSV();
        }
    }

    private void loadWordsFromCSV() {
        List<String> bannedWords = getBannedWords();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(",");
                for (String word : words) {
                    String trimmedWord = word.trim();
                    if (!trimmedWord.isEmpty() && !bannedWords.contains(trimmedWord)) {
                        bannedWords.add(trimmedWord);
                    }
                }
            }
        } catch (IOException e) {
            plugin.getLogger().warning("Error reading CSV file: " + e.getMessage());
        }
        wordConfig.set("banned-words", bannedWords);
        saveWordList();
    }

    public List<String> getBannedWords() {
        return wordConfig.getStringList("banned-words");
    }

    public List<String> getRegexPatterns() {
        return wordConfig.getStringList("regex-patterns");
    }

    public void addBannedWord(String word) {
        List<String> words = getBannedWords();
        if (!words.contains(word)) {
            words.add(word);
            wordConfig.set("banned-words", words);
            saveWordList();
        }
    }

    public boolean removeBannedWord(String word) {
        List<String> words = getBannedWords();
        boolean removed = words.remove(word);
        if (removed) {
            wordConfig.set("banned-words", words);
            saveWordList();
        }
        return removed;
    }

    private void saveWordList() {
        try {
            wordConfig.save(wordFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save words.yml!");
            e.printStackTrace();
        }
    }
}