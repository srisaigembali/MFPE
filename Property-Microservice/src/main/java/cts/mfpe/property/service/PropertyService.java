package cts.mfpe.property.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.mfpe.property.entities.Property;
import cts.mfpe.property.repos.PropertyRepository;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepo;
	
	public void createProperty(Property property) {
		propertyRepo.save(property);
	}
	
	public List<Property> getAllProperties(){
		return propertyRepo.findAll();
	}
	
	public List<Property> getAllPropertiesByType(String propertyType){
		return propertyRepo.findByPropertyType(propertyType);
	}
	
	public List<Property> getAllPropertiesByLocality(String locality){
		return propertyRepo.findByLocality(locality);
	}
	
}
