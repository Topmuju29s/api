package ws.license.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ws.license.exam.entities.ExamSchedule;
import ws.license.exam.entities.ExamScheduleDetail;
import ws.license.exam.service.ExamScheduleService;

@RestController
//@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/licenseexam")
public class ExamScheduleController {
	@Autowired
	private ExamScheduleService exService;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/examschedule/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	@RequestMapping(value = "/examschedule/add", method=RequestMethod.POST)
	public ResponseEntity<ExamSchedule> addExamSchedule(@RequestBody ExamSchedule ExamSchedule) 
	{
		System.out.println("addExamSchedule = "+ExamSchedule);
		System.out.println("ExamSchedule = "+ExamSchedule.getScheduleId()+"|"+ExamSchedule.getLocationId());
		ExamSchedule ex = null;
		try
		{
			ex = exService.save(ExamSchedule);
			System.out.println(ex);
			return new ResponseEntity<ExamSchedule>(ex,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/examschedule/update", method=RequestMethod.PUT)
	public ResponseEntity<ExamSchedule> updateExamSchedule(@RequestBody ExamSchedule ExamSchedule) throws Exception 
	{
		try
		{
			List<ExamScheduleDetail> old = exService.findById(ExamSchedule.getScheduleId());
			
			if (old.size() > 0)
			{
				ExamSchedule.setCreateTime(old.get(0).getCreateTime());
				ExamSchedule.setCreateUserCode(old.get(0).getCreateUserCode());
				ExamSchedule eLast = exService.save(ExamSchedule);
				return new ResponseEntity<ExamSchedule>(eLast,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No scheduleId found with id :"+ ExamSchedule.getScheduleId());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ scheduleId:"+ExamSchedule.getScheduleId()+"ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/examschedule/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteExamSchedule(@PathVariable("id") int id) 
	{
		
		try
		{
			System.out.printf("deleteExamSchedule========",id);
			exService.delete(id);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล id:"+id+" ได้");
		}
	}
	@RequestMapping(value = "/examschedule/search", method=RequestMethod.POST)
	public ResponseEntity<List<ExamScheduleDetail>> searchExamSchedule(@Param("type") String type) 
	{
		System.out.println("searchExamSchedule type = "+type);
		try
		{

			if(type.equals("A"))
			{
				List<ExamScheduleDetail> list = exService.findAll();
				return new ResponseEntity<List<ExamScheduleDetail>>(list,HttpStatus.OK);
			}
			else
			{
				List<ExamScheduleDetail> ex = exService.findById(Integer.parseInt(type));
				if (ex.size() > 0) 
				{
					return new ResponseEntity<List<ExamScheduleDetail>>(ex,HttpStatus.OK);
			    } 
				else 
				{
					
			        System.out.printf("No schedule_id found with id :"+ type);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ schedule_id:"+type);
			    }
			}
		}
		catch(Exception e)
		{
			System.out.println("Error searchExamSchedule : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/examschedule/searchGET", method=RequestMethod.GET)
	public ResponseEntity<List<ExamScheduleDetail>> searchExamScheduleGet() 
	{
		System.out.println("searchExamScheduleGet");

		List<ExamScheduleDetail> list = exService.findAll();
		return new ResponseEntity<List<ExamScheduleDetail>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/examschedule/searchDetail", method = RequestMethod.GET)
    public ResponseEntity<List<ExamScheduleDetail>> searchExamScheduleByDetail(@Param("examDate") String examDate, 
    																	 @Param("roundId") String roundId,
    																	 @Param("provinceCode") String provinceCode,
    																	 @Param("examOrg") String examOrg) {
        System.out.println("examDate = " + examDate);
        System.out.println("roundId = " + roundId);
        System.out.println("provinceCode = " + provinceCode);
        System.out.println("examOrg = " + examOrg);
        try {

        	List<ExamScheduleDetail> ex = exService.findByDetail(examDate,roundId,provinceCode,examOrg);
            
            return new ResponseEntity<List<ExamScheduleDetail>>(ex,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error searchExamSchedule : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
