package daos;

import models.Account;
import models.Student;

public class StudentDAO extends AbstractDAO<Student> {
    public StudentDAO() {
        super(Student.class);
    }
}
