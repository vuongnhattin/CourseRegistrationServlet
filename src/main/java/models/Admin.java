package models;

import jakarta.persistence.*;
import utils.HTMLTable.IField;
import utils.HTMLTable.IRow;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Admin implements IRow, IField {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "USERNAME", nullable = true, length = 50)
    private String username;
    @Basic
    @Column(name = "NAME", nullable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "GENDER", nullable = true, length = 10)
    private String gender;
    @Basic
    @Column(name = "BIRTHDAY", nullable = true)
    private Date birthday;

    public Admin(Integer id, String username, String name, String gender, Date birthday) {
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
    }

    public Admin() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;
        return id == admin.id && Objects.equals(username, admin.username) && Objects.equals(name, admin.name) && Objects.equals(gender, admin.gender) && Objects.equals(birthday, admin.birthday);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(username);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(gender);
        result = 31 * result + Objects.hashCode(birthday);
        return result;
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

    @Override
    public List<String> getRow() {
        return List.of(username, name, gender, birthday.toString());
    }

    @Override
    public Integer getFieldNumber() {
        return 4;
    }

    @Override
    public List<String> getFieldVariables() {
        return List.of("username", "name", "gender", "birthday");
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("Tên đăng nhập", "Họ và tên", "Giới tính", "Ngày sinh");
    }

    @Override
    public List<String> getFieldTypes() {
        return List.of("text", "text", "select", "date");
    }

    @Override
    public List<List<String>> getFieldOptions() {
        return List.of(new ArrayList<>(), new ArrayList<>(), List.of("Nam", "Nữ", "Khác"), new ArrayList<>());
    }
}
