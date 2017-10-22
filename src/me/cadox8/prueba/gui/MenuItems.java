package me.cadox8.prueba.gui;

import me.cadox8.prueba.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MenuItems {

    public List<ItemStack> getItemsPerPage(Player p, int page, int tot) {
        List<ItemStack> items = new ArrayList<>();
        List<String> lore = new ArrayList<>();
        int min = 46;

        lore.clear();
        items.clear();

        lore.add(ChatColor.GOLD + "Click izquierdo para ver");

        if (page == 1) {
            for (int x = 1; x < min; x++) {
                if (x > tot) break;

                lore.add(x + "");
                items.add(Utils.createSkull(p.getUniqueId().toString(), lore));
                lore.remove(2);
            }
        }

        if (page >= 2) {
            for (int x = (min * page) - min; x < min * page; x++) {
                if (x > tot) break;

                lore.add(x + "");
                items.add(Utils.createSkull(p.getUniqueId().toString(), lore));
                lore.remove(2);
            }
        }

        return items;
    }
}
