package ws.license.exam.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamSchedule;
import ws.license.exam.repository.ExamScheduleRepository;

@Service
public class ExamScheduleService {
	
	@Autowired
	private ExamScheduleRepository examScheduleRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
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
        
    public List<ExamSchedule> findByDetail(String examDate, String roundId, String provinceCode, String examOrg) 
	{

                    List<ExamSchedule> ex = new ArrayList<ExamSchedule>();
                    List<ExamSchedule> exFilter = new ArrayList<ExamSchedule>();
                    
                    if (!"".equals(convertNullToEmptyString(provinceCode)) & !"".equals(convertNullToEmptyString(examOrg))) {
                    	ex = examScheduleRepository.findByProvinceCodeAndExamOrg(provinceCode, examOrg);
                    } else if (!"".equals(convertNullToEmptyString(provinceCode)) & "".equals(convertNullToEmptyString(examOrg))) {
                    	ex = examScheduleRepository.findByProvinceCode(provinceCode);
                    } else if ("".equals(convertNullToEmptyString(provinceCode)) & !"".equals(convertNullToEmptyString(examOrg))) {
                    	ex = examScheduleRepository.findByExamOrg(examOrg);
                    } else {
                    	ex = examScheduleRepository.findAll();
                    }
                    
                    if (!"".equals(convertNullToEmptyString(examDate))) {
                    	Date examTypeDate = convertStringToDate(examDate);   
                    	exFilter = ex.stream().filter(p -> convertDateToDate(p.getExamDate()).equals(examTypeDate)).collect(Collectors.toList());
                    	
                    	System.out.println("exFilter = " + exFilter.toString());
                    }
                    if (!"".equals(convertNullToEmptyString(roundId))) {
                    	exFilter = ex.stream().filter(p -> p.getRoundId().equals(roundId)).collect(Collectors.toList());
                    	System.out.println("exFilter = " + exFilter.toString());	
                    }
                    if ("".equals(convertNullToEmptyString(examDate)) && "".equals(convertNullToEmptyString(roundId))) {
                    	exFilter = ex;
                    }

                    return exFilter;
	}
	
	
	public String delete(int id) 
	{
		examScheduleRepository.deleteById(id);
		return "schedulr_id with ID :"+id+" is deleted"; 
	}
        
    private String convertNullToEmptyString(Object value) {
        String stringValue = String.valueOf(value);
        stringValue = (("null".equals(stringValue)) ? "" : stringValue);
        return stringValue;
    }
    
    private Date convertStringToDate(String dateString) {
    	
    	Date date = null;
    	try {    		
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
	    	date = format.parse(dateString);    		
    	} catch (ParseException e) {
    		date = new Date();
    	}
    	return date;
    }
    
    private Date convertDateToDate(Date dateUnformat) {
    	Date dateFormat = null;
    	String dateString = null;
    	String pattern = "dd/MM/YYYY";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern,Locale.ENGLISH);	
		dateString = simpleDateFormat.format(dateUnformat);    
    	dateFormat = convertStringToDate(dateString);
    	
    	return dateFormat;
    }
    
}
