package com.project.hourbank.models;

import com.project.hourbank.enums.Status;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Protocol implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idUser;

    private String loginUser;

    private String subject;

    private String description;

    private LocalDateTime date;

    private Status status;


    public Protocol() {
        this.id = id;
        this.idUser = idUser;
        this.subject = subject;
        this.description = description;
        this.date = date;
        this.status = status;
    }



}
