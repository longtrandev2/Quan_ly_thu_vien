package nazeem.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nazeem.web.model.Departs;

@Repository
public interface DepartRepository extends CrudRepository<Departs,Long> {

}
