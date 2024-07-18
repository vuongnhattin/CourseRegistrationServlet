import daos.*;
import models.*;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HibernateMyTest {
    @Test
    public void test() {
        System.out.println(new RegisterDAO().getNumberOfCourseOfStudentInCurrentSemester(1));
    }

    @Test
    public void test2() {
    }
}
