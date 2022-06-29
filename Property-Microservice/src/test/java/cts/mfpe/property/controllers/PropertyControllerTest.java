package cts.mfpe.property.controllers;

import cts.mfpe.property.entities.Property;
import cts.mfpe.property.exceptions.PropertyNotFoundException;
import cts.mfpe.property.service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class PropertyControllerTest {

    @InjectMocks
    PropertyController propertyController;

    @Mock
    PropertyService propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProperty() {
        doNothing().when(propertyService).createProperty(any(Property.class));
        assertEquals(HttpStatus.CREATED, propertyController.createProperty(new Property()).getStatusCode());
    }

    @Test
    void getAllProperties() {
        when(propertyService.getAllProperties()).thenReturn(new ArrayList<Property>());
        assertEquals(HttpStatus.OK, propertyController.getAllProperties().getStatusCode());
    }

    @Test
    void getAllPropertiesByType_Exception() throws Exception {
        when(propertyService.getAllPropertiesByType(anyString())).thenReturn(new ArrayList<Property>());
        assertThrows(PropertyNotFoundException.class, () -> propertyController.getAllPropertiesByType("Villa"));
    }

    @Test
    void getAllPropertiesByType() throws Exception {
        List<Property> p = new ArrayList<Property>();
        p.add(new Property());
        when(propertyService.getAllPropertiesByType(anyString())).thenReturn(p);
        assertEquals(HttpStatus.OK, propertyController.getAllPropertiesByType("").getStatusCode());
    }

    @Test
    void getAllPropertiesByLocality_Exception() {
        when(propertyService.getAllPropertiesByLocality(anyString())).thenReturn(new ArrayList<Property>());
        assertThrows(PropertyNotFoundException.class, () -> propertyController.getAllPropertiesByLocality("VVIP Mall"));
    }

    @Test
    void getAllPropertiesByLocality() throws Exception {
        List<Property> p = new ArrayList<Property>();
        p.add(new Property());
        when(propertyService.getAllPropertiesByLocality(anyString())).thenReturn(p);
        assertEquals(HttpStatus.OK, propertyController.getAllPropertiesByLocality("").getStatusCode());
    }
}