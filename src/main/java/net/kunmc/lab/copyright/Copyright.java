package net.kunmc.lab.copyright;

import net.kunmc.lab.copyright.commands.ControlExecutor;
import net.kunmc.lab.copyright.lib.LocaleManager;
import net.kunmc.lab.copyright.listeners.RecipeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Copyright extends JavaPlugin {

    public static CraftManager manager;
    public static LocaleManager localeManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        manager = new CraftManager();
        localeManager = new LocaleManager();
        this.getServer().getPluginManager().registerEvents(new RecipeListener(), this);
        this.getServer().getPluginCommand("control").setExecutor(new ControlExecutor());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
