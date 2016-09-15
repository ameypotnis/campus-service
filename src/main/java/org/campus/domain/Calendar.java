package org.campus.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by indrajeet on 17/8/16.
 */
@Entity
@Data
public class Calendar extends BaseEntity {

    @Column(name = "YEARMONTH",nullable = false)
    private String yearMonth;

    @Column(name = "SUBJECT",nullable = false)
    private String subject;

    @Column(name = "WORKING_DAYS",nullable = false)
    private String workingDays;
}
