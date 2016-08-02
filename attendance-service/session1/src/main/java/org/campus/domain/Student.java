package org.campus.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by amey on 26/7/16.
 */
@Data
@Entity
public class Student extends BaseEntity {
    @Column(name = "ROLL", nullable = false)
    private Integer roll;
    @Column(name = "NAME", nullable = false)
    private String name;

    public Student() {}

    public Student(Integer roll) {
        this.roll = roll;
    }
}
