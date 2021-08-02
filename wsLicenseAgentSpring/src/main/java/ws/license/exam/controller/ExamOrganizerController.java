package ws.license.exam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ws.license.exam.entities.ExamOrganizer;
import ws.license.exam.service.ExamOrganizerService;

@RestController
@RequestMapping("/licenseexam")
public class ExamOrganizerController 
{
	@Autowired
	private ExamOrganizerService exService;
	
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/examorganizer/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	@RequestMapping(value = "/examorganizer/add", method=RequestMethod.POST)
	public ResponseEntity<ExamOrganizer> addExamOrganizer(@RequestBody ExamOrganizer examOrganizer) 
	{
		ExamOrganizer ex = null;
		try
		{
			ex = exService.save(examOrganizer);
			System.out.println(ex);
			return new ResponseEntity<ExamOrganizer>(ex,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/examorganizer/update", method=RequestMethod.PUT)
	public ResponseEntity<ExamOrganizer> updateExamOrganizer(@RequestBody ExamOrganizer ExamOrganizer) throws Exception 
	{
		try
		{
			Optional<ExamOrganizer> old = exService.findById(ExamOrganizer.getOrgCode());
			
			if (old.isPresent()) 
			{
				ExamOrganizer.setCreateTime(old.get().getCreateTime());
				ExamOrganizer.setCreateUserCode(old.get().getCreateUserCode());
				ExamOrganizer eLast = exService.save(ExamOrganizer);
				return new ResponseEntity<ExamOrganizer>(eLast,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No round_id found with id :"+ ExamOrganizer.getOrgCode());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ roundId:"+ExamOrganizer.getOrgCode()+"ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/examorganizer/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteExamOrganizer(@PathVariable("id") String id) 
	{
		
		try
		{
			System.out.printf("deleteExamOrganizer========",id);
			exService.delete(id);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล id:"+id+" ได้");
		}
	}
	@RequestMapping(value = "/examorganizer/search", method=RequestMethod.POST)
	public ResponseEntity<List<ExamOrganizer>> searchExamOrganizer(@Param("type") String type) 
	{
		System.out.println("searchExamOrganizer type = "+type);
		try
		{
			List<ExamOrganizer> listEx = new ArrayList<ExamOrganizer>();
			if(type.equals("A"))
			{
				List<ExamOrganizer> list = exService.findAll();
				return new ResponseEntity<List<ExamOrganizer>>(list,HttpStatus.OK);
			}
			else
			{
				Optional<ExamOrganizer> ex = exService.findById(type);
				if (ex.isPresent()) 
				{
					listEx.add(ex.get());
					for(int i=0;i<listEx.size();i++)
					{
						ExamOrganizer en = (ExamOrganizer)listEx.get(i);
						System.out.println("result searchExamOrganizer : "+en.getOrgCode()+"|"+en.getOrgName()+"|"+en.getCreateUserCode()+"|"+en.getUpdateUserCode());
					}
					return new ResponseEntity<List<ExamOrganizer>>(listEx,HttpStatus.OK);
			    } 
				else 
				{
					
			        System.out.printf("No org_code found with id :"+ type);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ orgCode:"+type);
			    }
			}
		}
		catch(Exception e)
		{
			System.out.println("Error searchExamOrganizer : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/examorganizer/searchGET", method=RequestMethod.GET)
	public ResponseEntity<List<ExamOrganizer>> searchExamOrganizerGet() 
	{
		System.out.println("searchExamOrganizerGet");

		List<ExamOrganizer> list = exService.findAll();
		return new ResponseEntity<List<ExamOrganizer>>(list,HttpStatus.OK);
	}
	
}
