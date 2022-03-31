package practicum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import practicum.model.entity.Item;
import practicum.model.entity.Waybill;

@Mapper(componentModel = "spring")
public interface ItemWaybillPutMapper {

    @Mappings({
            @Mapping(target = "itemId", source = "id")
    })
    Waybill itemToWaybill(Item item);

}
