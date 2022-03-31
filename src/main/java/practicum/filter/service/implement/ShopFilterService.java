package practicum.filter.service.implement;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import practicum.filter.service.interfaces.Filter;
import practicum.filter.specification.SpecificationsBuilder;
import practicum.model.entity.Shop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ShopFilterService implements Filter {

    @Override
    public Specification<Shop> generateSearchSpecifications(String search) {
        SpecificationsBuilder<Shop> builder = new SpecificationsBuilder<>();
        Pattern pattern = Pattern.compile(Filter.pattern);
        Matcher matcher = pattern.matcher(search + ",");
        while(matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(3));
        }
        return builder.build();
    }
}
