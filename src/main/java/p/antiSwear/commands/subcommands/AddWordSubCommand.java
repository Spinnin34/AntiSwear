package p.antiSwear.commands.subcommands;

import org.bukkit.command.CommandSender;
import p.antiSwear.AntiSwear;
import p.antiSwear.commands.SubCommand;
import p.antiSwear.utils.MessageUtils;

import java.util.ArrayList;
import java.util.List;

public class AddWordSubCommand implements SubCommand {
    private final AntiSwear plugin;

    public AddWordSubCommand(AntiSwear plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            MessageUtils.sendMessage(sender,"Usage: /antiswear add <word>");
            return true;
        }

        String word = args[0].toLowerCase();
        plugin.getWordListManager().addBannedWord(word);
        MessageUtils.sendMessage(sender,"Added '" + word + "' to the banned word list.");
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return "antiswear.add";
    }
}
