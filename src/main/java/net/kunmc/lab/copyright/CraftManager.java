package net.kunmc.lab.copyright;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;

public class CraftManager {

    Map<UUID, Material> crafters;

    public CraftManager(){
        this.crafters = new java.util.HashMap<>();
    }

    public Map<UUID, Material> getCrafters() {
        return crafters;
    }

    public boolean canCraft(UUID uuid, ItemStack is){
        return canCraft(uuid, is.getType());
    }

    public boolean canCraft(UUID uuid, Material m){
        return crafters.containsKey(uuid) && crafters.get(uuid) == m;
    }

    public void add(UUID uuid, Material m){
        crafters.put(uuid, m);
    }

    public void add(UUID uuid, ItemStack m){
        crafters.put(uuid, m.getType());
    }

    public void revoke(UUID uuid){
        crafters.remove(uuid);
    }
}
