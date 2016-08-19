package org.campus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.campus.web.helper.CustomDateDeserializer;
import org.campus.web.helper.CustomDateSerializer;

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
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date date;

    @Column(name = "CLASS", nullable = false)
    private String standard;

    @Column(name = "BRANCH", nullable = false)
    private String branch;

    @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @ManyToMany
    @JoinTable(name="ATTENDANCE_STUDENT")
    private List<Student> students;

    public Attendance() {}

    public Attendance(String date, String standard, String branch, String subject) {
        this.date = toDate(date);
        this.standard = standard;
        this.branch = branch;
        this.subject = subject;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    @JsonIgnore
    public List<Student> getStudents() {
        return students;
    }

    @JsonIgnore
    public String getDateAsString() {
        return toString(date);
    }
}
