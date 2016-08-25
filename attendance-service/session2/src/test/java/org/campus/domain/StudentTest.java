package org.campus.domain;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by amey on 19/8/16.
 */
public class StudentTest {

    @Test
    public void getAttendancesForGivenSubject() {
        Student student = new Student("BE", "CS", 1);
        String distributedSystem = "DistributedSystem";
        student.setAttendances(new LinkedList<Attendance>(){{
            add(new Attendance("20160802", "BE", "CS", distributedSystem));
            add(new Attendance("20160804", "BE", "CS", distributedSystem));
            add(new Attendance("20160805", "BE", "CS", distributedSystem));
            add(new Attendance("20160802", "BE", "CS", "Processor"));
            add(new Attendance("20160803", "BE", "CS", "Processor"));
            add(new Attendance("20160904", "BE", "CS", distributedSystem));
            add(new Attendance("20160905", "BE", "CS", distributedSystem));
        }});
        List<String> actual = student.findAttendanceFor("201608", distributedSystem);
        assertThat(actual.size()).isEqualTo(3);
        assertThat(actual.get(0)).isEqualTo("20160802");
        assertThat(actual.get(1)).isEqualTo("20160804");
        assertThat(actual.get(2)).isEqualTo("20160805");
        actual = student.findAttendanceFor("201609", distributedSystem);
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0)).isEqualTo("20160904");
        assertThat(actual.get(1)).isEqualTo("20160905");
    }

    @Test
    public void shouldSetStudentAsDefaulter() {
        Student student = new Student("BE", "CS", 1);
        String distributedSystem = "DistributedSystem";
        student.setAttendances(new LinkedList<Attendance>(){{
            add(new Attendance("20160802", "BE", "CS", distributedSystem));
            add(new Attendance("20160804", "BE", "CS", distributedSystem));
            add(new Attendance("20160805", "BE", "CS", distributedSystem));
            add(new Attendance("20160802", "BE", "CS", "Processor"));
            add(new Attendance("20160803", "BE", "CS", "Processor"));
            add(new Attendance("20160904", "BE", "CS", distributedSystem));
            add(new Attendance("20160905", "BE", "CS", distributedSystem));
        }});
        Map<String, Object> actual = student.isDefaulterFor("201609", distributedSystem);
        assertThat(actual.get("total")).isEqualTo(30);
        assertThat(actual.get("present-days")).isEqualTo(2);
        assertThat(actual.get("absent-days")).isEqualTo(28);
        assertThat(actual.get("isDefaulter")).isEqualTo(true);
    }

}