package practicum.service.interfaces;

import org.springframework.http.ResponseEntity;
import practicum.dto.AuthenticationRequestDto;
import practicum.dto.UserDto;
import practicum.model.entity.User;

import java.util.List;

public interface UserService {
    void register(User user);

    List<UserDto> getAllUsers();

    User findByUsername(String username);

    UserDto findUserById(Long id);

    void deleteUserById(Long id);
}
