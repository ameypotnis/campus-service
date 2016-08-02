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
    private String roll;
    @Column(name = "NAME", nullable = false)
    private String name;

    public Student() {}

    public Student( String _class, String branch, Integer roll) {
        this.roll = String.format("%s-%s-%s", _class, branch, roll);
    }
}
