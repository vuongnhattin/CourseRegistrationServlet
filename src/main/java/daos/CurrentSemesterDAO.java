package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.CurrentSemester;
import models.Semester;
import utils.JPAConfig;

public class CurrentSemesterDAO extends AbstractDAO<CurrentSemester> {
    public CurrentSemesterDAO() {
        super(CurrentSemester.class);
    }

    public String getCurrentSemesterName() {
        return this.findById(1).getSemesterBySemesterId().getName();
    }

    public Integer getCurrentSemesterYear() {
        return this.findById(1).getSemesterBySemesterId().getYear();
    }
}
