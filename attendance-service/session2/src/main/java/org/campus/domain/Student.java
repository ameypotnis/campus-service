package org.campus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amey on 26/7/16.
 */
@Data
@Entity
public class Student extends BaseEntity {
    @Column(name = "ROLL", nullable = false)
    private Integer roll;
    @Column(name = "CLASS", nullable = false)
    private String standard;
    @Column(name = "BRANCH", nullable = false)
    private String branch;
    @Column(name = "NAME", nullable = false)
    private String name;
    @ManyToMany(mappedBy="students")
    private List<Attendance> attendances;

    public Student() {}

    public Student(String standard, String branch, Integer roll) {
        this.standard = standard;
        this.branch = branch;
        this.roll = roll;
    }

    public Student(String name, String standard, String branch, Integer roll) {
        this.name = name;
        this.standard = standard;
        this.branch = branch;
        this.roll = roll;
    }

    public void addAttendance(Attendance attendance) {
        if (attendances == null) {
            attendances = new ArrayList<Attendance>();
        }
        attendances.add(attendance);
    }

    @JsonIgnore
    public List<Attendance> getAttendances() {
        return attendances;
    }
}
