package com.munnitorbackend.Config;

import com.munnitorbackend.Service.IUserService;
import com.munnitorbackend.Service.UserServiceImplement;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import com.munnitorbackend.Model.Usuario;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
@Component
public class JwtUtil {

    //la firma es un sha256 de P@tit0
    private final static String firma= "18D80FAB6B2635DAC80E79DF01A545D4D98EF1E5B84D28DAA5E0969D213CEE18";

    private final static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    //String username
    static void addAuthentication(HttpServletResponse res,Authentication auth) {

        List<String> roles = auth.getAuthorities().stream().map(rol -> rol.getAuthority()).collect(Collectors.toList());

        String token = Jwts.builder()
            .setSubject(auth.getName())
                //seteo los roles
            .claim("roles", roles)
            .setIssuedAt(new Date())
            .setNotBefore(new Date())
            //expiracion del toker con 86400 le digo que es un dia
            .setExpiration(new Date(System.currentTimeMillis()+86400000))
                // Hash con el que firmaremos la clave
            .signWith(SignatureAlgorithm.HS512, firma.getBytes())
            .compact();

        //agregamos al encabezado el token
        res.addHeader("Authorization", "Bearer." + token);
    }

    static public String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return token;
    }


    // Método para validar el token enviado por el cliente
    //static Authentication getAuthentication(HttpServletRequest request) {
    static public String getUser(HttpServletRequest request) {

    // Obtenemos el token que viene en el encabezado de la peticion
        String token = getToken(request);

        // si hay un token presente, entonces lo validamos
        if (token != null && validarToken(token)) {
            String user = Jwts.parser()
                    .setSigningKey(firma.getBytes())
                    //.requireExpiration(new Date(Calendar.getInstance().getTime().getTime()+86400000))
                    .parseClaimsJws(token.replace("Bearer.", "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            if (user != null){
                return user;
            }else{
                return null;
            }
            //return user != null ?

                    //new UsernamePasswordAuthenticationToken(user, usuario.getPassword(), usuario.getAuthorities()) :
                    //null;
        }
        return null;
    }

    static public boolean validarToken(String token){
        try{
            Jwts.parser().setSigningKey(firma.getBytes()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Token mal firmado");
        }catch (UnsupportedJwtException e){
            logger.error("Token no soportado");
        }catch (IllegalArgumentException e){
            logger.error("Token vacio");
        }catch (ExpiredJwtException e){
            logger.error("Token expirado");
        }catch (SignatureException e) {
            logger.error("Fallo la firma del token");
        }
        return false;
    }
}
