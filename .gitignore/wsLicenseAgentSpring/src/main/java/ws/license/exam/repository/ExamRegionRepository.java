package ws.license.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ws.license.exam.entities.ExamRegion;


public interface ExamRegionRepository  extends JpaRepository<ExamRegion, String>
{

}
