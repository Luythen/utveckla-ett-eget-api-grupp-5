package org.acme.model;

public class UserResponseDto {

    public Long id;
    public String username;
    public String apiKey;

    public UserResponseDto(){
    }

    //konstruktorn som hämtar värden från User entiteten
    public UserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.apiKey = user.getApiKey();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public UserResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public UserResponseDto setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }
}