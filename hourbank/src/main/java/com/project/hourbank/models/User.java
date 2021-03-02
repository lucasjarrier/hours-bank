package com.project.hourbank.models;

import com.project.hourbank.enums.Level;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @NotNull
    private String name;
    @NonNull
    @NotNull
    private String login;
    @NonNull
    @NotNull
    private String password;
    @NonNull
    @NotNull
    private String mail;
    @NonNull
    @NotNull
    private Level level;
    @NonNull
    @NotNull
    private int[] timeWorked;
    private boolean enabled;
    private String validationCode;
    private int hours = 0;

    public User() {
    }

    public User(String name, String email, String password, String code) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.level = level;
        this.timeWorked = timeWorked;
        this.validationCode = code;
        this.enabled = false;
    }

    /**
     * Essa function calcula o saldo de horas do user.
     * @return
     */

    public int getHours() {
        int sum = 0;
        for (int i = 0; i < this.timeWorked.length; i++) {
            sum += (int) timeWorked[i];
        }
        return sum;
    }

    public Long getId() {
        return id;
    }

    public String getIdString() {
        String str = Long.toString(id);
        return str;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getMail() {
        return mail;
    }

    public void setMail(@NonNull String mail) {
        this.mail = mail;
    }

    @NonNull
    public Level getLevel() {
        return level;
    }

    public void setLevel(@NonNull Level level) {
        this.level = level;
    }

    @NonNull
    public int[] getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(@NonNull int[] timeWorked) {
        this.timeWorked = timeWorked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
