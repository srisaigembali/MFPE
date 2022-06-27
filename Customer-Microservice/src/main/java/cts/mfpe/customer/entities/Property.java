package cts.mfpe.customer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {

	private int id;
	private String propertyType;
	private String locality;
	private double budget;
}
