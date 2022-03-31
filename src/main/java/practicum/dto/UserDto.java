package practicum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practicum.model.entity.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    @JsonIgnore
    private Long id;

    private String username;

    private String password;

    private Role role;
}
