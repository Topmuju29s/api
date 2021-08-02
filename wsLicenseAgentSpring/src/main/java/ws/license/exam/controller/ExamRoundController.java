package ws.license.exam.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ws.license.exam.entities.ExamRound;
import ws.license.exam.service.ExamRoundService;


@RestController
@RequestMapping("/licenseexam")
public class ExamRoundController {

	@Autowired
	private ExamRoundService exService;
		
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/examround/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}

	
	@RequestMapping(value = "/examround/add", method=RequestMethod.POST)
	public /*ExamRound*/ResponseEntity<ExamRound> addExamRound(@RequestBody ExamRound examRound) 
	{
		ExamRound ex = null;
		try
		{
			ex = exService.save(examRound);
			System.out.println(ex);
			return new ResponseEntity<ExamRound>(ex,HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/examround/update", method=RequestMethod.PUT)
	public /*ExamRound*/ResponseEntity<ExamRound> updateExamRound(@RequestBody ExamRound examRound) throws Exception 
	{
		try
		{
			Optional<ExamRound> old = exService.findById(examRound.getRoundId());
			
			if (old.isPresent()) 
			{
				examRound.setCreateTime(old.get().getCreateTime());
				examRound.setCreateUserCode(old.get().getCreateUserCode());
				ExamRound eLast = exService.save(examRound);
				return new ResponseEntity<ExamRound>(eLast,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No round_id found with id :"+ examRound.getRoundId());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ roundId:"+examRound.getRoundId()+"ที่ใช้ในการอัพเดต!");
				//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@RequestMapping(value = "/examround/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteExamRound(@PathVariable("id") String id) 
	{
		
		try
		{
			System.out.printf("deleteExamRound========",id);
			exService.delete(id);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล id:"+id+" ได้");
			//return new ResponseEntity<>("delete "+id+" fail",HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/examround/search", method=RequestMethod.POST)
	public /*List<ExamRound>*/ResponseEntity<List<ExamRound>> searchExamRound(@Param("type") String type) 
	{
		System.out.println("searchExamRound type = "+type);
		try
		{
			//List<ExamRound> data = exService.testSearchSQL(type);
			List<ExamRound> listEx = new ArrayList<ExamRound>();
			if(type.equals("A"))
			{
				List<ExamRound> list = exService.findAll();
				return new ResponseEntity<List<ExamRound>>(list,HttpStatus.OK);
			}
			else
			{
		
				//exService.findById(type).orElseThrow(() -> new NotFoundException("round_id "+type+" is Not Found!"));
				
				Optional<ExamRound> ex = exService.findById(type);
				if (ex.isPresent()) 
				{
					listEx.add(ex.get());
					for(int i=0;i<listEx.size();i++)
					{
						ExamRound en = (ExamRound)listEx.get(i);
						System.out.println("result searchExamRound : "+en.getRoundId()+"|"+en.getTimeStr()+"|"+en.getCreateUserCode()+"|"+en.getUpdateUserCode());
					}
					//return ResponseEntity.of(Optional.of(listEx));
					return new ResponseEntity<List<ExamRound>>(listEx,HttpStatus.OK);
			    } 
				else 
				{
					
			        System.out.printf("No round_id found with id :"+ type);
			       // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			        //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ roundId:"+type);
			    }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error searchExamRound : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/examround/searchGET", method=RequestMethod.GET)
	public /*List<ExamRound>*/ResponseEntity<List<ExamRound>> searchExamRoundGet() 
	{
		System.out.println("searchExamRoundGet");

		List<ExamRound> list = exService.findAll();
		return new ResponseEntity<List<ExamRound>>(list,HttpStatus.OK);
	}
	

}
