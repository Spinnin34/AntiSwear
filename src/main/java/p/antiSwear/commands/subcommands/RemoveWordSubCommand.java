package p.antiSwear.commands.subcommands;

import org.bukkit.command.CommandSender;
import p.antiSwear.AntiSwear;
import p.antiSwear.commands.SubCommand;
import p.antiSwear.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class RemoveWordSubCommand implements SubCommand {
    private final AntiSwear plugin;

    public RemoveWordSubCommand(AntiSwear plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            MessageUtils.sendMessage(sender,"Usage: /antiswear remove <word>");
            return true;
        }

        String word = args[0].toLowerCase();
        if (plugin.getWordListManager().removeBannedWord(word)) {
            MessageUtils.sendMessage(sender,"Removed '" + word + "' from the banned word list.");
        } else {
            MessageUtils.sendMessage(sender,"'" + word + "' was not found in the banned word list.");
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(plugin.getWordListManager().getBannedWords());
        }
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return "antiswear.remove";
    }
}
