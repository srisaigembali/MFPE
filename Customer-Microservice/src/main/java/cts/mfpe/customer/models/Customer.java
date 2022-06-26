package cts.mfpe.customer.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String emailid;
	private String contactNumber;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = @JoinColumn(name = "customer_id"), 
			   inverseJoinColumns = @JoinColumn(name = "requirement_id"))
	private Set<Requirement> requirements;
	
	public void addRequirement(Requirement requirement) {
		requirements.add(requirement);
	}
}
