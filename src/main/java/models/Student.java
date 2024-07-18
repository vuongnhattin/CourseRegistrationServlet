package models;

import jakarta.persistence.*;
import utils.HTMLTable.IField;
import utils.HTMLTable.IRow;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Student implements IField, IRow {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "ID", nullable = false)
    private Integer id;

    public Student() {}

    public Student(Integer id, String username, String name, String gender, Date birthday, String department) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME", nullable = true, length = 50)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "GENDER", nullable = true, length = 10)
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "BIRTHDAY", nullable = true)
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "DEPARTMENT", nullable = true, length = 50)
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @OneToOne
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME", insertable = false, updatable = false)
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Study> studies = new ArrayList<>();

    public List<Study> getStudies() {
        return studies;
    }

    public void setStudies(List<Study> studies) {
        this.studies = studies;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Register> registers = new ArrayList<>();

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(username, student.username) && Objects.equals(name, student.name) && Objects.equals(gender, student.gender) && Objects.equals(birthday, student.birthday) && Objects.equals(department, student.department);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(username);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(gender);
        result = 31 * result + Objects.hashCode(birthday);
        result = 31 * result + Objects.hashCode(department);
        return result;
    }

    @Override
    public Integer getFieldNumber() {
        return 5;
    }

    @Override
    public List<String> getFieldVariables() {
        return List.of("username", "name", "gender", "birthday", "department");
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("Tên đăng nhập", "Họ và tên", "Giới tính", "Ngày sinh", "Khoa");
    }

    @Override
    public List<String> getFieldTypes() {
        return List.of("text", "text", "select", "date", "select");
    }

    @Override
    public List<List<String>> getFieldOptions() {
        List<String> temp = new ArrayList<>();
        return List.of(temp, temp, List.of("Nam", "Nữ", "Khác"), temp, List.of("Công nghệ thông tin", "Sinh học", "Vật lý", "Hoá học", "Toán tin"));
    }

    @Override
    public List<String> getRow() {
        return List.of(username, name, gender, birthday.toString(), department);
    }
}
