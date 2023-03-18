package fr.eitelalaric.gestiondestock.config;

import fr.eitelalaric.gestiondestock.service.auth.ApplicationUserService;
import fr.eitelalaric.gestiondestock.utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApplicationFilterRequest extends OncePerRequestFilter {
    private JwtUtils jwtUtils;

    private ApplicationUserService applicationUserService;

    public ApplicationFilterRequest(JwtUtils jwtUtils, ApplicationUserService applicationUserService) {
        this.jwtUtils = jwtUtils;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("authorization");
        String username = null;
        String token = null;
         if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtUtils.getUsernameFromToken(token);
         }
         if (StringUtils.hasLength(username) && SecurityContextHolder.getContext().getAuthentication()==null){
             UserDetails userDetails = applicationUserService.loadUserByUsername(username);
             if (jwtUtils.validateToken(token)) {
                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,null, userDetails.getAuthorities()
                 );
                 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
             }
         }
            filterChain.doFilter(request, response);
    }
}
