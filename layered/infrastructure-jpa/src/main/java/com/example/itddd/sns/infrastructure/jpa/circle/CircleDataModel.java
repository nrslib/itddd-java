package com.example.itddd.sns.infrastructure.jpa.circle;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "circles")
public class CircleDataModel {
    @Id
    private String id;
    private String name;
    private String ownerId;
    @ElementCollection
    @CollectionTable(name = "circles_to_member_id", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "member_id")
    private List<String> members;

    public CircleDataModel(String id, String name, String ownerId, List<String> members) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.members = members;
    }

    public CircleDataModel() {}
}
