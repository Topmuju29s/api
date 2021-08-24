package ws.license.exam.repository;

import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ws.license.exam.entities.ExamSchedule;

@Repository
@Transactional
public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Integer>, JpaSpecificationExecutor<ExamSchedule>
{
    
    List<ExamSchedule> findByExamDate(Date examDate);
    List<ExamSchedule> findByRoundId(String roundId);
    List<ExamSchedule> findByExamDateAndRoundId(Date examDate, String roundId);
    
    @Query("SELECT es from exam_schedule es "
    		+ "JOIN ExamLocation el on el.locationId = es.locationId "
    		+ "where el.provinceCode = :provinceCode and el.orgCode = :examOrg "
    		+ " order by es.scheduleId desc")
    List<ExamSchedule> findByProvinceCodeAndExamOrg(@Param("provinceCode") String provinceCode,
    												@Param("examOrg") String examOrg);
    
    @Query("SELECT es from exam_schedule es "
    		+ "JOIN ExamLocation el on el.locationId = es.locationId "
    		+ "where el.provinceCode = :provinceCode "
    		+ " order by es.scheduleId desc")
    List<ExamSchedule> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
    @Query("SELECT es from exam_schedule es "
    		+ "JOIN ExamLocation el on el.locationId = es.locationId "
    		+ "where el.orgCode = :examOrg "
    		+ " order by es.scheduleId desc")
    List<ExamSchedule> findByExamOrg(@Param("examOrg") String examOrg);
    
    
}
