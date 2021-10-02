package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Specilization;

public interface SpecilizationRepository extends JpaRepository<Specilization, Long> {
	
	@Query("SELECT count(*) FROM Specilization WHERE specCode=:specCode")
	public Integer getSpecCodeCount(String specCode);

}
