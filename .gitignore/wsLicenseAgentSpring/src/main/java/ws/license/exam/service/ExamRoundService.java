package ws.license.exam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamRound;
import ws.license.exam.repository.ExamRoundRepository;

@Service
public class ExamRoundService
{
	@Autowired
	private ExamRoundRepository examRoundRepository;
	

	public List<ExamRound> findAll()
	{
		return examRoundRepository.findAll();
	}

	public Optional<ExamRound> findById(String id) 
	{
		return examRoundRepository.findById(id);
	}

	public ExamRound save(ExamRound examRound)
	{
		return examRoundRepository.save(examRound);
	}
	
	
	public String delete(String id) 
	{
		examRoundRepository.deleteById(id);
		return "round_id with ID :"+id+" is deleted"; 
	}
	
	//@Override
	public List<ExamRound> testSearchSQL(String type)
	{
		System.out.println("testSearchSQL type = "+type);
		return examRoundRepository.searchDataAll();
	}


}
