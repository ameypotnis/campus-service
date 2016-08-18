package org.campus.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by indrajeet on 17/8/16.
 */
@Entity
@Data
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @Column(name = "TYPE",nullable = false)
    private String type;

    @Column(name = "VALUE",nullable = false)
    private String value;

    public Master() {}
}
