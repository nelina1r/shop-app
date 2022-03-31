package practicum.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practicum.model.entity.User;
import practicum.security.jwt.JwtUser;
import practicum.security.jwt.JwtUserFactory;
import practicum.service.interfaces.UserService;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}
