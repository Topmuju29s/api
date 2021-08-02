package ws.license.exam.service;

import java.util.List;
import ws.license.exam.entities.ExamRound;

public interface ExamRoundServiceImp 
{
	List<ExamRound> searchExamRound(String type);
	ExamRound addExamRound(ExamRound examRound);
	ExamRound updateExamRound(ExamRound examRound);
	void deleteExamRound(String id);
	List<ExamRound> testSearchSQL(String type);
	ExamRound findById(String id);
}