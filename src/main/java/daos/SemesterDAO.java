package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Semester;
import utils.JPAConfig;

import java.util.List;

public class SemesterDAO extends AbstractDAO<Semester> {
    public SemesterDAO() {
        super(Semester.class);
    }

    public Semester findSemesterByNameAndYear(String name, Integer year) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Semester> query = entityManager.createQuery("select s from Semester s where s.name = :name and s.year = :year", Semester.class);
        query.setParameter("name", name);
        query.setParameter("year", year);

        List<Semester> semesters = query.getResultList();
        return semesters.isEmpty() ? null : semesters.get(0);
    }
}
