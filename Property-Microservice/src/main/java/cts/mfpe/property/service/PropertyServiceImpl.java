package cts.mfpe.property.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.mfpe.property.entities.Property;
import cts.mfpe.property.repos.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	private PropertyRepository propertyRepo;
	
	@Override
	public void createProperty(Property property) {
		propertyRepo.save(property);
	}
	
	@Override
	public List<Property> getAllProperties(){
		return propertyRepo.findAll();
	}
	
	@Override
	public List<Property> getAllPropertiesByType(String propertyType){
		return propertyRepo.findByPropertyType(propertyType);
	}
	
	@Override
	public List<Property> getAllPropertiesByLocality(String locality){
		return propertyRepo.findByLocality(locality);
	}
	
}
