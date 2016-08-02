package org.campus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amey on 26/7/16.
 */
@Entity
@Data
public class Attendance {
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name = "DATE", nullable = false)
    private String date;
    @Column(name = "BATCH", nullable = false)
    private String batch;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="ATTENDANCE_STUDENT",
            joinColumns=@JoinColumn(name="ATTENDANCE_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="STUDENT_ID", referencedColumnName="ID"))
    private List<Student> students;

    public Attendance() {
    }

    public Attendance(String date, String batch) {
        this.date = date;
        this.batch = batch;
    }
}
