package com.example.itddd.sns.infrastructure.jpa.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class UserDataModel {
    @Id
    private String id;
    private String name;
    private int type;

    public UserDataModel(String id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public UserDataModel() {
    }
}