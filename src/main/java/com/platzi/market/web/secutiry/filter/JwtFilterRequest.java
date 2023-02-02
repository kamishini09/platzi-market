package com.platzi.market.web.secutiry.filter;

import com.platzi.market.domain.service.PlatziUserDetailsService;
import com.platzi.market.web.secutiry.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*verifica que lo que viene en el encabezado de la peticion es un token y si el token es correcto*/
        String authorizationHeader = request.getHeader("Authorization");

        /*si viene el token y el authorizationHeader empieza con Bearer */
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            /*desde el caracter numero 7 el autenticacion header descontando el (Bearer ) y el espacio */
            String jwt = authorizationHeader.substring(7);
            /*verificar el usuaio */
            String username = jwtUtil.extractUsername(jwt);
            /*verificamos que no ha entrado a la app y aun no este debidamente logeado*/
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = platziUserDetailsService.loadUserByUsername(username);

                if(jwtUtil.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
