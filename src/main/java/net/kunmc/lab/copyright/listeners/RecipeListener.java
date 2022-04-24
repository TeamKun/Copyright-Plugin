package net.kunmc.lab.copyright.listeners;

import net.kunmc.lab.copyright.Copyright;
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
                Copyright.localeManager.sendMessage(p, ChatColor.YELLOW + "<item>" + ChatColor.RESET + "は" + ChatColor.GOLD + Bukkit.getOfflinePlayer(crafter).getName() + ChatColor.RESET + "しか作成することができません。", is.getType(), is.getDurability(), is.getEnchantments(), is.getItemMeta());
            }
        } else {
            Copyright.manager.add(p.getUniqueId(), event.getRecipe().getResult());
            for (Player p2 : Bukkit.getOnlinePlayers()) {
                Copyright.localeManager.sendMessage(p2, ChatColor.GOLD + p.getName() + ChatColor.RESET + "が" + ChatColor.YELLOW + "<item>" + ChatColor.RESET + "を作成しました。", is.getType(), is.getDurability(), is.getEnchantments(), is.getItemMeta());
            }
        }
    }

}
