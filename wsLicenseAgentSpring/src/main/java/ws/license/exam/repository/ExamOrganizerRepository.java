package ws.license.exam.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ws.license.exam.entities.ExamOrganizer;

@Repository
public interface ExamOrganizerRepository extends JpaRepository<ExamOrganizer, String>
{

	public List<ExamOrganizer> findAllByOrderByOrgCodeAsc();
	
}
