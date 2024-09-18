package com.example.playlist_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(HEADER);

        if (token == null || !token.startsWith(PREFIX)) {
            System.out.println("Token is missing or doesn't have the expected prefix");
            chain.doFilter(request, response);
            return;
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JwtKeyGenerator.getSecretKey())
                .build()
                .parseClaimsJws(token.replace(PREFIX, ""))
                .getBody();

        String user = claims.getSubject();
        System.out.println("User extracted from token: " + user);


        if (user != null) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
