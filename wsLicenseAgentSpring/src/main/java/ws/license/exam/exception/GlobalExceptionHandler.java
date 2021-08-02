package ws.license.exam.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice

public class GlobalExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorDto> generateException(ResponseStatusException re)
	{
		ErrorDto dto = new ErrorDto();
		dto.setTimestamp(new Date().toString());
		dto.setStatus( String.valueOf( re.getStatus().value()));
		dto.setErrorMessage(re.getReason());
		//log.error("Exception Occured : ",re);
		
		return new ResponseEntity<ErrorDto>(dto,re.getStatus());
	}
	
//	@ExceptionHandler(NotFoundException.class)
//	public ResponseEntity<ErrorDto> generateException(NotFoundException re)
//	{
//		ErrorDto dto = new ErrorDto();
//		dto.setTimestamp(new Date().toString());
//		dto.setStatus("404");
//		dto.setErrorMessage(re.getMessage());
//		//log.error("Exception Occured : ",re);
//		
//		return new ResponseEntity<ErrorDto>(dto,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	@ExceptionHandler(RuntimeException.class)
//	public ResponseEntity<ErrorDto> generateException(RuntimeException re, WebRequest request)
//	{
//		ErrorDto dto = new ErrorDto();
//		dto.setTimestamp(new Date().toString());
//		dto.setStatus("500");
//		dto.setErrorMessage(re.getMessage());
//		//log.error("Exception Occured : ",re);
//
//		return new ResponseEntity<ErrorDto>(dto,HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}