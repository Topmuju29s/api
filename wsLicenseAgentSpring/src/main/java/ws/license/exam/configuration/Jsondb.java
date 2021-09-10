package ws.license.exam.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ws.license.exam.domain.Region;
import ws.license.exam.domain.ExamType;

public class Jsondb {
	
	public List<Region> getRegionFromJson(){
	
		List<Region> region = null;
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Region>> typeReference = new TypeReference<List<Region>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/examRegion.json");
		
		try{
			
			region = mapper.readValue(inputStream, typeReference);
		
		} catch (IOException e){ 
			
		}
		
		return region;
	
	}
	
	public List<ExamType> getExamTypeFromJson(){
		
		List<ExamType> exam = null;
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<ExamType>> typeReference = new TypeReference<List<ExamType>>(){};
		InputStream inputStream = TypeReference.class.getResourceAsStream("/json/examType.json");
		
		try{
			
			exam = mapper.readValue(inputStream, typeReference);
		
		} catch (IOException e){ 
			
		}
		
		return exam;
	
	}

}
