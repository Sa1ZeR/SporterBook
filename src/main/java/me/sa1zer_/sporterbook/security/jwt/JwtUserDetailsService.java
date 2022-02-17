package me.sa1zer_.sporterbook.security.jwt;

import me.sa1zer_.sporterbook.model.enums.Role;
import me.sa1zer_.sporterbook.repository.base.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    public static final String USERNAME_SPLITTER = "<::>";

    //private final Map<Role, HumanRepository<?, ?>> repositories = new ConcurrentHashMap<>();

    private final List<HumanRepository<?, ?>> repositories;

    @Autowired
    public JwtUserDetailsService(List<HumanRepository<?, ?>> repositories) {
        this.repositories = repositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String login = username.split(USERNAME_SPLITTER)[0];
//        Role role = Role.valueOf(username.split(USERNAME_SPLITTER)[1].toUpperCase());

//        var repository = repositories.getOrDefault(role, null);
//        if(repository != null) {
//            BaseHuman human = (BaseHuman) repository.findByLoginOrEmail(login, login)
//                    .orElseThrow(() -> new UsernameNotFoundException(String.format(
//                            "User with login or email %s not found!", login)));
//
//            return JwtUserFactory.createJwtUser(human);
//        }
//
//        throw new UsernameNotFoundException(String.format(
//                "User with login or email %s not found!", login));

    }
}
