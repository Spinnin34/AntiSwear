package p.antiSwear;

import org.bukkit.plugin.java.JavaPlugin;
import p.antiSwear.commands.CommandManager;
import p.antiSwear.listeners.ChatListener;
import p.antiSwear.managers.ConfigManager;
import p.antiSwear.managers.WordListManager;
import p.antiSwear.services.SwearFilter;

public final class AntiSwear extends JavaPlugin {
    private ConfigManager configManager;
    private WordListManager wordListManager;
    private SwearFilter swearFilter;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.wordListManager = new WordListManager(this);
        this.swearFilter = new SwearFilter(this);

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);

        CommandManager commandManager = new CommandManager(this);
        getCommand("antiswear").setExecutor(commandManager);
        getCommand("antiswear").setTabCompleter(commandManager);

        getLogger().info("AntiSwear plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AntiSwear plugin has been disabled!");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public SwearFilter getSwearFilter() {
        return swearFilter;
    }

    public WordListManager getWordListManager() {
        return wordListManager;
    }

}