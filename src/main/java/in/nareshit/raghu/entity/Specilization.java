package in.nareshit.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="specilization_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specilization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spec_id")
	private Long id;
	
	@Column(name = "spec_code", length = 10, nullable = false,unique = true)
	private String specCode;
	
	@Column(name = "spec_name", length = 60, nullable = false,unique = true)
	private String specName;
	
	@Column(name = "spec_note", length = 250, nullable = false)
	private String specNote;
}
