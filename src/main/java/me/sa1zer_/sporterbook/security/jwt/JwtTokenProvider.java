package me.sa1zer_.sporterbook.security.jwt;

import io.jsonwebtoken.*;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import me.sa1zer_.sporterbook.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.expired}")
    private long expiredTime;

    @Value("${jwt.token.secret}")
    private String secret;

    private final JwtUserDetailsService userDetailsService;

    public JwtTokenProvider(JwtUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(Authentication authentication) {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expired = new Date(System.currentTimeMillis() + expiredTime);

        String username = user.getUsername();

        Map<String, Object> claims = new HashMap<>();
        claims.put("login", username);
        claims.put("email", user.getEmail());
        claims.put("id", user.getId());
        //claims.put("roles", user.getAuthorities());

        return SecurityConstants.TOKEN_PREFIX + Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(now).setExpiration(expired)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException |
                ExpiredJwtException |  UnsupportedJwtException |
                IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(SecurityConstants.HEADER_AUTHORIZE);
        if(StringUtils.hasText(header) && header.startsWith(SecurityConstants.TOKEN_PREFIX))
            return header.split(" ")[1];
        return null;
    }

    public String getUser(String token) {
        return (String) Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().get("login");
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody();

        return Long.parseLong((String) claims.get("id"));
    }

    public Authentication getAuthentication(String token) {
//        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(createLoginWithRole(
//                getUser(token), getRole(token)));
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(getUser(token));
        return new UsernamePasswordAuthenticationToken(jwtUser, "", jwtUser.getAuthorities());
    }
}
