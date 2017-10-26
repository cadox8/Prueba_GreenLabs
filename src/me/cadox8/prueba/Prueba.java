package me.cadox8.prueba;

import com.mongodb.DB;
import lombok.Getter;
import me.cadox8.prueba.api.User;
import me.cadox8.prueba.events.InvEvent;
import me.cadox8.prueba.exc.NoCollectionException;
import me.cadox8.prueba.utils.Mongo;
import me.cadox8.prueba.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Prueba extends JavaPlugin {

    @Getter private static Prueba instance;
    @Getter private static String prefix = Utils.colorize(" &7|| &cPrueba &7|| ");

    @Getter private Mongo mongo;
    @Getter private DB database; //Dejemos esto aquí por si acaso

    @Getter private static List<User> users;

    public void onEnable() {
        instance = this;

        try {
            mongo = new Mongo("localhost", 27017, "root", "password", "punish");
            database = mongo.connect();
        } catch (Exception exc) {
            System.out.println("El plugin necesita conexión con la base de datos");
            getServer().getPluginManager().disablePlugin(this);
        }

        // 40 ticks delay
        getServer().getScheduler().runTaskTimer(this, ()-> updateUsers(), 0, 40);

        getServer().getPluginManager().registerEvents(new InvEvent(this), this);
    }

    public void updateUsers() {
        try {
            users = getMongo().getAllDocuments("punish");
        } catch (NoCollectionException e) {
            System.out.println("Error: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
