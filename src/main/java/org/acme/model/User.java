package org.acme.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    //skapar en unik API-nyckel
    public User() {
            this.apiKey = UUID.randomUUID().toString();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public User setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
}
