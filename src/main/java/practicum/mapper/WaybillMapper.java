package practicum.mapper;

import org.mapstruct.Mapper;
import practicum.dto.WaybillDto;
import practicum.model.entity.Waybill;

@Mapper(componentModel = "spring")
public interface WaybillMapper {

    WaybillDto toDto(Waybill entity);

}
