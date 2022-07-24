package server.controllers;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import server.models.User;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtController {
    private static final String SECRET = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    public static String createJWT(User user) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("user", user)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5l, ChronoUnit.HOURS)))
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }

    public static String createJWT(User user, long ttlMillis) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("user", user)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(ttlMillis, ChronoUnit.MILLIS)))
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }

    public static User getUser(String jwtString) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);
        // test
//        System.out.println(jwt);
//        System.out.println(jwt.getBody().get("user"));
//        System.out.println(jwt.getBody().get("user").getClass());
        Gson  gson = new Gson();
        String json = gson.toJson(jwt.getBody().get("user"), Map.class);
//        System.out.println(json);
        User user = gson.fromJson(json,User.class);
//        System.out.println(user.getAvatar());

        return user;
    }

    public static Jws<Claims> parseJwt(String jwtString) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);
        // test
//        System.out.println(jwt);
//        System.out.println(jwt.getBody().get("user"));
//        System.out.println(jwt.getBody().get("user").getClass());
//        Gson  gson = new Gson();
//        String json = gson.toJson(jwt.getBody().get("user"), Map.class);
//        System.out.println(json);
//        User user = gson.fromJson(json,User.class);
//        System.out.println(user.getAvatar());

        return jwt;
    }
}
