package net.kunmc.lab.copyright;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class CraftManager {

    Map<Material, UUID> crafters;
    boolean running;

    public CraftManager(){
        this.crafters = new java.util.HashMap<>();
        running = false;
    }

    public Map<Material, UUID> getCrafters() {
        return crafters;
    }

    public void add(UUID uniqueId, ItemStack result) {
        crafters.put(result.getType(), uniqueId);
    }

    public UUID getCrafter(ItemStack is) {
        if(is == null) return null;
        if(is.getType() == Material.AIR) return null;
        if(!crafters.containsKey(is.getType())) return null;
        return crafters.get(is.getType());
    }

    public void reset() {
        this.crafters = new java.util.HashMap<>();
    }

    public void enable() {
        this.running = true;
    }

    public void disable() {
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
