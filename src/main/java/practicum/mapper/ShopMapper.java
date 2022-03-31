package practicum.mapper;

import org.mapstruct.Mapper;
import practicum.dto.ShopDto;
import practicum.model.entity.Shop;

@Mapper(componentModel = "spring")
public interface ShopMapper {

    ShopDto toDto(Shop entity);

    Shop toEntity(ShopDto dto);
}
