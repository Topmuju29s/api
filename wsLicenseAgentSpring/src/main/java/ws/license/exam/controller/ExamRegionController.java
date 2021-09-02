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

import ws.license.exam.entities.ExamRegion;
import ws.license.exam.service.ExamRegionService;

@RestController
//@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/licenseexam")
public class ExamRegionController 
{
	@Autowired
	private ExamRegionService exService;
	
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/examregion/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	@RequestMapping(value = "/examregion/add", method=RequestMethod.POST)
	public ResponseEntity<ExamRegion> addExamRegion(@RequestBody ExamRegion ExamRegion) 
	{
		ExamRegion ex = null;
		try
		{
			ex = exService.save(ExamRegion);
			System.out.println(ex);
			return new ResponseEntity<ExamRegion>(ex,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/examregion/update", method=RequestMethod.PUT)
	public ResponseEntity<ExamRegion> updateExamRegion(@RequestBody ExamRegion ExamRegion) throws Exception 
	{
		try
		{
			Optional<ExamRegion> old = exService.findById(ExamRegion.getProvinceCode());
			
			if (old.isPresent()) 
			{
				ExamRegion.setCreateTime(old.get().getCreateTime());
				ExamRegion.setCreateUserCode(old.get().getCreateUserCode());
				ExamRegion eLast = exService.save(ExamRegion);
				return new ResponseEntity<ExamRegion>(eLast,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No province_code found with id :"+ ExamRegion.getProvinceCode());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ province_code:"+ExamRegion.getProvinceCode()+"ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/examregion/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteExamRegion(@PathVariable("id") String id) 
	{
		
		try
		{
			System.out.printf("deleteExamRegion========",id);
			exService.delete(id);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล id:"+id+" ได้");
		}
	}
	@RequestMapping(value = "/examregion/search", method=RequestMethod.POST)
	public ResponseEntity<List<ExamRegion>> searchExamRegion(@Param("type") String type) 
	{
		System.out.println("searchExamRegion type = "+type);
		try
		{
			List<ExamRegion> listEx = new ArrayList<ExamRegion>();
			if(type.equals("A"))
			{
				List<ExamRegion> list = exService.findAll();
				return new ResponseEntity<List<ExamRegion>>(list,HttpStatus.OK);
			}
			else
			{
				Optional<ExamRegion> ex = exService.findById(type);
				if (ex.isPresent()) 
				{
					listEx.add(ex.get());
					for(int i=0;i<listEx.size();i++)
					{
						ExamRegion en = (ExamRegion)listEx.get(i);
					}
					return new ResponseEntity<List<ExamRegion>>(listEx,HttpStatus.OK);
			    } 
				else 
				{
					
			        System.out.printf("No provicne_code found with id :"+ type);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ provicne_code:"+type);
			    }
			}
		}
		catch(Exception e)
		{
			System.out.println("Error searchExamRegion : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/examregion/searchGET", method=RequestMethod.GET)
	public ResponseEntity<List<ExamRegion>> searchExamRegionGet() 
	{
		System.out.println("searchExamRegionGet");

		List<ExamRegion> list = exService.findAll();
		return new ResponseEntity<List<ExamRegion>>(list,HttpStatus.OK);
	}
}
