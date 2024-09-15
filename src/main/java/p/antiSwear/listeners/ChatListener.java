package p.antiSwear.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import p.antiSwear.AntiSwear;
import p.antiSwear.models.FilterResult;
import p.antiSwear.services.SwearFilter;
import p.antiSwear.utils.PermissionUtils;


public class ChatListener implements Listener {
    private final AntiSwear plugin;

    public ChatListener(AntiSwear plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (PermissionUtils.hasPermission(player, "antiswear.bypass")) {
            return;
        }

        SwearFilter swearFilter = plugin.getSwearFilter();
        FilterResult result = swearFilter.filterMessage(event.getMessage());

        if (result.containsProfanity()) {
            event.setMessage(result.getFilteredMessage());

            if (plugin.getConfigManager().isLogEnabled()) {
                plugin.getLogger().info(player.getName() + " tried to use profanity: " + result.getOriginalMessage());
            }
        }
    }
}
