package nazeem.web.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Departs {

	@Id
	@Column(length = 10)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy ="departs" )
	private Set<Staffs> staffs;

	public Departs() {
		
	}

	public Departs(Long id, String name, Set<Staffs> staffs) {
		
		this.id = id;
		this.name = name;
		this.staffs = staffs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Staffs> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<Staffs> staffs) {
		this.staffs = staffs;
	}
     
	
}
