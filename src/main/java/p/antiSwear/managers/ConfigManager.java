package p.antiSwear.managers;

import org.bukkit.configuration.file.FileConfiguration;
import p.antiSwear.AntiSwear;
import p.antiSwear.processors.ConfigProcessor;
import p.antiSwear.annotations.ConfigValue;

public class ConfigManager {
    private final AntiSwear plugin;

    @ConfigValue(path = "censor-character", defaultValue = "*")
    private String censorCharacter;

    @ConfigValue(path = "enable-logging", defaultValue = "true")
    private boolean logEnabled;

    @ConfigValue(path = "enable-regex", defaultValue = "true")
    private boolean regexEnabled;

    @ConfigValue(path = "openai-api-key", defaultValue = "")
    private String openAiKey;

    public ConfigManager(AntiSwear plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        ConfigProcessor.processConfig(this, config);
    }

    // Getters
    public String getCensorCharacter() {
        return censorCharacter;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public boolean isRegexEnabled() {
        return regexEnabled;
    }

    public String getOpenAiKey() {return openAiKey;}
}
