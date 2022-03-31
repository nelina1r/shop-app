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
import practicum.dto.ShopDto;
import practicum.model.entity.Shop;
import practicum.service.interfaces.ShopService;

import java.util.List;

@Api(value = "shop controller")
@RestController
public class ShopController {

    @Autowired
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @ApiOperation("add new shop")
    @PostMapping(Urls.Shop.FULL)
    public ResponseEntity<String> addShop(ShopDto shopDto){
        shopService.addShop(shopDto);
        return ResponseEntity.ok("added");
    }

    @ApiOperation("get all shops")
    @GetMapping(Urls.Shop.FULL)
    public ResponseEntity<List<ShopDto>> findAllShops(
            @Spec(path = "id", paramSeparator = ',', spec = In.class) Specification<Shop> spec,
                @RequestParam(value = "search", required = false) String search,
                @PageableDefault(size = 20) Pageable page){
        return ResponseEntity.ok(shopService.findAllShops(spec, search, page));
    }

    @ApiOperation("get shop by id")
    @GetMapping(Urls.Shop.Id.FULL)
    public ResponseEntity<ShopDto> getShopById(@PathVariable Long id){
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("delete shop by id")
    @DeleteMapping(Urls.Shop.Id.FULL)
    public ResponseEntity<String> deleteShopById(@PathVariable Long id){
        shopService.deleteShopById(id);
        return ResponseEntity.ok("deleted");
    }
}
