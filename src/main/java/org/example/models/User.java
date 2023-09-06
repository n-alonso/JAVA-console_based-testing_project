package org.example.models;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static final AtomicInteger id_counter = new AtomicInteger();

    private final int id;
    private String name;
    private String surname;
    private final String email;
    private final Map<String, Integer> scores;

    public User(String name, String surname, String email) {
        this.id = id_counter.getAndIncrement();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.scores = new HashMap<>();
        this.scores.put("Java", 0);
        this.scores.put("DSA", 0);
        this.scores.put("Databases", 0);
        this.scores.put("Spring", 0);
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Integer> getScores() { return this.scores; }

    public int updateJava(int score) {
        int java = this.scores.get("Java");
        java += score;
        if (java < 0) java = 0;

        this.scores.put("Java", java);

        return java;
    }

    public int updateDSA(int score) {
        int dsa = this.scores.get("DSA");
        dsa += score;
        if (dsa < 0) dsa = 0;

        this.scores.put("DSA", dsa);

        return dsa;
    }

    public int updateDatabases(int score) {
        int db = this.scores.get("Databases");
        db += score;
        if (db < 0) db = 0;

        this.scores.put("Databases", db);

        return db;
    }

    public int updateSpring(int score) {
        int spring = this.scores.get("Spring");
        spring += score;
        if (spring < 0) spring = 0;

        this.scores.put("Spring", spring);

        return spring;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        User user = (User) obj;

        return id == user.getId() && email.equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        int result = 17;

        int emailHash = 0;
        for (char c : email.toCharArray()) {
            emailHash = 31 * emailHash + c;
        }

        result = 31 * result + id;
        result = 31 * result + emailHash;

        return result;
    }

    @Override
    public String toString() {
        return "User{name=%s,surname=%s,email=%s}".formatted(this.name, this.surname, this.email);
    }

    public String scoresToString() {
        return "%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d".formatted(
                this.id,
                this.scores.get("Java"),
                this.scores.get("DSA"),
                this.scores.get("Databases"),
                this.scores.get("Spring")
        );
    }
}
