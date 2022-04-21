package me.sa1zer_.sporterbook.security.jwt;

import me.sa1zer_.sporterbook.domain.model.User;
import me.sa1zer_.sporterbook.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    //private final Map<Role, HumanRepository<?, ?>> repositories = new ConcurrentHashMap<>();

    //private final List<HumanRepository<?, ?>> repositories;

    //    @Autowired
//    public JwtUserDetailsService(List<HumanRepository<?, ?>> repositories) {
//        this.repositories = repositories;
//    }

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with %s login or email not found", username)));
        return JwtUserFactory.createJwtUser(user);

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
