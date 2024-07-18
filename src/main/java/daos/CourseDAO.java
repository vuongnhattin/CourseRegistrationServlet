package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.Course;
import models.Student;
import utils.JPAConfig;

import java.util.List;

public class CourseDAO extends AbstractDAO<Course> {
    public CourseDAO() {
        super(Course.class);
    }

    public List<Course> getCourseCurrentSemester() {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.semesterId = (select cs.semesterId from CurrentSemester cs where cs.id = 1)", Course.class);

        return query.getResultList();
    }

    public List<Course> getCourseCurrentSemesterOfStudent(Integer studentId) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.semesterId = (select cs.semesterId from CurrentSemester cs where cs.id = 1) and c.id in (select r.courseId from Register r where r.studentId = :studentId)", Course.class);
        query.setParameter("studentId", studentId);

        return query.getResultList();
    }

    public List<Course> getCourseCurrentSemesterNotOfStudent(Integer studentId) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.semesterId = (select cs.semesterId from CurrentSemester cs where cs.id = 1) and c.id not in (select r.courseId from Register r where r.studentId = :studentId)", Course.class);
        query.setParameter("studentId", studentId);

        return query.getResultList();
    }

    public List<Course> getCourseOfStudent(Integer studentId) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.id in (select r.courseId from Register r where r.studentId = :studentId)", Course.class);
        query.setParameter("studentId", studentId);

        return query.getResultList();
    }

    public List<Course> getCourseByStudentAndSemester(Integer studentId, Integer semesterId) {
        EntityManager entityManager = JPAConfig.getEntityManager();

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.id in (select r.courseId from Register r where r.studentId = :studentId) and c.semesterId = :semesterId", Course.class);
        query.setParameter("studentId", studentId);
        query.setParameter("semesterId", semesterId);

        return query.getResultList();
    }


}
