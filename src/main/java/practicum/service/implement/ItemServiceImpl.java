package practicum.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicum.dto.ItemDto;
import practicum.filter.service.implement.ItemFilterService;
import practicum.mapper.ItemMapper;
import practicum.model.entity.Item;
import practicum.model.entity.Status;
import practicum.model.repository.ItemRepository;
import practicum.service.interfaces.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemFilterService itemFilterService;

    @Override
    public List<ItemDto> findAll(Specification<Item> spec, String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return itemRepository.findAll(spec, page)
                .stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
        else
            return itemRepository.findAll(itemFilterService.generateSearchSpecifications(search), page)
                    .stream()
                    .map(itemMapper::toDto)
                    .collect(Collectors.toList());
    }

    @Override
    public ItemDto getById(Long id) {
        return itemMapper.toDto(itemRepository.getById(id));
    }

    @Transactional
    @Override
    public ItemDto save(ItemDto itemDto) {
        itemRepository.save(itemMapper.toEntity(itemDto));
        return itemDto;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ItemDto buyItem(Long id) {
        itemRepository.setItemStatusById(Status.BOUGHT, Status.ONSTORAGE, id);
        return itemMapper.toDto(itemRepository.getById(id));
    }


    @Transactional
    @Override
    public void changeStatusOnItemsBoughtToDeleted(){
        itemRepository.setItemStatus(Status.DELETED, Status.BOUGHT);
    }
}
