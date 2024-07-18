package models;

import jakarta.persistence.*;
import utils.HTMLTable.IField;
import utils.HTMLTable.IRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Subject implements IRow, IField {
    @Id
    @Column(name = "ID", nullable = false, length = 8)
    private String id;
    @Basic
    @Column(name = "NAME", nullable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "CREDITS", nullable = true)
    private Integer credits;

    public Subject() {}

    public Subject(String id, String name, Integer credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && Objects.equals(credits, subject.credits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(credits);
        return result;
    }

    @Override
    public Integer getFieldNumber() {
        return 3;
    }

    @Override
    public List<String> getFieldVariables() {
        return List.of("id", "name", "credits");
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("Mã môn học", "Tên môn học", "Số tín chỉ");
    }

    @Override
    public List<String> getFieldTypes() {
        return List.of("text", "text", "select");
    }

    @Override
    public List<List<String>> getFieldOptions() {
        return List.of(new ArrayList<>(), new ArrayList<>(), List.of("1", "2", "3", "4"));
    }

    @Override
    public List<String> getRow() {
        return List.of(id, name, credits.toString());
    }
}
