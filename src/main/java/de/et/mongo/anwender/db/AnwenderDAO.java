package de.et.mongo.anwender.db;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import de.et.mongo.anwender.model.Anwender;
import org.bson.Document;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;

/**
 * Anwender DAO
 *
 * More Information:
 * https://github.com/mongodb/morphia/wiki/QuickStart
 * http://mongodb.github.io/morphia/1.0/getting-started/quick-tour/
 *
 * Created by Erik on 15.10.2015.
 */
public class AnwenderDAO {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        Morphia morphia = new Morphia();
        morphia.map(Anwender.class);
        Datastore ds = morphia.createDatastore(client, "test");

        Anwender a = new Anwender("Hans", 32, "Linz");
        ds.save(a);

        List<Anwender> anwenders = ds.find(Anwender.class).asList();
        for (Anwender anwender : anwenders) {
            System.out.println(anwender);
        }

        ds.delete(a);
    }

    public void write() {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("test");
        db.getCollection("anwender").insertOne(new Document("name","Lea")
                .append("age", 25).append("stadt", "Stein"));
    }

    public void read() {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("test");
        FindIterable<Document> anwender = db.getCollection("anwender").find();
        anwender.forEach(new Block<Document>() {

            @Override
            public void apply(Document document) {
                System.out.println(document);
            }
        });
    }
}
