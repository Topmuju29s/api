package ws.license.exam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamOrganizer;
import ws.license.exam.repository.ExamOrganizerRepository;

@Service
public class ExamOrganizerService
{
	@Autowired
	private ExamOrganizerRepository examOrganizerRepository;
	

	public List<ExamOrganizer> findAll()
	{
		return examOrganizerRepository.findAll();
	}

	public Optional<ExamOrganizer> findById(String id) 
	{
		return examOrganizerRepository.findById(id);
	}

	public ExamOrganizer save(ExamOrganizer examRound)
	{
		return examOrganizerRepository.save(examRound);
	}
	public String delete(String id) 
	{
		examOrganizerRepository.deleteById(id);
		return "org_code with ID :"+id+" is deleted"; 
	}
}
	