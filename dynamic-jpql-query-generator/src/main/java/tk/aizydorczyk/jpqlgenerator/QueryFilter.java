package tk.aizydorczyk.jpqlgenerator;

import static java.util.Objects.isNull;

public class QueryFilter {
    private String field;
    private String value;

    public QueryFilter() {
    }

    String getField() {
        if (isNull(field)) {
            return null;
        } else {
            return field.replace('.', '_');
        }

    }

    public void setField(String field) {
        this.field = field;
    }

    String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
