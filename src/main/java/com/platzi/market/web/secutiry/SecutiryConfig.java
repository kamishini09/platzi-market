package com.platzi.market.web.secutiry;

import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.secutiry.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(platziUserDetailsService);
    }

    /*indicamos que para consumir el servicio de AuthController no necesitan estar autenticados porque con ese es que se autentican*/
    /*el filtro es el encargado de procesar todas las peticiones */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*esto significa que todas las peticiones que terminene en  authenticate las permita
        *  con lo (**) en el paht dice que no importa lo que tenga antes del authenticate
        * */
        http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }


    /*le estadomos indicando a sping que lo estamos eligiendo a el como gestor de autenticacion de la aplicacion por eso debemos agregarlo
    * y ademas agregar el @Bean*/
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
