package me.cadox8.prueba.events;

import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.api.User;
import me.cadox8.prueba.exc.NoCollectionException;
import me.cadox8.prueba.gui.GUI;
import me.cadox8.prueba.utils.PunishLevel;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.SkullMeta;

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
                        if (e.getInventory().getItem(e.getSlot()) != null) GUI.playerPage.put(p, GUI.playerPage.get(p) + 1);
                        break;
                    case 47:
                        if (e.getInventory().getItem(e.getSlot()) != null && GUI.playerPage.get(p) - 1 <= 0) GUI.playerPage.put(p, GUI.playerPage.get(p) - 1);
                        break;

                    default:
                        SkullMeta sm = (SkullMeta) e.getInventory().getItem(e.getSlot()).getItemMeta();
                        User user = null;

                        for (User u : Prueba.getUsers()) if (u.getUuid().equals(sm.getOwningPlayer().getUniqueId())) user = u;

                        p.closeInventory();
                        if (user != null) {
                            if (GUI.playerData.containsKey(p)) GUI.playerData.remove(p);
                            GUI.playerData.put(p, user);
                        }
                        break;
                }
                break;
            case "Sancionar":
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.BLOCK_REDSTONE_TORCH_BURNOUT, 1, 1);
                switch (e.getSlot()) {
                    case 0:
                        p.closeInventory();
                        new GUI().openGUI(p, GUI.playerPage.get(p));
                        if (GUI.playerData.containsKey(p)) GUI.playerData.remove(p);
                        break;
                    case 8:
                        p.closeInventory();
                        if (GUI.playerData.containsKey(p)) GUI.playerData.remove(p);
                        break;
                    case 5:
                        User u = GUI.playerData.get(p);
                        try {
                            u.updateDocument("punish", PunishLevel.next(u.getPl()), p);
                        } catch (NoCollectionException ex) {
                            System.out.println(ex.getMessage());
                        }
                        break;

                    default:
                        break;
                }

            default:
                break;
        }
    }
}
