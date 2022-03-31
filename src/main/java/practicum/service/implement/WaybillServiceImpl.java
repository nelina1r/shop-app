package practicum.service.implement;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practicum.dto.WaybillDto;
import practicum.filter.service.implement.WaybillFilterService;
import practicum.mapper.ItemWaybillPutMapper;
import practicum.mapper.WaybillMapper;
import practicum.model.entity.Status;
import practicum.model.entity.Waybill;
import practicum.model.repository.ItemRepository;
import practicum.model.repository.WaybillRepository;
import practicum.service.interfaces.WaybillService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaybillServiceImpl implements WaybillService {

    private final WaybillRepository waybillRepository;
    private final ItemRepository itemRepository;
    private final WaybillMapper waybillMapper;
    private final ItemWaybillPutMapper itemWaybillPutMapper;
    private final WaybillFilterService waybillFilterService;


    @Transactional
    @Override
    public void addItemToWaybill(Long id) {
        waybillRepository.save(itemWaybillPutMapper.itemToWaybill(itemRepository.findById(id).get()));
    }

    @Transactional
    @Override
    public void deleteItemFromWaybill(Long id) {
        waybillRepository.delete(waybillRepository.findById(id).get());
    }

    @Transactional
    @Override
    public void deleteAllItemsFromWaybill() {
        waybillRepository.deleteAll();
    }

    @Override
    public List<WaybillDto> findAllItemsInWaybill(Specification<Waybill> spec, String search, Pageable page) {
        if(StringUtils.isBlank(search))
            return waybillRepository.findAll(spec, page)
                    .stream()
                    .map(waybillMapper::toDto)
                    .collect(Collectors.toList());
        else
            return waybillRepository.findAll(waybillFilterService.generateSearchSpecifications(search), page)
                    .stream()
                    .map(waybillMapper::toDto)
                    .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void buyItemsInWaybill() {
        waybillRepository.buyItemsInWaybill((Status.BOUGHT.toString()));
    }
}
