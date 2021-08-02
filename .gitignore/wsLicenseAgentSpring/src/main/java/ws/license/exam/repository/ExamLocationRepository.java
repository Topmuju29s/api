package ws.license.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ws.license.exam.entities.ExamLocation;

@Repository
public interface ExamLocationRepository extends JpaRepository<ExamLocation, Integer> 
{

}
