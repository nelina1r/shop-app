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
import org.springframework.web.bind.annotation.*;
import practicum.controller.url.Urls;
import practicum.dto.WaybillDto;
import practicum.model.entity.Waybill;
import practicum.service.interfaces.WaybillService;

import java.util.List;

@Api(value = "waybill controller")
@RestController
public class WaybillController {

    @Autowired
    private final WaybillService waybillService;


    public WaybillController(WaybillService waybillService) {
        this.waybillService = waybillService;
    }

    @ApiOperation("get all items in waybill")
    @GetMapping(Urls.Waybill.FULL)
    public ResponseEntity<List<WaybillDto>> findAllItemsInWaybill(
            @Spec(path = "itemId", paramSeparator = ',', spec = In.class) Specification<Waybill> spec,
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault(size = 20) Pageable page) {
        return ResponseEntity.ok(waybillService.findAllItemsInWaybill(spec, search, page));
    }

    @ApiOperation("add new item to waybill")
    @PostMapping(Urls.Waybill.Id.FULL)
    public ResponseEntity<String> addItemToWaybill(@PathVariable Long id){
        waybillService.addItemToWaybill(id);
        return ResponseEntity.ok("added");
    }

    @ApiOperation("delete all items from waybill")
    @PostMapping(Urls.Waybill.Clearlist.FULL)
    public ResponseEntity<String> deleteAllItemsFromWaybill(){
        waybillService.deleteAllItemsFromWaybill();
        return ResponseEntity.ok("deleted");
    }

    @ApiOperation("delete one item from waybill by id")
    @DeleteMapping(Urls.Waybill.Id.FULL)
    public ResponseEntity<String> deleteItemFromWaybill(@PathVariable Long id){
        waybillService.deleteItemFromWaybill(id);
        return ResponseEntity.ok("deleted");
    }

    @ApiOperation("buy all items in waybill")
    @PostMapping(Urls.Waybill.Buy.FULL)
    public ResponseEntity<String> buyWaybill(){
        waybillService.buyItemsInWaybill();
        return ResponseEntity.ok("bought");
    }

}
