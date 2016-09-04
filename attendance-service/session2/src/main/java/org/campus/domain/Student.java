package org.campus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amey on 26/7/16.
 */
@Data
@Entity
public class Student extends BaseEntity {
    @Column(name = "ROLL", nullable = false,unique = true)
    private Integer roll;
    @Column(name = "CLASS", nullable = false)
    private String standard;
    @Column(name = "BRANCH", nullable = false)
    private String branch;
    @Column(name = "NAME", nullable = false)
    private String name;
    @ManyToMany(mappedBy="students")
    private List<Attendance> attendances;
    @OneToMany
    private  List<Complaint> complaints;

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

    public List<String> findAttendanceFor(String subject,String date) {
        int year,month;
        year=Integer.parseInt(date.substring(0,4));
        month=Integer.parseInt(date.substring(4,6))-1;
        Date dateStart=makeDate(year,month,1);
        Date dateLast=makeDate(year,month+1,0);
        return attendances.stream().filter(s -> s.getSubject().equals(subject) && dateStart.before(s.getDate()) && dateLast.after(s.getDate())).map(Attendance::getDateString).collect(Collectors.toList());
    }

    public void addAttendance(Attendance attendance) {
        if (attendances == null) {
            attendances = new ArrayList<Attendance>();
        }
        attendances.add(attendance);
    }

    public void addComplaint(Complaint complaint){
        if(complaint == null) {
                complaints=new ArrayList<Complaint>();
        }
        complaints.add(complaint);
    }

    public static Date makeDate(int y,int m,int x){
        Calendar cal= Calendar.getInstance();
        if(x==1)
            cal.set(y, m, 1);
        else
            cal.set(y, m, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    @JsonIgnore
    public List<Attendance> getAttendances() {
        return attendances;
    }

    public List<Complaint> getComplaints() {return  complaints;}
}
