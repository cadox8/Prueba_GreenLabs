package me.cadox8.prueba.events;

import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.api.User;
import me.cadox8.prueba.gui.GUI;
import me.cadox8.prueba.gui.UserGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvEvent implements Listener {

    private final Prueba plugin;

    public InvEvent(Prueba instance) {
        plugin = instance;
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (e.getClickedInventory().getTitle() == null) return;
        Player p = (Player) e.getWhoClicked();

        if (!p.hasPermission("prueba.admin")) {
            e.setCancelled(true);
            p.closeInventory();
        }

        switch (e.getClickedInventory().getTitle()) {
            case "Sanciones":
                e.setCancelled(true);

                switch (e.getSlot()) {
                    case 50:

                        break;
                    case 47:

                        break;

                    default:
                        User user = Prueba.getUsers().get(GUI.playerPage.get(p) * e.getSlot());
                        p.closeInventory();
                        new UserGUI(user, p).open();
                        break;
                }
                break;
            default:
                break;
        }
    }
}
