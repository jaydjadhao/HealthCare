package in.nareshit.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.Specilization;
import in.nareshit.raghu.exception.SpecilizationNotFoundException;
import in.nareshit.raghu.repo.SpecilizationRepository;
import in.nareshit.raghu.service.ISpecilizationService;

@Service
public class SpecilizationServiceImpl implements ISpecilizationService {

	@Autowired
	private SpecilizationRepository repo;

	@Override
	public Long saveSpecilization(Specilization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specilization> getAllSpecilizations() {
		return repo.findAll();
	}

	@Override
	public void removeSpecilization(Long id) {
		repo.delete(getOneSpecilization(id));
	}

	@Override
	public Specilization getOneSpecilization(Long id) {
		return repo.findById(id).orElseThrow(()-> new SpecilizationNotFoundException(id + " not Found !!"));
	}

	@Override
	public void updateSpecilization(Specilization spec) {
		repo.save(spec);
	}

	@Override
	public boolean isSpecCodeExists(String specCode) {
		return repo.getSpecCodeCount(specCode)>0;
	}

}
