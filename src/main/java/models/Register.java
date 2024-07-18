package models;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(models.RegisterPK.class)
public class Register {
    @Id
    @jakarta.persistence.Column(name = "STUDENT_ID", nullable = false)
    private Integer studentId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Id
    @jakarta.persistence.Column(name = "COURSE_ID", nullable = false)
    private Integer courseId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "TIME", nullable = true)
    private Timestamp time;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID", insertable = false, updatable = false)
    private Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Register register = (Register) o;
        return Objects.equals(studentId, register.studentId) && Objects.equals(courseId, register.courseId) && Objects.equals(time, register.time);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(studentId);
        result = 31 * result + Objects.hashCode(courseId);
        result = 31 * result + Objects.hashCode(time);
        return result;
    }

    public Register() {}

    public Register(Integer studentId, Integer courseId, Timestamp time) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.time = time;
    }
}
