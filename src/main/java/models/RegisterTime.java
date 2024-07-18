package models;

import jakarta.persistence.*;
import utils.HTMLTable.IField;
import utils.HTMLTable.IRow;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "REGISTER_TIME", schema = "dbo", catalog = "QLHP_JAVA")
public class RegisterTime implements IRow, IField {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "START_TIME", nullable = true)
    private Timestamp startTime;
    @Basic
    @Column(name = "END_TIME", nullable = true)
    private Timestamp endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterTime that = (RegisterTime) o;
        return Objects.equals(id, that.id) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(startTime);
        result = 31 * result + Objects.hashCode(endTime);
        return result;
    }

    public RegisterTime() {}

    public RegisterTime(Integer id, Timestamp startTime, Timestamp endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public List<String> getRow() {
        return List.of(startTime.toString(), endTime.toString());
    }

    @Override
    public Integer getFieldNumber() {
        return 2;
    }

    @Override
    public List<String> getFieldVariables() {
        return List.of("startTime", "endTime");
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("Thời gian bắt đầu", "Thời gian kết thúc");
    }

    @Override
    public List<Boolean> getFieldDisabledAdd() {
        return List.of(false, false);
    }

    @Override
    public List<Boolean> getFieldDisabledUpdate() {
        return List.of(false, false);
    }

    @Override
    public List<String> getFieldTypes() {
        return List.of("datetime-local", "datetime-local");
    }
}
