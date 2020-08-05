package ru.alexey336.filter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin pl;

    public void onEnable(){
        pl = this;
        getLogger().info("§eЗаказать плагин:\n§avk.com/alexey336\n§at.me/alexey336");
        getServer().getPluginManager().registerEvents(new Handler(), this);
        saveDefaultConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (!sender.hasPermission("chatfilter.reload")){
            String noperm = getConfig().getString("noperm").replace("&", "§");
            sender.sendMessage(noperm);
            return true;
        }

        if (args.length < 1 || !args[0].contains("reload")){
            sender.sendMessage("\n§eИспользование: \n\n§a/cf reload - §fперезагрузить конфиг\n ");
            return true;
        }

        reloadConfig();
        sender.sendMessage("§aconfig.yml обновлен!");


        return true;
    }

}
