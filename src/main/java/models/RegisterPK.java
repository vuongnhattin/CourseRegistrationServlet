package models;

import java.io.Serializable;
import java.util.Objects;

public class RegisterPK implements Serializable {
    private Integer studentId;
    private Integer courseId;

    public RegisterPK() {}

    public RegisterPK(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterPK that = (RegisterPK) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
