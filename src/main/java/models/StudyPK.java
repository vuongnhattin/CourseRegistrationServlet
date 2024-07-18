package models;

import java.io.Serializable;
import java.util.Objects;

public class StudyPK implements Serializable {
    private int studentId;
    private int class1Id;

    public StudyPK() {}

    public StudyPK(int studentId, int class1Id) {
        this.studentId = studentId;
        this.class1Id = class1Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyPK studyPK = (StudyPK) o;
        return studentId == studyPK.studentId && class1Id == studyPK.class1Id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, class1Id);
    }
}
