package me.cadox8.prueba.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PunishLevel {

    ADVERTIDO(0),
    KICKEADO(1),
    BANEADO(2);

    @Getter private int level;
}
