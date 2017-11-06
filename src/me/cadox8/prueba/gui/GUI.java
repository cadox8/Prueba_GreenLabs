package me.cadox8.prueba.gui;

import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.api.User;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class GUI {

    private final Prueba plugin = Prueba.getInstance();

    public static HashMap<Player, Integer> playerPage = new HashMap<>();
    public static HashMap<Player, User> playerData = new HashMap<>();

    public void openGUI(Player p, int page){
        Inventory inv = plugin.getServer().createInventory(null, 54, "Sanciones");
        int tot = Prueba.getUsers().size();
        List<ItemStack> items = new MenuItems().getItemsPerPage(page);

        if(items.isEmpty() || items == null){
            p.sendMessage(Prueba.getPrefix() + ChatColor.RED + "No hay sanciones");
            return;
        }

        for(ItemStack i : items) inv.addItem(i);

        if(page == 1 && Math.round(tot / 45) >= 1) inv.setItem(50, getNextItem());

        if(page >= 2){
            if(Math.round(tot / 45) >= page) inv.setItem(50, getNextItem());
            inv.setItem(47, getPrevItem());
        }
        p.closeInventory();
        p.openInventory(inv);
    }

    private ItemStack getNextItem(){
        ItemStack i = new ItemStack(Material.ARROW);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Siguiente");
        i.setItemMeta(im);

        return i;
    }

    private ItemStack getPrevItem(){
        ItemStack i = new ItemStack(Material.ARROW);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.AQUA + "Anterior");
        i.setItemMeta(im);

        return i;
    }
}
