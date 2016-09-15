package org.campus.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by indrajeet on 4/9/16.
 */
@Entity
@Data
public class Complaint extends BaseEntity {

    @Column(name = "DESCRIPTION",nullable = false)
    private String description;

    @Column(name = "RESPONSIBLE",nullable = false)
    private String responsible;

    @Column(name = "TITLE",nullable = false)
    private String title;


    private enum Status {OPEN,CLOSED,RESOLVED}

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Complaint(String description, String title,String status,String responsible) {
        this.description=description;
        this.title=title;
        this.status=Status.valueOf(status);
        this.responsible=responsible;
    }

    public Status getStatus(String status) {
        return Status.valueOf(status);
    }
    public void setStatus(String status) {this.status=Status.valueOf(status);}

    public Complaint() {}
}
