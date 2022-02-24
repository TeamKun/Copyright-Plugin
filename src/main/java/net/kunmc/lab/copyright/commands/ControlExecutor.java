package net.kunmc.lab.copyright.commands;

import net.kunmc.lab.copyright.Copyright;
import net.kunmc.lab.copyright.CraftManager;
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
        if(!sender.hasPermission("copyright.control")){
            sender.sendMessage(ChatColor.RED + "権限がありません。");
            return true;
        }

        if(args.length < 1 || args[0].equalsIgnoreCase("help")){
            s(sender);
            return true;
        }

        if(args[0].equalsIgnoreCase("reset")){
            Copyright.manager.reset();
            sender.sendMessage(ChatColor.AQUA + "クラフトデータを初期化しました。");
            Utils.a("クラフトデータが" + sender.getName() + "によって" + ChatColor.AQUA + "初期化" + ChatColor.RESET + "されました。");
            return true;
        }

        if(args[0].equalsIgnoreCase("enable")){
            Copyright.manager.enable();
            String status = Copyright.manager.isRunning() ? ChatColor.GREEN + "有効" : ChatColor.RED + "無効";
            sender.sendMessage(ChatColor.GREEN + "プラグインの機能を有効にしました。");
            Utils.a("プラグインの機能が" + sender.getName() + "によって" + status + ChatColor.RESET + "になりました。");
            return true;
        }

        if(args[0].equalsIgnoreCase("disable")){
            Copyright.manager.disable();
            String status = Copyright.manager.isRunning() ? ChatColor.GREEN + "有効" : ChatColor.RED + "無効";
            sender.sendMessage(ChatColor.RED + "プラグインの機能を無効にしました。\n" +
                    ChatColor.YELLOW + "⚠クラフトデータは初期化されません。");
            Utils.a("プラグインの機能が" + sender.getName() + "によって" + status + ChatColor.RESET + "になりました。");
            return true;
        }

        if(args[0].equalsIgnoreCase("status")){
            String status = Copyright.manager.isRunning() ? ChatColor.GREEN + "有効" : ChatColor.RED + "無効";
            sender.sendMessage(ChatColor.GREEN + "プラグインは" + status + ChatColor.GREEN + "です。");
            return true;
        }

        s(sender);
        return true;
    }

    void s(CommandSender p){
        p.sendMessage("/control help     - このページを表示します。\n" +
                "/control reset   - クラフトデータを初期化します。\n" +
                "/control enable  - プラグインの機能を有効にします。\n" +
                "/control disable - プラグインの機能を無効にします。\n" +
                "/control status  - プラグインの状態(有効/無効)を表示します。");
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = null;
        if(!sender.hasPermission("copyright.control")) return Utils.nl;
        if(args.length < 2) {
            list = Utils.cl(Arrays.asList("reset", "enable", "disable", "help", "status"), args[0]);
            return list;
        } else {
            return Utils.nl;
        }
    }
}
