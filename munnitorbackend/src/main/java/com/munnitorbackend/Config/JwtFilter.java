package com.munnitorbackend.Config;

import com.munnitorbackend.Service.IUserService;
import com.munnitorbackend.Service.UserServiceImplement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import com.munnitorbackend.Model.Usuario;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Las peticiones que no sean /login pasarán por este filtro
 * el cuál se encarga de pasar el "request" a nuestra clase de utilidad JwtUtil
 * para que valide el token.
 *///GenericFilterBean
public class JwtFilter extends GenericFilterBean implements AuthenticationEntryPoint {

    private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    static IUserService userService;

    @Qualifier("userServiceImplement")
    @Autowired
    public UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            String nombreUsuario =JwtUtil.getUser((HttpServletRequest) servletRequest);
            if (nombreUsuario!=null){
                UserDetails user =userDetailsService.loadUserByUsername(nombreUsuario);
                if (user!= null){
                    Authentication authentication=new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    commence((HttpServletRequest)servletRequest,(HttpServletResponse) servletResponse,null);
                }

            }else{
                //
                commence((HttpServletRequest)servletRequest,(HttpServletResponse) servletResponse,null);
            }
        }catch (ServletException e){
            logger.error("Causa del error: " + e.getCause() + " Informacion: " + e.getMessage());
        }catch (IOException e){

        }catch (Exception e){

        }
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}