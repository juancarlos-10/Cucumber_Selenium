package db;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import utils.LogHelper;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MongoDBHelper {
    private static final Logger LOGGER = LogHelper.getLogger(MongoDBHelper.class);
    private static MongoClient mongoClient;

    public static void connectToServer() {
        LOGGER.log(Level.INFO, "Conectandose al servidor...");
        String user = "myUserAdmin";
        String database = "admin";
        char[] password = {'a', 'd', 'm', 'i', 'n'};
        MongoCredential credential = MongoCredential.createCredential(user, database, password);
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Collections.singletonList(credential));
        try {
            mongoClient.getAddress();
            LOGGER.log(Level.INFO, "Conexion exitosa");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "No se pudo conectar correctamente al servidor");
            LOGGER.log(Level.SEVERE, e.getMessage());
            mongoClient.close();
        }
    }

    //Conectarse a la BD//
    public static MongoDatabase getDataBase(String dbName) {
        return mongoClient.getDatabase(dbName);
    }

    //Obtener una coleccion de la bd//
    public static MongoCollection<Document> getCollectionFromDb(MongoDatabase db, String collection){
        return db.getCollection(collection);
    }

    //Obteenr un documento por clave/valor//
    public static Document getDocumentBykeyValue(MongoCollection<Document> collection, String key, String value){
        return collection.find(Filters.eq(key,value)).first();
    }

    //obtener un objeto de un documento
    public static Object getElementByKeyFromDocument(Document document, String key){
        return document.get(key);
    }

}