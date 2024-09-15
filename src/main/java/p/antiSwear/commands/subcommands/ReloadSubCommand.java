package p.antiSwear.commands.subcommands;

import org.bukkit.command.CommandSender;
import p.antiSwear.AntiSwear;
import p.antiSwear.commands.SubCommand;
import p.antiSwear.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class ReloadSubCommand implements SubCommand {
    private final AntiSwear plugin;

    public ReloadSubCommand(AntiSwear plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        plugin.getConfigManager().loadConfig();
        plugin.getWordListManager().loadWordList();
        MessageUtils.sendMessage(sender,"AntiSwear configuration reloaded.");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return "antiswear.reload";
    }
}
