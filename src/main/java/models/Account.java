package models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Account implements Serializable {
    @Id
    @Column(name = "USERNAME")
    private String username;

    public Account() {}

    public Account(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    @Basic
    @Column(name = "PASSWORD")
    private String password;
    @Basic
    @Column(name = "TYPE")
    private String type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(type, account.type);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(username);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(type);
        return result;
    }

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
