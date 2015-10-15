package de.et.mongo.anwender.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

/**
 * Ein Anwender.
 *
 * Created by Erik on 15.10.2015.
 */
@Entity
public class Anwender {

    @Id
    private ObjectId id;
    private String name;
    private int age;
    private String city;

    public Anwender() {
    }

    public Anwender(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Anwender: " + name + ", " + age + ", " + city;
    }
}
