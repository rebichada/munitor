package com.munnitorbackend.Config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Julito
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("UserServiceImplement")
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean 
     public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);

    }

    @Override
     protected void configure(HttpSecurity http) throws Exception {

        //antMatchers todas estas rutas hacen referencia a la ruta resources/templates ("/", "/App/principal" ....) o resources/static ("/js/**", "/css/**")
        http.authorizeRequests().antMatchers("/","/commonJS/**","/css/**","/users","/users/login","/users/new","/principal").permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers("/**").hasRole("USER").and().formLogin()
                //referencia al metodo get
                .loginPage("/users/login")
                .permitAll()
                //hace referencia al controlador de tipo get
                .defaultSuccessUrl("/home")
                .failureUrl("/users/login?error=true")
                //hace referencia al los name de los labels dentro del index.html
                .usernameParameter("email")
                .passwordParameter("password");
        //para logout, esto lo dejamos asi por spring security.
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");

    }

    



}

