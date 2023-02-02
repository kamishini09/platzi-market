package com.platzi.market.web.secutiry;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "hernando";

    public String generateToken(UserDetails userDetails){
        /*obtenemos el usuario = userDetails.getUsername()  y le indicamso en que fecha es creado el Json web token*/
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000 *60*60*10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /*validar si el jwt token es correcto*/
    public boolean validateToken(String token, UserDetails userDetails){
        /*preguntamos primero si el usuario que esta llegando en la peticion sea el mismo que esta en el token*/
        /*y que no alla expirado el token*/
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /*extraer el usuario del token*/
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }
    /*verificamos si el token ya expiro*/
    public boolean isTokenExpired(String token){
        /*preguntamos si esta antes de la fecha actual*/
        return getClaims(token).getExpiration().before(new Date());
    }

    /*retornar objetos de json web token*/
    private Claims getClaims(String tokne){
        /*obtenemos el cuerpo del web tokne separado por cada uno de los objetos*/
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(tokne).getBody();
    }


}
