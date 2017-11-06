package me.cadox8.prueba.api;

import lombok.Getter;

import java.util.Date;

public final class Sancion {

    @Getter private User user;
    @Getter private String op;
    @Getter private Date date;

    public Sancion(User user, String op) {
        this(user, op, new Date());
    }

    public Sancion(User user, String op, Date date) {
        this.user = user;
        this.op = op;
        this.date = date;
    }
}
