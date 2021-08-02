package ws.license.exam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamRegion;
import ws.license.exam.repository.ExamRegionRepository;

@Service
public class ExamRegionService 
{
	@Autowired
	private ExamRegionRepository examRegionRepository;
	

	public List<ExamRegion> findAll()
	{
		return examRegionRepository.findAll();
	}

	public Optional<ExamRegion> findById(String id) 
	{
		return examRegionRepository.findById(id);
	}

	public ExamRegion save(ExamRegion ExamRegion)
	{
		return examRegionRepository.save(ExamRegion);
	}
	
	
	public String delete(String id) 
	{
		examRegionRepository.deleteById(id);
		return "province_code with ID :"+id+" is deleted"; 
	}
}
