import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface ITest {
    int getNum();

    default List<Boolean> getList() {
        return new ArrayList<>(Collections.nCopies(getNum(), true));
    }
}
