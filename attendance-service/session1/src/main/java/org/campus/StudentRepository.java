package org.campus;

import org.campus.domain.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by amey on 2/8/16.
 */
public interface StudentRepository extends CrudRepository<Student, Long> {
}
