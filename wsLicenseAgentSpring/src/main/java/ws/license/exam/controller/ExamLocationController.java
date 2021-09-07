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

import ws.license.exam.entities.ExamLocation;
import ws.license.exam.entities.ExamLocationDetail;
import ws.license.exam.service.ExamLocationService;

@RestController
//@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/licenseexam")
public class ExamLocationController 
{
	@Autowired
	private ExamLocationService exService;
	
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/examlocation/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	@RequestMapping(value = "/examlocation/add", method=RequestMethod.POST)
	public ResponseEntity<String> addExamLocation(@RequestBody ExamLocation ExamLocation) 
	{
		System.out.println("addExamLocation = "+ExamLocation);
		System.out.println("ExamLocation = "+ExamLocation.getLocationId()+"|"+ExamLocation.getLocationDetail());
		ExamLocation ex = null;
		try
		{
			ex = exService.save(ExamLocation);
			System.out.println(ex);
			return new ResponseEntity<>("success",HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/examlocation/update", method=RequestMethod.PUT)
	public ResponseEntity<ExamLocation> updateExamLocation(@RequestBody ExamLocation ExamLocation) throws Exception 
	{
		try
		{
			List<ExamLocationDetail> old = exService.findById(ExamLocation.getLocationId());

			if (old.size() > 0)
			{
				ExamLocation.setCreateTime(old.get(0).getCreateTime());
				ExamLocation.setCreateUserCode(old.get(0).getCreateUserCode());
				ExamLocation ex = exService.save(ExamLocation);
				return new ResponseEntity<ExamLocation>(ex,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No location_id found with id :"+ ExamLocation.getLocationId());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ location_id:"+ExamLocation.getLocationId()+"ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/examlocation/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteExamLocation(@PathVariable("id") int id) 
	{
		
		try
		{
			System.out.printf("deleteExamLocation========",id);
			exService.delete(id);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล id:"+id+" ได้");
		}
	}
	@RequestMapping(value = "/examlocation/search", method=RequestMethod.POST)
	public ResponseEntity<List<ExamLocationDetail>> searchExamLocation(@Param("type") String type) 
	{
		System.out.println("searchExamLocation type = "+type);
		try
		{
			List<ExamLocation> listEx = new ArrayList<ExamLocation>();
			if(type.equals("A"))
			{
				List<ExamLocationDetail> list = exService.findAll();
				return new ResponseEntity<List<ExamLocationDetail>>(list,HttpStatus.OK);
			}
			else
			{
				List<ExamLocationDetail> ex = exService.findById(Integer.parseInt(type));
				if (ex.size() > 0) 
				{
					for(int i=0;i<ex.size();i++)
					{
						ExamLocationDetail en = (ExamLocationDetail)ex.get(i);
						System.out.println("result searchExamLocation : "+en);
					}
					return new ResponseEntity<List<ExamLocationDetail>>(ex,HttpStatus.OK);
			    } 				
				else 
				{
					
			        System.out.printf("No location_id found with id :"+ type);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ location_id:"+type);
			    }
			}
		}
		catch(Exception e)
		{
			System.out.println("Error searchExamLocation : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/examlocation/searchGET", method=RequestMethod.GET)
	public ResponseEntity<List<ExamLocationDetail>> searchExamLocationGet() 
	{
		System.out.println("searchExamLocationGet");

		List<ExamLocationDetail> list = exService.findAll();
		return new ResponseEntity<List<ExamLocationDetail>>(list,HttpStatus.OK);
	}
}
