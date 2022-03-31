package practicum.mapper;

import org.mapstruct.Mapper;
import practicum.dto.ItemDto;
import practicum.model.entity.Item;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item entity);

    Item toEntity(ItemDto dto);
}
