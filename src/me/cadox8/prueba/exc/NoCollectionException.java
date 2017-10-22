package me.cadox8.prueba.exc;

public class NoCollectionException extends Exception {

    public NoCollectionException(String name) {
        super("La colección no ha podido ser encontrada. Nombre: " + name);
    }
}
