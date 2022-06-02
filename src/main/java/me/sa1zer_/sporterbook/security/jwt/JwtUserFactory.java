package me.sa1zer_.sporterbook.security.jwt;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.domain.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
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
        if(user.getRoles().contains(Role.ADMIN))
            return Arrays.stream(Role.values()).map(role -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toList());
        return user.getRoles().stream().map(r ->
                new SimpleGrantedAuthority(r.name())).collect(Collectors.toList());
    }
}
