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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.license.exam.entities.ExamSchedule;
import ws.license.exam.entities.ExamScheduleDetail;
import ws.license.exam.entities.ExamScheduleLog;
import ws.license.exam.repository.ExamScheduleDetailRepository;
import ws.license.exam.repository.ExamScheduleLogRepository;
import ws.license.exam.repository.ExamScheduleRepository;

@Service
public class ExamScheduleService {
	
	@Autowired
	private ExamScheduleRepository examScheduleRepository;
	@Autowired
	private ExamScheduleLogRepository examScheduleLogRepository;
	@Autowired
	private ExamScheduleDetailRepository examScheduleDetailRepository;
	
	public List<ExamScheduleDetail> findAll()
	{
		return examScheduleDetailRepository.findAll();
	}

	public List<ExamScheduleDetail> findById(int id) 
	{
		return examScheduleDetailRepository.findById(id);
	}

	public ExamSchedule save(ExamSchedule examSchedule)
	{
		
		ExamScheduleLog examScheduleLog = 
				new ExamScheduleLog(examSchedule.getScheduleId(), examSchedule.getLocationId(),
						examSchedule.getAlteredLocationId(), examSchedule.getExamDate(),
						examSchedule.getRoundId(), examSchedule.getMaxApplicant(),
						examSchedule.getApplyOpenDate(), examSchedule.getApplyCloseDate(),
						examSchedule.getOpenStatus(), "",
						examSchedule.getReceiveDate(), examSchedule.getReceiveTime(),
						examSchedule.getCreateUserCode(), examSchedule.getCreateTime(), 
						examSchedule.getUpdateUserCode(), examSchedule.getLastUpdate()); 

		examScheduleLogRepository.save(examScheduleLog);
		return examScheduleRepository.save(examSchedule);
	}
	
	
	public String delete(int id) 
	{
		examScheduleRepository.deleteById(id);
		return "schedulr_id with ID :"+id+" is deleted"; 
	}
	
	public List<ExamScheduleDetail> findByDetail(String examDate, String roundId, String provinceCode, String examOrg) 
	{

                    List<ExamScheduleDetail> ex = new ArrayList<ExamScheduleDetail>();
                    List<ExamScheduleDetail> exFilter = new ArrayList<ExamScheduleDetail>();
                    
                    if (!"".equals(convertNullToEmptyString(provinceCode)) & !"".equals(convertNullToEmptyString(examOrg))) {
                    	ex = examScheduleDetailRepository.findByProvinceCodeAndExamOrg(provinceCode, examOrg);
                    } else if (!"".equals(convertNullToEmptyString(provinceCode)) & "".equals(convertNullToEmptyString(examOrg))) {
                    	ex = examScheduleDetailRepository.findByProvinceCode(provinceCode);
                    } else if ("".equals(convertNullToEmptyString(provinceCode)) & !"".equals(convertNullToEmptyString(examOrg))) {
                    	ex = examScheduleDetailRepository.findByExamOrg(examOrg);
                    } else {
                    	ex = examScheduleDetailRepository.findAll();
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
	private String convertNullToEmptyString(Object value) {
        String stringValue = String.valueOf(value);
        stringValue = (("null".equals(stringValue)) ? "" : stringValue);
        return stringValue;
    }
    
    private Date convertStringToDate(String dateString) {
    	
    	Date date = null;
    	try {    		
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
	    	date = format.parse(dateString);    		
    	} catch (ParseException e) {
    		date = new Date();
    	}
    	return date;
    }
    
    private Date convertDateToDate(Date dateUnformat) {
    	Date dateFormat = null;
    	String dateString = null;
    	String pattern = "yyyy-MM-dd";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern,Locale.ENGLISH);	
		dateString = simpleDateFormat.format(dateUnformat);    
    	dateFormat = convertStringToDate(dateString);
    	System.out.println("convertDateToDate dateString " + dateString);
    	System.out.println("convertDateToDate dateFormat " + dateFormat);
    	return dateFormat;
    }
}
