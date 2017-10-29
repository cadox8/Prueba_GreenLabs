package me.cadox8.prueba.api;

import com.mongodb.BasicDBObject;
import lombok.Getter;
import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.exc.NoCollectionException;
import me.cadox8.prueba.utils.Mongo;
import me.cadox8.prueba.utils.PunishLevel;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

     private final Mongo mongo = Prueba.getInstance().getMongo();

    @Getter private UUID uuid;
    private PunishLevel pl;

    public User(String uuid, PunishLevel pl) {
        this(UUID.fromString(uuid), pl);
    }

    public User(UUID uuid, PunishLevel pl) {
        this.uuid = uuid;
        this.pl = pl;
    }

    public OfflinePlayer getPlayer() {
        return Prueba.getInstance().getServer().getOfflinePlayer(uuid);
    }
    public int getPunishLevel() {
        return pl.getLevel();
    }
    public String getName() {
        return getPlayer().getName();
    }

    public boolean updateDocument(String collection , PunishLevel pl, Player p) throws NoCollectionException {
        if (mongo.getCollection(collection) == null) throw new NoCollectionException(collection);
        int level = pl.getLevel() > PunishLevel.BANEADO.getLevel() ? PunishLevel.BANEADO.getLevel() : pl.getLevel();
        BasicDBObject query = new BasicDBObject().append("UUID", uuid);
        BasicDBObject newLevel = new BasicDBObject();
        newLevel.append("$set", new BasicDBObject().append("level", level));

        Sancion s = new Sancion(this, p);

        mongo.getCollection(collection).update(query, newLevel);
        return true;
    }

    public List<Sancion> getHistory(String collection) throws NoCollectionException {
        if (mongo.getCollection(collection) == null) throw new NoCollectionException(collection);
        List<Sancion> sanciones = new ArrayList<>();



        return sanciones;
    }
}
