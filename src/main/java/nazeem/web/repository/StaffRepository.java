package nazeem.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nazeem.web.model.Staffs;

@Repository
public interface StaffRepository extends CrudRepository<Staffs,Long>{

}
