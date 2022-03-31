package practicum.filter.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private boolean orPredicate;

    public SearchCriteria(final String key, final String operation,final Object value, final String orPredicate) {
        super();
        this.orPredicate = orPredicate != null && orPredicate.equals("'");
        this.key = key;
        this.value = value;
        this.operation = operation;
    }
}
