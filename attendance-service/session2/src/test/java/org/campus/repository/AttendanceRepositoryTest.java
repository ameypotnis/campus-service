package org.campus.repository;

import org.campus.domain.Attendance;
import org.campus.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.campus.domain.BaseEntity.toDate;

/**
 * Created by amey on 2/8/16.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AttendanceRepositoryTest {

    @Autowired
    private AttendanceRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveAndSearchAttendance() {
        //given
        Student indrajeet = new Student("Indrajeet", "BE", "CS", 1);
        studentRepository.save(indrajeet);
        Student avdhut = new Student("Avdhut", "BE", "CS", 2);
        studentRepository.save(avdhut);

        //when
        Attendance attendance = new Attendance("20160726", "BE", "CS");
        attendance.addStudent(indrajeet);
        attendance.addStudent(avdhut);
        indrajeet.addAttendance(attendance);
        avdhut.addAttendance(attendance);
        repository.save(attendance);

        attendance = new Attendance("20160727", "BE", "CS");
        attendance.addStudent(indrajeet);
        indrajeet.addAttendance(attendance);
        repository.save(attendance);

        //then
        Attendance actual = repository.findByDateAndStandardAndBranch(toDate("20160726"), "BE", "CS");
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getDate()).isEqualTo(toDate("20160726"));
        assertThat(actual.getStandard()).isEqualTo("BE");
        assertThat(actual.getBranch()).isEqualTo("CS");

        assertThat(actual.getStudents().size()).isEqualTo(2);
        assertThat(actual.getStudents().get(0).getName()).isEqualTo("Indrajeet");
        assertThat(actual.getStudents().get(1).getName()).isEqualTo("Avdhut");

        indrajeet = studentRepository.findByRollAndStandardAndBranch(1, "BE", "CS");
        assertThat(indrajeet.getName()).isEqualTo("Indrajeet");
        assertThat(indrajeet.getAttendances().size()).isEqualTo(2);
        avdhut = studentRepository.findByRollAndStandardAndBranch(2, "BE", "CS");
        assertThat(avdhut.getName()).isEqualTo("Avdhut");
        assertThat(avdhut.getAttendances().size()).isEqualTo(1);
    }

}