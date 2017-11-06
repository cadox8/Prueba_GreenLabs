package me.cadox8.prueba.gui;

import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.api.User;
import me.cadox8.prueba.exc.NoCollectionException;
import me.cadox8.prueba.utils.ItemMaker;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UserGUI {

    private final Prueba plugin = Prueba.getInstance();

    private final User user;
    private final Player p;

    private int slot = 9;

    public UserGUI(User user, Player p) {
        this.user = user;
        this.p = p;
    }

    public void open() {
        Inventory inv = plugin.getServer().createInventory(null, 45, "Sancionar");

        inv.setItem(0, new ItemMaker(Material.ARROW).setName("Atras").build());
        inv.setItem(3, new ItemMaker(Material.SKULL_ITEM, 3).setName(user.getName()).build());
        inv.setItem(5, new ItemMaker(Material.DIAMOND).setName("Subir aviso").build());
        inv.setItem(8, new ItemMaker(Material.BARRIER).setName("&cCerrar").build());

        try {
            user.getHistory("punish").forEach(s -> {
                inv.setItem(slot, new ItemMaker(Material.PAPER).setName("Sancion").setLore("Por: " + s.getOp(), "Día: " + s.getDate()).build());
                slot++;
            });
        } catch (NoCollectionException e) {
            System.out.println(e.getMessage());
        }

        p.openInventory(inv);
    }
}
