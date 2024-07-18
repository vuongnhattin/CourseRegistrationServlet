package models;

import daos.SubjectDAO;
import daos.TeacherDAO;
import jakarta.persistence.*;
import utils.HTMLTable.IField;
import utils.HTMLTable.IRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Course implements IField, IRow {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "SEMESTER_ID", nullable = true)
    private Integer semesterId;
    @Basic
    @Column(name = "SUBJECT_ID", nullable = true, length = 8)
    private String subjectId;
    @Basic
    @Column(name = "TEACHER_ID", nullable = true)
    private Integer teacherId;
    @Basic
    @Column(name = "ROOM", nullable = true, length = 10)
    private String room;
    @Basic
    @Column(name = "DAY", nullable = true, length = 20)
    private String day;
    @Basic
    @Column(name = "TIME", nullable = true, length = 50)
    private String time;
    @Basic
    @Column(name = "MAX_SLOTS", nullable = true)
    private Integer maxSlots;

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(Integer maxSlots) {
        this.maxSlots = maxSlots;
    }

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", insertable = false, updatable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", insertable = false, updatable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    List<Register> registers;

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(semesterId, course.semesterId) && Objects.equals(subjectId, course.subjectId) && Objects.equals(teacherId, course.teacherId) && Objects.equals(room, course.room) && Objects.equals(day, course.day) && Objects.equals(time, course.time) && Objects.equals(maxSlots, course.maxSlots);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(semesterId);
        result = 31 * result + Objects.hashCode(subjectId);
        result = 31 * result + Objects.hashCode(teacherId);
        result = 31 * result + Objects.hashCode(room);
        result = 31 * result + Objects.hashCode(day);
        result = 31 * result + Objects.hashCode(time);
        result = 31 * result + Objects.hashCode(maxSlots);
        return result;
    }

    public Course() {}

    public Course(Integer id, Integer semesterId, String subjectId, Integer teacherId, String room, String day, String time, Integer maxSlots) {
        this.id = id;
        this.semesterId = semesterId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.room = room;
        this.day = day;
        this.time = time;
        this.maxSlots = maxSlots;
    }

    @Override
    public List<String> getRow() {
        String teacherIdName = String.format("%d - %s", teacherId, teacher.getName());
        String subjectIdName = String.format("%s - %s", subjectId, subject.getName());
        return List.of(id.toString(), subjectIdName, subject.getCredits().toString(), teacherIdName, room, day, time, maxSlots.toString());
    }

    @Override
    public Integer getFieldNumber() {
        return 8;
    }

    @Override
    public List<String> getFieldVariables() {
        return List.of("id", "subjectIdName", "subjectCredits", "teacherIdName", "room", "day", "time", "maxSlots");
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("ID", "Môn học", "Số tín chỉ", "Giáo viên", "Tên phòng học", "Ngày học", "Ca học", "Số slot tối đa");
    }

    @Override
    public List<String> getFieldTypes() {
        return List.of("text", "select", "text", "select", "text", "select", "select", "text");
    }

    @Override
    public List<List<String>> getFieldOptions() {
        List<Subject> subjects = new SubjectDAO().findAll();
        List<Teacher> teachers = new TeacherDAO().findAll();
        List<String> subjectsIdName = new ArrayList<>();
        List<String> teachersIdName = new ArrayList<>();
        subjects.forEach(subject -> subjectsIdName.add(String.format("%s - %s", subject.getId(), subject.getName())));
        teachers.forEach(teacher -> teachersIdName.add(String.format("%d - %s", teacher.getId(), teacher.getName())));
        List<String> days = List.of("Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật");
        List<String> times = List.of("7:30 - 9:30", "9:30 - 11:30", "13:30 - 15:30", "15:30 - 17:30");
        List<String> temp = new ArrayList<>();
        return List.of(temp, subjectsIdName, temp, teachersIdName, temp, days, times, temp);
    }

    @Override
    public List<Boolean> getFieldDisabledAdd() {
        return List.of(true, false, true, false, false, false, false, false);
    }

    @Override
    public List<Boolean> getFieldDisabledUpdate() {
        return IField.super.getFieldDisabledUpdate();
    }
}
