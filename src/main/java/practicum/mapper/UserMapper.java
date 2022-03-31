package practicum.mapper;

import org.mapstruct.Mapper;
import practicum.dto.UserDto;
import practicum.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
