package practicum.filter.service.implement;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import practicum.filter.specification.SpecificationsBuilder;
import practicum.filter.service.interfaces.Filter;
import practicum.model.entity.Item;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ItemFilterService implements Filter {

    @Override
    public Specification<Item> generateSearchSpecifications(String search){
        SpecificationsBuilder<Item> builder = new SpecificationsBuilder<>();
        Pattern pattern = Pattern.compile(Filter.pattern);
        Matcher matcher = pattern.matcher(search + ",");
        while(matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(3));
        }
        return builder.build();
    }
}
