package practicum.security.jwt;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import practicum.model.entity.Role;
import practicum.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                true,
                mapToGrantedAuthorities()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(){
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())
        ).collect(Collectors.toList());
    }
}
