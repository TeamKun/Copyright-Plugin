package net.kunmc.lab.copyright.commands;

import net.kunmc.lab.copyright.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ControlExecutor implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.isOp()){
            sender.sendMessage(ChatColor.RED + "権限がありません。");
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = null;
        if(args.length < 1) {
            list = Utils.cl(Arrays.asList("reload", "enable", "disable", "help"), args[0]);
            return list;
        } else {
            return Utils.nl;
        }
    }
}
