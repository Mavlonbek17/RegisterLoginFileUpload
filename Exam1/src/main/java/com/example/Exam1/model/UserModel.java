package com.example.Exam1.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="users_table")
public class UserModel {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String login;
    String password;
    String email;
    String FileName;

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(login, userModel.login) && Objects.equals(password, userModel.password) && Objects.equals(email, userModel.email) && Objects.equals(FileName, userModel.FileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email, FileName);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", FileName='" + FileName + '\'' +
                '}';
    }
}
