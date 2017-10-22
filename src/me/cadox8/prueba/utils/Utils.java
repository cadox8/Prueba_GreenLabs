package me.cadox8.prueba.utils;

import me.cadox8.prueba.Prueba;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

public class Utils {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static ItemStack createSkull(String uuid, List<String> lore) {
        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) i;
        OfflinePlayer p = Prueba.getInstance().getServer().getOfflinePlayer(UUID.fromString(uuid));

        meta.setOwningPlayer(p);
        meta.setDisplayName(p.getName());
        meta.setLore(lore);
        i.setItemMeta(meta);

        return i;
    }
}
