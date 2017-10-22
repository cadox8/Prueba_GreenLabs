package me.cadox8.prueba.utils;

import com.mongodb.*;
import lombok.Getter;
import me.cadox8.prueba.exc.NoCollectionException;
import me.cadox8.prueba.exc.NoDocumentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mongo {

    @Getter private DB database;

    @Getter private String host;
    @Getter private int port;
    @Getter private String user;
    @Getter private char[] pass;
    @Getter private String DB_Name;

    private boolean auth;

    public Mongo(String host, int port, String DB_Name) {
        this(host, port, null, null, DB_Name);
        auth = false;
    }

    public Mongo(String host, int port, String user, String pass, String DB_Name) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass.toCharArray();
        this.DB_Name = DB_Name;
    }

    public DB connect() {
        if (database != null) return database;
        MongoClient mongo;

        if (auth) {
            MongoCredential credential = MongoCredential.createCredential(user, DB_Name, pass);
            mongo = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
        } else {
            mongo = new MongoClient(host, port);
        }
        database =  mongo.getDB(getDB_Name());
        return database;
    }

    private DBCollection getCollection(String collection) {
        return database.getCollection(collection);
    }


    // Metodos
    // Usando UUID

    public List<DBCursor> getAllDocuments(String collection) throws NoCollectionException {
        if (getCollection(collection) == null) throw new NoCollectionException(collection);
        List<DBCursor> documents = new ArrayList<>();

        DBCursor cursor = getCollection(collection).find();
        try {
            while(cursor.hasNext()) documents.add(cursor);
        } finally {
            cursor.close();
        }
        return documents;
    }

    public DBCursor getDocument(String collection, String uuid) throws NoDocumentException, NoCollectionException {
        if (getCollection(collection) == null) throw new NoCollectionException(collection);

        BasicDBObject query = new BasicDBObject("UUID", uuid);
        DBCursor cursor = getCollection(collection).find(query);

        try {
            while(cursor.hasNext()) return cursor;
        } finally {
            cursor.close();
        }
        throw new NoDocumentException(uuid);
    }

    public boolean updateDocument(String uuid, String collection , PunishLevel pl) throws NoCollectionException {
        if (getCollection(collection) == null) throw new NoCollectionException(collection);

        int level = pl.getLevel() > PunishLevel.BANEADO.getLevel() ? PunishLevel.BANEADO.getLevel() : pl.getLevel();
        BasicDBObject query = new BasicDBObject().append("UUID", uuid);
        BasicDBObject newLevel = new BasicDBObject();
        newLevel.append("$set", new BasicDBObject().append("level", level));

        getCollection(collection).update(query, newLevel);
        return true;
    }
}
