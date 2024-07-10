package ru.teamsource.bankruptservice.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

import java.util.Date;

@Data
public class Token {
    private String jwt;
    public boolean isExpired() {
        DecodedJWT jwt = JWT.decode(this.jwt);
        return jwt.getExpiresAt().before(new Date());
    }
}
