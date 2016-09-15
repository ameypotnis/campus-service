package org.campus.repository;

import org.campus.domain.Calendar;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by amey on 26/7/16.
 */

public interface CalendarRepository extends CrudRepository<Calendar, Long> {
    Calendar findByYearMonthAndAndSubject(String yearMonth, String subject);
}