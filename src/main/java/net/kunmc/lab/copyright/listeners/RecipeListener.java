package net.kunmc.lab.copyright.listeners;

import net.kunmc.lab.copyright.Copyright;
import net.kunmc.lab.copyright.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class RecipeListener implements Listener {

    @EventHandler
    public void on(org.bukkit.event.inventory.CraftItemEvent event) {
        if (!Copyright.manager.isRunning()) return;
        Player p = (Player) event.getWhoClicked();
        ItemStack is = event.getRecipe().getResult();

        if (Copyright.manager.getCrafter(is) != null) {
            UUID crafter = Copyright.manager.getCrafter(is);
            if(!p.getUniqueId().equals(crafter)) {
                event.setCancelled(true);
                p.sendMessage(is.getI18NDisplayName() + "は" + Bukkit.getOfflinePlayer(crafter).getName() + "しか作成することができません。");
            }
        } else {
            Copyright.manager.add(p.getUniqueId(), event.getRecipe().getResult());
            Utils.a(ChatColor.GOLD + p.getName() + "が" + ChatColor.YELLOW + is.getI18NDisplayName() + ChatColor.RESET + "を作りました。");
        }
    }

}
