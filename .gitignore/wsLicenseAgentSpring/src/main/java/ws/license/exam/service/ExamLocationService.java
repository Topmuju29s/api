package ws.license.exam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamLocation;
import ws.license.exam.entities.ExamOrganizer;
import ws.license.exam.repository.ExamLocationRepository;
import ws.license.exam.repository.ExamOrganizerRepository;

@Service
public class ExamLocationService 
{
	@Autowired
	private ExamLocationRepository examLocationRepository;
	

	public List<ExamLocation> findAll()
	{
		return examLocationRepository.findAll();
	}

	public Optional<ExamLocation> findById(int id) 
	{
		return examLocationRepository.findById(id);
	}

	public ExamLocation save(ExamLocation examLocation)
	{
		return examLocationRepository.save(examLocation);
	}
	public String delete(int id) 
	{
		examLocationRepository.deleteById(id);
		return "location_id with ID :"+id+" is deleted"; 
	}
}
