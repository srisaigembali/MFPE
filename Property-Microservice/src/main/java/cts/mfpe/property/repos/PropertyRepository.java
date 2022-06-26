package cts.mfpe.property.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cts.mfpe.property.models.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

	List<Property> findByPropertyType(String propertyType);
	
	List<Property> findByLocality(String locality);
}
