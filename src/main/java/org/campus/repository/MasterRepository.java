package org.campus.repository;

import org.campus.domain.Master;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indrajeet on 17/8/16.e
 */
public interface MasterRepository extends CrudRepository<Master, Long> {
    List<Master> findByType(String type);
    Master findByTypeAndValue(String type, String value);

}