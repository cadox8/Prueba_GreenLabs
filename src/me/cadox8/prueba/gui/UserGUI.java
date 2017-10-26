package me.cadox8.prueba.gui;

import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.api.User;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UserGUI {

    private final Prueba plugin = Prueba.getInstance();

    private final User user;
    private final Player p;

    public UserGUI(User user, Player p) {
        this.user = user;
        this.p = p;
    }

    public void open() {
        Inventory inv = plugin.getServer().createInventory(null, 45, "Sancionar");


        p.openInventory(inv);
    }
}
