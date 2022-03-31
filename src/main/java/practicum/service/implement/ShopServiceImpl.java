package practicum.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicum.dto.ShopDto;
import practicum.filter.service.implement.ShopFilterService;
import practicum.mapper.ShopMapper;
import practicum.model.entity.Shop;
import practicum.model.repository.ShopRepository;
import practicum.service.interfaces.ShopService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper shopMapper;
    private final ShopFilterService shopFilterService;

    @Transactional
    @Override
    public void addShop(ShopDto ShopDto) {
        shopRepository.save(shopMapper.toEntity(ShopDto));
    }

    @Override
    public List<ShopDto> findAllShops(Specification<Shop> spec, String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return shopRepository.findAll(spec, page)
                    .stream()
                    .map(shopMapper::toDto)
                    .collect(Collectors.toList());
        else
            return shopRepository.findAll(shopFilterService.generateSearchSpecifications(search), page)
                    .stream()
                    .map(shopMapper::toDto)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteShopById(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public ShopDto getShopById(Long id) {
        return shopMapper.toDto(shopRepository.findById(id).get());
    }

}
