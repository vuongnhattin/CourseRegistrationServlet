package models;

import jakarta.persistence.*;
import utils.HTMLTable.IField;
import utils.HTMLTable.IRow;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Semester implements IRow, IField {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "NAME", nullable = true, length = 3)
    private String name;
    @Basic
    @Column(name = "YEAR", nullable = true)
    private Integer year;
    @Basic
    @Column(name = "START_DATE", nullable = true)
    private Date startDate;
    @Basic
    @Column(name = "END_DATE", nullable = true)
    private Date endDate;
    @OneToMany(mappedBy = "semesterBySemesterId")
    private Collection<CurrentSemester> currentSemestersById;

    public Semester() {}

    public Semester(Integer id, String name, Integer year, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Semester semester = (Semester) o;
        return Objects.equals(id, semester.id) && Objects.equals(name, semester.name) && Objects.equals(year, semester.year) && Objects.equals(startDate, semester.startDate) && Objects.equals(endDate, semester.endDate);
    }

    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", currentSemestersById=" + currentSemestersById +
                '}';
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(year);
        result = 31 * result + Objects.hashCode(startDate);
        result = 31 * result + Objects.hashCode(endDate);
        return result;
    }

    @Override
    public Integer getFieldNumber() {
        return 5;
    }

    @Override
    public List<String> getFieldVariables() {
        return List.of("id", "name", "year", "startDate", "endDate");
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("Mã học kỳ", "Tên học kỳ", "Năm học", "Ngày bắt đầu", "Ngày kết thúc");
    }

    @Override
    public List<String> getFieldTypes() {
        return List.of("text", "select", "text", "date", "date");
    }

    @Override
    public List<List<String>> getFieldOptions() {
        List<String> temp = new ArrayList<>();
        return List.of(temp, List.of("HK1", "HK2", "HK3"), temp, temp, temp);
    }

    @Override
    public List<Boolean> getFieldDisabledAdd() {
        return List.of(true, false, false, false, false);
    }

    @Override
    public List<String> getRow() {
        return List.of(id.toString(), name, year.toString(), startDate.toString(), endDate.toString());
    }

    public Collection<CurrentSemester> getCurrentSemestersById() {
        return currentSemestersById;
    }

    public void setCurrentSemestersById(Collection<CurrentSemester> currentSemestersById) {
        this.currentSemestersById = currentSemestersById;
    }
}
