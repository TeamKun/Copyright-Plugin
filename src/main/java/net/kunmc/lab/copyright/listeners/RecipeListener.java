package net.kunmc.lab.copyright.listeners;

import net.kunmc.lab.copyright.Copyright;
import net.kunmc.lab.copyright.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class RecipeListener implements Listener {

    // item craft event
    @EventHandler
    public void on(org.bukkit.event.inventory.CraftItemEvent event) {
        Player p = (Player) event.getWhoClicked();
        ItemStack is = event.getRecipe().getResult();
        if (!Copyright.manager.canCraft(p.getUniqueId(), event.getRecipe().getResult())) {
            event.setCancelled(true);
            p.sendMessage(is.getI18NDisplayName() + "は" + p.getName() + "しか作成することができません。");
        } else {
            Copyright.manager.add(p.getUniqueId(), event.getRecipe().getResult());
            Utils.a(p.getName() + "が" + is.getI18NDisplayName() + "を作りました。");
        }
    }

}
