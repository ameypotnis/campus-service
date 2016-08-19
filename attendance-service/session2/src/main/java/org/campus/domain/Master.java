package org.campus.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by indrajeet on 17/8/16.
 */
@Entity
@Data
public class Master extends BaseEntity {

    @Column(name = "TYPE",nullable = false)
    private String type;

    @Column(name = "VALUE",nullable = false)
    private String value;

    public Master() {}

    public Master(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
