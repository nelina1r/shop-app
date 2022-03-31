package practicum.service.interfaces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import practicum.dto.ItemDto;
import practicum.model.entity.Item;

import java.util.List;

public interface ItemService {

    List<ItemDto> findAll(Specification<Item> spec, String search, Pageable page);

    ItemDto getById(Long id);

    ItemDto save(ItemDto item);

    void deleteById(Long id);

    ItemDto buyItem(Long id);

    void changeStatusOnItemsBoughtToDeleted();
}
