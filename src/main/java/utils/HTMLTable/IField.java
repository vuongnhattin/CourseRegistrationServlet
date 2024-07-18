package utils.HTMLTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface IField {
    Integer getFieldNumber();
    default List<String> getFieldVariables() {
        return Collections.nCopies(getFieldNumber(), "");
    }
    default List<String> getFieldNames() {
        return Collections.nCopies(getFieldNumber(), "");
    }
    default List<String> getFieldTypes() {
        return Collections.nCopies(getFieldNumber(), "text");
    }
    default List<List<String>> getFieldOptions() {
        return Collections.nCopies(getFieldNumber(), null);
    }
    default List<Boolean> getFieldDisabledAdd() {
        return Collections.nCopies(getFieldNumber(), false);
    }
    default List<Boolean> getFieldDisabledUpdate() {
        List<Boolean> res = new ArrayList<>(Collections.nCopies(getFieldNumber(), false));
        res.set(0, true);

        return res;
    }
}
