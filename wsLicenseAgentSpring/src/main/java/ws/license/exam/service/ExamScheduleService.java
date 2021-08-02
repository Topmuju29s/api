package ws.license.exam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamSchedule;
import ws.license.exam.repository.ExamScheduleRepository;

@Service
public class ExamScheduleService {
	
	@Autowired
	private ExamScheduleRepository examScheduleRepository;
	public List<ExamSchedule> findAll()
	{
		return examScheduleRepository.findAll();
	}

	public Optional<ExamSchedule> findById(int id) 
	{
		return examScheduleRepository.findById(id);
	}

	public ExamSchedule save(ExamSchedule ExamSchedule)
	{
		return examScheduleRepository.save(ExamSchedule);
	}
	
	
	public String delete(int id) 
	{
		examScheduleRepository.deleteById(id);
		return "schedulr_id with ID :"+id+" is deleted"; 
	}
}
