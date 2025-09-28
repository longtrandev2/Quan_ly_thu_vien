package nazeem.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nazeem.web.model.Staffs;
import nazeem.web.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
    StaffRepository staffRepository;

	@Override
	public List<Staffs>  save(Staffs entity) {
		return (List<Staffs>) staffRepository.save(entity);
	}

	@Override
	public Optional<Staffs> findById(Long id) {
		return staffRepository.findById(id);
	}

	@Override
	public List<Staffs> findAll() {
		return (List<Staffs>) staffRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		staffRepository.deleteById(id);
	}
	
	
}
