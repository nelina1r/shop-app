package practicum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import practicum.model.entity.Status;

@ApiModel
@Data
@NoArgsConstructor
public class ItemDto {

    @JsonIgnore
    private Long id;

    private String name;

    private Long price;

    private Long quantity;

    private String category;

    private String shopName;

    private Status status;
}
