package me.taako.doubleshulkershells;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public final class DoubleShulkerShells extends JavaPlugin implements Listener {

    private final Logger logger = Bukkit.getLogger();
    private int shulkerShellDropAmount;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        shulkerShellDropAmount = getConfig().getInt("shulker_shell_drop_amount");

        logger.info("Enabled Plugin!");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType entityType = event.getEntityType();
        if (entityType != EntityType.SHULKER) return;

        List<ItemStack> drops = event.getDrops();
        drops.removeIf(item -> item.getType() == Material.SHULKER_SHELL);

        drops.add(new ItemStack(Material.SHULKER_SHELL, shulkerShellDropAmount));
    }

    @Override
    public void onDisable() {
        logger.info("Disabled Plugin!");
    }
}
