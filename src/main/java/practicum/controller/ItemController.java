package practicum.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import practicum.controller.url.Urls;
import practicum.dto.ItemDto;
import practicum.model.entity.Item;
import practicum.service.interfaces.ItemService;

import java.util.List;

@Api(value = "item controller")
@RestController
public class ItemController {

    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @ApiOperation("add new item")
    @PostMapping(Urls.Item.FULL)
    public ResponseEntity<String> newItem(@RequestBody ItemDto itemDto){
        itemService.save(itemDto);
        return ResponseEntity.ok("Saved");
    }

    @ApiOperation("get all items with searching")
    @GetMapping(Urls.Item.FULL)
    public List<ItemDto> findAll(
        @Spec(path = "id", paramSeparator = ',', spec = In.class) Specification<Item> spec,
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault(size = 20) Pageable page)
    {
        return itemService.findAll(spec, search, page);
    }

    @ApiOperation("get item by id")
    @GetMapping(Urls.Item.Id.FULL)
    public ResponseEntity<ItemDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getById(id));
    }

    @ApiOperation("delete item by id")
    @DeleteMapping(Urls.Item.Id.FULL)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @ApiOperation("buy item")
    @PostMapping(Urls.Item.Id.FULL)
    public ResponseEntity<ItemDto> buyItem(@PathVariable Long id){
        return ResponseEntity.ok(itemService.buyItem(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("debite all items")
    @PostMapping(Urls.Item.Debite.FULL)
    public ResponseEntity<String> changeStatusOnItemsBoughtToDeleted(){
        itemService.changeStatusOnItemsBoughtToDeleted();
        return ResponseEntity.ok("debited");
    }
}
