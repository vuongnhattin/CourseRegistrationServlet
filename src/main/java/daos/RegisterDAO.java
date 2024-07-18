package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Register;
import models.Student;
import utils.JPAConfig;

import java.util.List;

public class RegisterDAO extends AbstractDAO<Register> {
    public RegisterDAO() {
        super(Register.class);
    }

    public Long getNumberOfCourseOfStudentInCurrentSemester(Integer studentId) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        Integer semesterId = new CurrentSemesterDAO().findById(1).getSemesterId();

        TypedQuery<Long> query = entityManager.createQuery("select count(*) from Register r, Course c, CurrentSemester cs where r.courseId = c.id and c.semesterId = cs.semesterId and cs.id = 1 and r.studentId = :studentId", Long.class);
        query.setParameter("studentId", studentId);
        Long result = query.getSingleResult();

        return result == null ? 0 : result;
    }

    public List<Student> getStudentOfCourse(Integer courseId) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Student> query = entityManager.createQuery("select s from Student s, Register r where s.id = r.studentId and r.courseId = :courseId", Student.class);
        query.setParameter("courseId", courseId);

        return query.getResultList();
    }
}
