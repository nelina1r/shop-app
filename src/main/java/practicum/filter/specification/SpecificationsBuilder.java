package practicum.filter.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificationsBuilder<T> {
    private final List<SearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationsBuilder with(String key, String operation, Object value, String orPredicate){
        params.add(new SearchCriteria(key, operation, value, orPredicate));
        return this;
    }

    public Specification<T> build(){
        if(params.size() == 0){
            return null;
        }

        List<Specification<T>> specs = params.stream()
                .map(GetQuerySpecification<T>::new)
                .collect(Collectors.toList());
        Specification<T> result = specs.get(0);

        for(int i = 1; i < params.size(); i++){
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new GetQuerySpecification<>(params.get(i)))
                    : Specification.where(result).and(new GetQuerySpecification<>(params.get(i)));
        }
        return  result;
    }
}
