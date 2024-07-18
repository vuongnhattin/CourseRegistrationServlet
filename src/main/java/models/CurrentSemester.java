package models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CURRENT_SEMESTER", schema = "dbo", catalog = "QLHP_JAVA")
public class CurrentSemester {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "SEMESTER_ID", nullable = true)
    private Integer semesterId;
    @ManyToOne
    @JoinColumn(name = "SEMESTER_ID", referencedColumnName = "ID", insertable=false, updatable=false)
    private Semester semesterBySemesterId;

    public CurrentSemester() {}

    public CurrentSemester(Integer id, Integer semesterId) {
        this.id = id;
        this.semesterId = semesterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentSemester that = (CurrentSemester) o;
        return Objects.equals(id, that.id) && Objects.equals(semesterId, that.semesterId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(semesterId);
        return result;
    }

    public Semester getSemesterBySemesterId() {
        return semesterBySemesterId;
    }

    public void setSemesterBySemesterId(Semester semesterBySemesterId) {
        this.semesterBySemesterId = semesterBySemesterId;
    }
}
