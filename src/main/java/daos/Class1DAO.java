package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Class1;
import models.Student;
import utils.JPAConfig;

import java.util.List;

public class Class1DAO extends AbstractDAO<Class1> {
    public Class1DAO() {
        super(Class1.class);
    }

    public List<Student> getStudentsInClassById(Integer id) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.id in (select st.studentId from Study st where st.class1Id = :id)", Student.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Student> getStudentsNotInClassById(Integer id) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.id not in (select st.studentId from Study st where st.class1Id = :id)", Student.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
