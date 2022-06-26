package cts.mfpe.customer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cts.mfpe.customer.models.Requirement;
import cts.mfpe.customer.repos.RequirementRepository;

@Service
public class RequirementService {

	@Autowired
	private RequirementRepository requirementRepo;
	
	public List<Requirement> getRequirements(){
		return requirementRepo.findAll();
	}
}
