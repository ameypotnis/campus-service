package org.campus.domain;

import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by amey on 2/8/16.
 */
@MappedSuperclass
@Data
public class BaseEntity {
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public boolean isNew() {
        return this.id == null;
    }

    public static Date toDate(String date) {
        if (date == null) return null;
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toString(Date date) {
        if (date == null) return null;
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }

    public static String generateIdentifier() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0,6);
    }
}
