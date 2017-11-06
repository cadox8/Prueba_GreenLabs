package me.cadox8.prueba.api;

import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import lombok.Getter;
import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.exc.NoCollectionException;
import me.cadox8.prueba.utils.Mongo;
import me.cadox8.prueba.utils.PunishLevel;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class User {

     private final Mongo mongo = Prueba.getInstance().getMongo();

    @Getter private UUID uuid;
    @Getter private PunishLevel pl;
    private List<Sancion> sancion;

    public User(String uuid, PunishLevel pl, Sancion... sancion) {
        this(UUID.fromString(uuid), pl, sancion);
    }

    public User(UUID uuid, PunishLevel pl, Sancion... sancion) {
        this.uuid = uuid;
        this.pl = pl;
        this.sancion = Arrays.asList(sancion);
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
    public Sancion getSancion(int id) {
        if (id > sancion.size()) return null;
        return sancion.get(id);
    }

    public boolean updateDocument(String collection , PunishLevel pl, Player p) throws NoCollectionException {
        if (mongo.getCollection(collection) == null) throw new NoCollectionException(collection);
        int level = pl.getLevel() > PunishLevel.BANEADO.getLevel() ? PunishLevel.BANEADO.getLevel() : pl.getLevel();
        BasicDBObject query = new BasicDBObject().append("UUID", uuid);
        BasicDBObject newLevel = new BasicDBObject();
        newLevel.append("$set", new BasicDBObject().append("level", level));

        BasicDBObject newSan = new BasicDBObject();
        newSan.append(pl.getLevel() + "", new GsonBuilder().create().toJson(new Sancion(this, p.getName())));

        mongo.getCollection(collection).update(query, newLevel);
        return true;
    }

    public List<Sancion> getHistory(String collection) throws NoCollectionException {
        if (mongo.getCollection(collection) == null) throw new NoCollectionException(collection);
        List<Sancion> sanciones = new ArrayList<>();



        return sanciones;
    }
}
