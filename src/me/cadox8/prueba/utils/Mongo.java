package me.cadox8.prueba.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.mongodb.util.JSON;
import lombok.Getter;
import me.cadox8.prueba.api.User;
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

    private boolean auth = true;

    private Gson gson = new GsonBuilder().create();

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

    public DBCollection getCollection(String collection) {
        return database.getCollection(collection);
    }


    // Metodos
    // Usando UUID

    public List<User> getAllDocuments(String collection) throws NoCollectionException {
        if (getCollection(collection) == null) throw new NoCollectionException(collection);
        List<User> users = new ArrayList<>();
        DBCursor cursor = getCollection(collection).find();

        try {
            while(cursor.hasNext()) users.add(gson.fromJson(JSON.serialize(cursor), User.class));
        } finally {
            cursor.close();
        }
        return users;
    }

    public User getDocument(String collection, String uuid) throws NoDocumentException, NoCollectionException {
        if (getCollection(collection) == null) throw new NoCollectionException(collection);
        BasicDBObject query = new BasicDBObject("UUID", uuid);
        DBCursor cursor = getCollection(collection).find(query);

        try {
            while(cursor.hasNext()) return gson.fromJson(JSON.serialize(cursor), User.class);
        } finally {
            cursor.close();
        }
        throw new NoDocumentException(uuid);
    }
}
