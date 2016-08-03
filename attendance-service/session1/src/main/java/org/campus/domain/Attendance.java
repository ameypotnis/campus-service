package org.campus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amey on 26/7/16.
 */
@Entity
@Data
public class Attendance extends BaseEntity {
    @Column(name = "ATTENDANCE_DATE", nullable = false)
    private Date date;

    @Column(name = "CLASS", nullable = false)
    private String standard;

    @Column(name = "BRANCH", nullable = false)
    private String branch;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ATTENDANCE_STUDENT",
            joinColumns = @JoinColumn(name = "ATTENDANCE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"))
    private List<Student> students;

    public Attendance() {}

    public Attendance(String date, String standard, String branch) {
        this.date = toDate(date);
        this.standard = standard;
        this.branch = branch;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
