package com.alkemy.util;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtUtil {
    //Clave secreta
    //@Value("${security.jwt.secret}")
    private String key;
    //Dueño del token
    //@Value("${security.jwt.issuer}")
    private String issuer;
    //Tiempo de validez del token
    //@Value("${security.jwt.ttlMillis}")    
    private long ttlMillis;    

    public String create(String subject) {

        //Algoritmo para firmar el token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;                

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //Firma del token
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);        
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());        

        //Seteo de Claims        
        JwtBuilder builder = Jwts.builder().setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(signingKey, signatureAlgorithm);        
        
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }        
        return builder.compact();
    }
    
    public String getUsername(String jwt) {
        Claims claims = Jwts.claims();        
        return claims.getSubject();
    }
   
    public String getKey(String jwt) {       
        Claims claims = Jwts.claims();        
        return claims.getId();
    }
     
    public Collection<Object> getClaims(String jwt) {        
        Claims claims = Jwts.claims();
        return claims.values();
    }
    public boolean isTokenExpired(String token){
        Claims claims = Jwts.claims(); 
        return claims.getExpiration().before(new Date());
    }
    
    public boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsername(token);
        return (userDetails.getUsername().equals(username) && !isTokenExpired(token));
    }
}
