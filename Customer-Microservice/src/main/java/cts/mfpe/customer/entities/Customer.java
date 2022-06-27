package cts.mfpe.customer.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Customer name cannot be null")
	private String name;
	@NotEmpty
	private String address;
	@Email(message="Invalid Email Entered")
	private String emailid;
	@NotNull(message="Invalid Contact Number Entered")
	@Min(value=10,message="Contact Number should be minimum of 10 digits")
	@Max(value=10,message="Contact Number should be maximum of 10 digits")
	private String contactNumber;
	@OneToMany
	private Set<Requirement> requirements;
}
