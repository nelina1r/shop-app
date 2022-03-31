package practicum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class WaybillDto {

    @JsonIgnore
    private Long id;

    private Long itemId;

    private String name;

    private Long price;

    private Long quantity;

    private String shopName;
}
