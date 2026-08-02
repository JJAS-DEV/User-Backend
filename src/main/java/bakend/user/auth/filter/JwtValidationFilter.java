package bakend.user.auth.filter;

import static bakend.user.auth.TokenJwt.*;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import bakend.user.auth.SimpleGrantedAutorityJsonCreator;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;

        }
        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();
            // String username2 = (String) claims.get("username");
            Object autoritiesClaims = claims.get("authorities");
            Collection<? extends GrantedAuthority> roles = Arrays.asList(
                new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAutorityJsonCreator.class)
                
                
                .readValue(autoritiesClaims.toString().getBytes(), 
                SimpleGrantedAuthority[].class));


            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    roles);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            
        } catch (JwtException e) {
            Map<String, String>body= new HashMap<>(); 
            body.put("error", e.getMessage());
            body.put("messege", "el token es invalido!!");


            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(401);
            response.setContentType(CONTENT_TYPE);
            

        }
    }

}
