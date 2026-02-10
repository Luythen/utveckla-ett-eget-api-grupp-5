package org.acme.service;

import java.util.HashMap;
import java.util.UUID;

import org.acme.model.User;

public abstract class TokenService {
    
    private static final HashMap<UUID, User> TOKEN_HASH_MAP = new HashMap<>();

    public static String generateToken (User user) {
        UUID token = UUID.randomUUID();
        TOKEN_HASH_MAP.put(token, user);
        return token.toString();
    }

    public static User getUserByToken (UUID token) {
        return TOKEN_HASH_MAP.get(token);
    }

}
