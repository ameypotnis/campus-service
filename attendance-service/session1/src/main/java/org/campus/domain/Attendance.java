package org.campus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by amey on 26/7/16.
 */
@Entity
@Data
public class Attendance extends BaseEntity {
    @Column(name = "CODE", nullable = false)
    private String code;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ATTENDANCE_STUDENT",
            joinColumns = @JoinColumn(name = "ATTENDANCE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"))
    private List<Student> students;

    public Attendance() {}

    public Attendance(String date, String _class, String branch) {
        this.code = String.format("%s-%s-%s", date, _class, branch);
    }
}
