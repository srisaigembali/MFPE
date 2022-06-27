package cts.mfpe.property.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cts.mfpe.property.entities.Property;
import cts.mfpe.property.service.PropertyService;

@RestController
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	@PostMapping("/createProperty")
	public ResponseEntity<String> createProperty(@RequestBody Property property) {
		propertyService.createProperty(property);
		return new ResponseEntity<>("Property Created Successfully!",HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllProperties")
	public ResponseEntity<List<Property>> getAllProperties(){
		return ResponseEntity.ok(propertyService.getAllProperties());
	}
	
	@GetMapping("/getAllPropertiesByType/{propertyType}")
	public ResponseEntity<List<Property>> getAllPropertiesByType(@PathVariable String propertyType){
		return ResponseEntity.ok(propertyService.getAllPropertiesByType(propertyType));
	}
	
	@GetMapping("/getAllPropertiesByLocality/{locality}")
	public ResponseEntity<List<Property>> getAllPropertiesByLocality(@PathVariable String locality){
		return ResponseEntity.ok(propertyService.getAllPropertiesByLocality(locality));
	}
}
