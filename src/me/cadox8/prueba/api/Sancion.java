package me.cadox8.prueba.api;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Date;

public class Sancion {

    @Getter private User user;
    @Getter private Player op;
    @Getter private Date date;

    public Sancion(User user, Player op) {
        this(user, op, new Date());
    }

    public Sancion(User user, Player op, Date date) {
        this.user = user;
        this.op = op;
        this.date = date;
    }
}
