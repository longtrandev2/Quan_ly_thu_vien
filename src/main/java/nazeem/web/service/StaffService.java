package nazeem.web.service;

import java.util.List;
import java.util.Optional;


import nazeem.web.model.Staffs;

public interface StaffService {

	void deleteById(Long id);

	Iterable<Staffs> findAll();

	Optional<Staffs> findById(Long id);

	List<Staffs> save(Staffs entity);

	

	

}
