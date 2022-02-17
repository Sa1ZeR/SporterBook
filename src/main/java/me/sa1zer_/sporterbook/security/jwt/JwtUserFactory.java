package me.sa1zer_.sporterbook.security.jwt;

import me.sa1zer_.sporterbook.model.base.BaseHuman;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser createJwtUser(BaseHuman human) {
        return new JwtUser(human.getId(), human.getEmail(), human.getLogin(),
                human.getPassword(), human.isActive(), getAuthorities(human));
    }

    private static List<GrantedAuthority> getAuthorities(BaseHuman human) {
        return null;
    }
}
