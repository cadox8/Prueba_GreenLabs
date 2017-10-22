package me.cadox8.prueba;

import com.mongodb.DB;
import lombok.Getter;
import me.cadox8.prueba.utils.Mongo;
import me.cadox8.prueba.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public class Prueba extends JavaPlugin {

    @Getter private static Prueba instance;
    @Getter private static String prefix = Utils.colorize(" &7|| &cPrueba &7|| ");

    @Getter private Mongo mongo;
    @Getter private DB database; //Dejemos esto aquí por si acaso

    public void onEnable() {
        instance = this;

        try {
            mongo = new Mongo("localhost", 27017, "root", "password", "punish");
            database = mongo.connect();
        } catch (Exception exc) {
            System.out.println("El plugin necesita conexión con la base de datos");
            getServer().getPluginManager().disablePlugin(this);
        }
    }
}
