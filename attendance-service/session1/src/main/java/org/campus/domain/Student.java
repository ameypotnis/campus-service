package org.campus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amey on 26/7/16.
 */
@Data
@Entity
public class Student {
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="students")
    private List<Attendance> attendances;
}
