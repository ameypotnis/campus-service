package org.campus.repository;

import org.campus.domain.Complaint;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by indrajeet on 4/9/16.
 */
public interface ComplaintRepository extends CrudRepository<Complaint,Long> {

}
