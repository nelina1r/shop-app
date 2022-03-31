package practicum.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import practicum.dto.ShopDto;
import practicum.model.entity.Shop;

import java.util.List;

public interface ShopService {

    void addShop(ShopDto shopDto);

    List<ShopDto> findAllShops(Specification<Shop> spec, String search, Pageable page);

    void deleteShopById(Long id);

    ShopDto getShopById(Long id);
}
