package me.cadox8.prueba.gui;

import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.exc.NoCollectionException;
import me.cadox8.prueba.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class GUI {

    private Prueba plugin = Prueba.getInstance();

    public static HashMap<Player, Integer> playerPage = new HashMap<>();

    private MenuItems mi = new MenuItems();

    public void openGUI(Player p, int page){
        Inventory inv = plugin.getServer().createInventory(null, 54, Utils.colorize("&cSanciones"));
        int tot = 0;

        try {
            tot = plugin.getMongo().getAllDocuments("punish").size();
        } catch (NoCollectionException e) {
            e.printStackTrace();
        }
        List<ItemStack> items = mi.getItemsPerPage(p, page, tot);

        if(items.isEmpty()){
            p.sendMessage(Prueba.getPrefix() + ChatColor.RED + "No hay sanciones");
            return;
        }

        for(ItemStack i : items) inv.addItem(i);

        if(page == 1 && Math.round(tot / 45) >= 1) inv.setItem(50, getNextItem());

        if(page >= 2){
            if(Math.round(tot / 45) >= page) inv.setItem(50, getNextItem());
            inv.setItem(47, getPrevItem());
        }

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
