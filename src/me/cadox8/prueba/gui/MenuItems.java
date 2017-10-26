package me.cadox8.prueba.gui;

import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MenuItems {

    public List<ItemStack> getItemsPerPage(int page) {
        List<ItemStack> items = new ArrayList<>();
        List<String> lore = new ArrayList<>();
        int min = 46;
        int tot = Prueba.getUsers().size();

        lore.clear();
        items.clear();

        lore.add(ChatColor.GOLD + "Click izquierdo para ver");

        // Nunca pasará, pero no está de más
        if (page == 0) return null;

        if (page == 1) {
            for (int x = 1; x < min; x++) {
                if (x > tot) break;
                items.add(Utils.createSkull(Prueba.getUsers().get(x).getUuid().toString(), lore));
            }
        }

        if (page >= 2) {
            for (int x = (min * page) - min; x < min * page; x++) {
                if (x > tot) break;
                items.add(Utils.createSkull(Prueba.getUsers().get(x).getUuid().toString(), lore));
            }
        }
        return items;
    }
}
