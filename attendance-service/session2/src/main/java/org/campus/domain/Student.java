package org.campus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by amey on 26/7/16.
 */
@Data
@Entity
public class Student extends BaseEntity {
    @Column(name = "UNIQUE_ID", nullable = false)
    private Integer identifier;
    @Column(name = "CLASS", nullable = false)
    private String standard;
    @Column(name = "BRANCH", nullable = false)
    private String branch;
    @Column(name = "NAME", nullable = false)
    private String name;
    @ManyToMany(mappedBy="students")
    private List<Attendance> attendances;

    public Student() {}

    public Student(String standard, String branch, Integer identifier) {
        this.standard = standard;
        this.branch = branch;
        this.identifier = identifier;
    }

    public Student(String name, String standard, String branch, Integer identifier) {
        this.name = name;
        this.standard = standard;
        this.branch = branch;
        this.identifier = identifier;
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

    public List<String> findAttendanceFor(String yearMonth, String subject) {
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4, 6)) -1;
        Date monthStartDate = createDateFor(year, month, false);
        Date monthEndDate = createDateFor(year, month, true);
        return attendances.stream()
                .filter(a -> a.getSubject().equals(subject) && monthStartDate.before(a.getDate()) && monthEndDate.after(a.getDate()))
                .map(Attendance::getDateAsString)
                .collect(Collectors.toList());
    }

    public Map<String, Object> isDefaulterFor(String yearMonth, String subject) {
        List<String> list = findAttendanceFor(yearMonth, subject);
        HashMap<String, Object> attendanceMap = new HashMap<>();
        attendanceMap.put("total", 30);
        attendanceMap.put("present-days", list.size());
        attendanceMap.put("absent-days", 30 - list.size());
        if (!list.isEmpty() && list.size() < 3) {
            attendanceMap.put("isDefaulter", true);

        }
        return attendanceMap;
    }

    private Date createDateFor(int year, int month, boolean last) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        if (last) {
            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        }
        return cal.getTime();
    }
}
