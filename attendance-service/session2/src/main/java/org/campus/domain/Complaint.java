package org.campus.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by indrajeet on 4/9/16.
 */
@Entity
@Data
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TITLE")
    private String title;

    private enum Status {OPEN,CLOSED,RESOLVED}

    @Enumerated(EnumType.STRING)
    private Status status;

    public Complaint() {}
}
