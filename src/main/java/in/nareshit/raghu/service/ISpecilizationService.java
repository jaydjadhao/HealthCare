package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.entity.Specilization;

public interface ISpecilizationService {
	public Long saveSpecilization(Specilization spec);
	public List<Specilization> getAllSpecilizations();
	public void removeSpecilization(Long id);
	public Specilization getOneSpecilization(Long id);
	public void updateSpecilization(Specilization spec);
	public boolean isSpecCodeExists(String specCode);
}
