package models;

import jakarta.persistence.*;
import utils.HTMLTable.IField;
import utils.HTMLTable.IRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "CLASS1", schema = "dbo", catalog = "QLHP_JAVA")
public class Class1 implements IRow, IField {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "COUNT_STUDENTS", nullable = true, insertable = false, updatable = false)
    private Integer countStudents;

    public Integer getCountStudents() {
        return countStudents;
    }

    public void setCountStudents(Integer countStudents) {
        this.countStudents = countStudents;
    }

    @Basic
    @Column(name = "COUNT_BOYS", nullable = true, insertable = false, updatable = false)
    private Integer countBoys;

    public Integer getCountBoys() {
        return countBoys;
    }

    public void setCountBoys(Integer countBoys) {
        this.countBoys = countBoys;
    }

    @Basic
    @Column(name = "COUNT_GIRLS", nullable = true, insertable = false, updatable = false)
    private Integer countGirls;

    public Integer getCountGirls() {
        return countGirls;
    }

    public void setCountGirls(Integer countGirls) {
        this.countGirls = countGirls;
    }

    @Basic
    @Column(name = "COUNT_OTHERS", nullable = true, insertable = false, updatable = false)
    private Integer countOthers;

    public Integer getCountOthers() {
        return countOthers;
    }

    public void setCountOthers(Integer countOthers) {
        this.countOthers = countOthers;
    }

    @OneToMany(mappedBy = "class1", cascade = CascadeType.ALL)
    List<Study> studies = new ArrayList<>();

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class1 that = (Class1) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(countStudents, that.countStudents) && Objects.equals(countBoys, that.countBoys) && Objects.equals(countGirls, that.countGirls) && Objects.equals(countOthers, that.countOthers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(countStudents);
        result = 31 * result + Objects.hashCode(countBoys);
        result = 31 * result + Objects.hashCode(countGirls);
        result = 31 * result + Objects.hashCode(countOthers);
        return result;
    }

    public Class1() {}

    public Class1(Integer id, String name, Integer countStudents, Integer countBoys, Integer countGirls, Integer countOthers) {
        this.id = id;
        this.name = name;
        this.countStudents = countStudents;
        this.countBoys = countBoys;
        this.countGirls = countGirls;
        this.countOthers = countOthers;
    }

    @Override
    public Integer getFieldNumber() {
        return 6;
    }

    @Override
    public List<String> getFieldVariables() {
        return List.of("id", "name", "countStudents", "countBoys", "countGirls", "countOthers");
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("ID", "Tên lớp", "Tổng số sinh viên", "Tổng số nam", "Tổng số nữ", "Tổng số khác");
    }

    @Override
    public List<Boolean> getFieldDisabledAdd() {
        return List.of(true, false, true, true, true, true);
    }

    @Override
    public List<Boolean> getFieldDisabledUpdate() {
        return List.of(true, false, true, true, true, true);
    }

    @Override
    public List<String> getRow() {
        return List.of(id.toString(), name, countStudents.toString(), countBoys.toString(), countGirls.toString(), countOthers.toString());
    }
}
