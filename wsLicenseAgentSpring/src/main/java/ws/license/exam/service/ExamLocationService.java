package ws.license.exam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamLocation;
import ws.license.exam.entities.ExamLocationDetail;
import ws.license.exam.entities.ExamOrganizer;
import ws.license.exam.repository.ExamLocationDetailRepository;
import ws.license.exam.repository.ExamLocationRepository;
import ws.license.exam.repository.ExamOrganizerRepository;

@Service
public class ExamLocationService 
{
	@Autowired
	private ExamLocationRepository examLocationRepository;
	
	@Autowired
	private ExamLocationDetailRepository examLocationDetailRepository;
	

	public List<ExamLocationDetail> findAll()
	{
		return examLocationDetailRepository.findExamLocationAll();
	}

	public List<ExamLocationDetail> findById(int id) 
	{
		return examLocationDetailRepository.findExamLocationById(id);		
	}

	public ExamLocation save(ExamLocation examLocation) throws Exception
	{
		
		if(examLocation.getLocationDetail().length() > 300){
			throw new Exception("ความยาวสถานที่ตั้งสอบเกิน 300 ตัวอักษร");
		}
		
		return examLocationRepository.save(examLocation);
	}
	
	public String updateExamLocation(ExamLocation examLocation)
	{
		examLocationRepository.updateExamLocation(examLocation.getLocationId());
		return "success";
	}
	
	public String delete(int id) 
	{
		examLocationRepository.deleteById(id);
		return "location_id with ID :"+id+" is deleted"; 
	}
}
