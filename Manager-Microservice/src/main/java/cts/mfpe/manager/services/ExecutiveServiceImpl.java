package cts.mfpe.manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cts.mfpe.manager.entities.Executive;
import cts.mfpe.manager.repos.ExecutiveRepository;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

	@Autowired
	private ExecutiveRepository executiveRepo;
	
	@Value("${allcustomers.url}")
	private String allCustomersURL;
	
	@Value("${customerbyid.url}")
	private String customerByIdURL;
	
	@Override
	public void createExecutive(Executive executive) {
		executiveRepo.save(executive);
	}

	@Override
	public List<Executive> getAllExecutives() {
		return executiveRepo.findAll();
	}

	@Override
	public List<Executive> getAllExecutivesByLocality(String locality) {
		return executiveRepo.findByLocality(locality);
	}


}
