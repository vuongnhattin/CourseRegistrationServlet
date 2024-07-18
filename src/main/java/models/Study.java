package models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@jakarta.persistence.IdClass(models.StudyPK.class)
public class Study {
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
    @jakarta.persistence.Column(name = "CLASS1_ID", nullable = false)
    private Integer class1Id;

    public Integer getClassTableId() {
        return class1Id;
    }

    public void setClassTableId(Integer class1Id) {
        this.class1Id = class1Id;
    }

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "CLASS1_ID", insertable = false, updatable = false)
    private Class1 class1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Study study = (Study) o;
        return Objects.equals(studentId, study.studentId) && Objects.equals(class1Id, study.class1Id);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(studentId);
        result = 31 * result + Objects.hashCode(class1Id);
        return result;
    }

    public Study() {}

    public Study(Integer studentId, Integer class1Id) {
        this.studentId = studentId;
        this.class1Id = class1Id;
    }
}
