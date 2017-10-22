package me.cadox8.prueba.exc;

public class NoDocumentException extends Exception {

    public NoDocumentException(String uuid) {
        super("El documento no ha podido ser encontrado. UUID: " + uuid);
    }
}
