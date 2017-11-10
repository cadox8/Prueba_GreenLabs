package me.cadox8.prueba.cmd;

import me.cadox8.prueba.gui.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HistoryCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String... args) {
        Player p;
        if (!(sender instanceof Player)) return false;
        p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("historial")) {
            if (!p.hasPermission("prueba.admin")) return false;

            p.closeInventory();
            GUI.playerPage.put(p, 1);
            new GUI().openGUI(p, GUI.playerPage.get(p));
        }

        return true;
    }
}
