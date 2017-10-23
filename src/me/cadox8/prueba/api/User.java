package me.cadox8.prueba.api;

import lombok.Getter;
import me.cadox8.prueba.Prueba;
import me.cadox8.prueba.utils.PunishLevel;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class User {

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
}
