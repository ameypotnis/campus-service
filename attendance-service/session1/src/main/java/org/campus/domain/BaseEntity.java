package org.campus.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by amey on 2/8/16.
 */
@MappedSuperclass
@Data
public class BaseEntity {
    @Column(name="ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public boolean isNew() {
        return this.id == null;
    }

}
