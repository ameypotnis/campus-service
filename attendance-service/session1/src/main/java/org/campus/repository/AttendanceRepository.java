package org.campus.repository;

import org.campus.domain.Attendance;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by amey on 26/7/16.
 */

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    public Attendance findByCode(String code);
}