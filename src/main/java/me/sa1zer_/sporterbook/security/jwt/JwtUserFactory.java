package me.sa1zer_.sporterbook.security.jwt;

import me.sa1zer_.sporterbook.domain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser createJwtUser(User user) {
        return new JwtUser(user.getId(), user.getEmail(), user.getLogin(),
                user.getPassword(), user.isActive(), getAuthorities(user));
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        return user.getAttributes().stream().map(attr ->
                new SimpleGrantedAuthority(attr.getRole().name())).collect(Collectors.toList());
    }
}
