package practicum.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import practicum.dto.WaybillDto;
import practicum.model.entity.Waybill;

import java.util.List;

public interface WaybillService {

    void addItemToWaybill(Long id);

    void deleteItemFromWaybill(Long id);

    void deleteAllItemsFromWaybill();

    List<WaybillDto> findAllItemsInWaybill(Specification<Waybill> spec, String search, Pageable page);

    void buyItemsInWaybill();
}
