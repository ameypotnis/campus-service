package org.campus.repository;

import org.campus.domain.Attendance;
import org.campus.domain.Complaint;
import org.campus.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by amey on 2/8/16.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
    Student findByRollAndStandardAndBranch(Integer roll, String standard, String branch);
    Student findByRoll(Integer roll);
}
