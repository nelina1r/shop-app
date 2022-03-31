package practicum.filter.service.interfaces;

import org.springframework.data.jpa.domain.Specification;
import practicum.model.entity.Item;

public interface Filter {

    String pattern = "(\\w+?)(:|<|>)(\\w+?),";

    Specification<?> generateSearchSpecifications(String search);
}
