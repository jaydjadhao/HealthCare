package in.nareshit.raghu;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import in.nareshit.raghu.entity.Specilization;
import in.nareshit.raghu.repo.SpecilizationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class HealthCareApplicationTests {
	@Autowired
	private SpecilizationRepository repo;
	
	/***
	 * 1. Test Save operation
	 */
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specilization spec = new Specilization(null, "CRDLS", "Cardiologists", "They’re experts on the heart and blood vessels.");
		spec = repo.save(spec);
		assertNotNull(spec.getId(), " Spec is not created !!!");
	}
	
	/***
	 * 2. Test display all operation
	 */
	@Test
	@Order(2)
	public void testSpecFetchAll() {
		List<Specilization> list = repo.findAll();
		assertNotNull(list);
		if (list.isEmpty()) {
			fail("No data exists in Database");
		}
	}
}
