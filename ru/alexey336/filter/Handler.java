package ru.alexey336.filter;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import java.util.List;

import static ru.alexey336.filter.Main.pl;

public class Handler implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e){
        String msg = e.getMessage();
        Player p = e.getPlayer();
        boolean bypass = pl.getConfig().getBoolean("admins-bypass");

        if (p.hasPermission("chatfilter.bypass") && bypass) return;

        List<String> blacklist = pl.getConfig().getStringList("commands.blacklist");

        for (String value : blacklist){
            if (msg.contains(value)){
                String error = pl.getConfig().getString("commands.message").replace("&", "§");
                p.sendMessage(error);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent e){
        String msg = e.getMessage();
        Player p = e.getPlayer();
        boolean bypass = pl.getConfig().getBoolean("admins-bypass");

        if (p.hasPermission("chatfilter.bypass") && bypass) return;

        List<String> blacklist = pl.getConfig().getStringList("chat.blacklist");

        for (String value : blacklist){
            if (msg.contains(value)){
                String error = pl.getConfig().getString("chat.message").replace("&", "§");
                p.sendMessage(error);
                e.setCancelled(true);
            }
        }
    }


}
