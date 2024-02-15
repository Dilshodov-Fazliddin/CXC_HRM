package org.example.cxc_hrm.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.cxc_hrm.exception.NotAcceptableException;
import org.example.cxc_hrm.service.auth.AuthenticationService;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenService jwtService;
    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);
        Jws<Claims> claimsJws = jwtService.extractToken(token);
        Date expiration = claimsJws.getBody().getExpiration();
        if (new Date().after(expiration)) throw new NotAcceptableException("Expired access token!");
        authenticationService.Authenticate(claimsJws.getBody(), request);

        filterChain.doFilter(request, response);
    }
}