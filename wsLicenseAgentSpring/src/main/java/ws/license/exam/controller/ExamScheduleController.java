package ws.license.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import ws.license.exam.service.ExamScheduleService;

@RestController
//@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/licenseexam")
public class ExamScheduleController {
	@Autowired
	private ExamScheduleService exService;
	
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
			Optional<ExamSchedule> old = exService.findById(ExamSchedule.getScheduleId());
			
			if (old.isPresent()) 
			{
				ExamSchedule.setCreateTime(old.get().getCreateTime());
				ExamSchedule.setCreateUserCode(old.get().getCreateUserCode());
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
	public ResponseEntity<List<ExamSchedule>> searchExamSchedule(@Param("type") String type) 
	{
		System.out.println("searchExamSchedule type = "+type);
		try
		{
			List<ExamSchedule> listEx = new ArrayList<ExamSchedule>();
			if(type.equals("A"))
			{
				List<ExamSchedule> list = exService.findAll();
				return new ResponseEntity<List<ExamSchedule>>(list,HttpStatus.OK);
			}
			else
			{
				Optional<ExamSchedule> ex = exService.findById(Integer.parseInt(type));
				if (ex.isPresent()) 
				{
					listEx.add(ex.get());
					for(int i=0;i<listEx.size();i++)
					{
						ExamSchedule en = (ExamSchedule)listEx.get(i);
						System.out.println("result searchExamSchedule : "+en);
					}
					return new ResponseEntity<List<ExamSchedule>>(listEx,HttpStatus.OK);
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
	public ResponseEntity<List<ExamSchedule>> searchExamScheduleGet() 
	{
		System.out.println("searchExamScheduleGet");

		List<ExamSchedule> list = exService.findAll();
		return new ResponseEntity<List<ExamSchedule>>(list,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/examschedule/searchDetail", method = RequestMethod.GET)
    public ResponseEntity<List<ExamSchedule>> searchExamScheduleByDetail(@Param("examDate") String examDate, 
    																	 @Param("roundId") String roundId,
    																	 @Param("provinceCode") String provinceCode,
    																	 @Param("examOrg") String examOrg) {
        System.out.println("examDate = " + examDate);
        System.out.println("roundId = " + roundId);
        System.out.println("provinceCode = " + provinceCode);
        System.out.println("examOrg = " + examOrg);
        try {
            List<ExamSchedule> ex = new ArrayList<ExamSchedule>();                        
            ex = exService.findByDetail(examDate,roundId,provinceCode,examOrg);
            
            return new ResponseEntity<List<ExamSchedule>>(ex,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error searchExamSchedule : " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
