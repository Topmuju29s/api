package ws.license.exam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ws.license.exam.entities.ExamSchedule;

@Repository
public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Integer>,JpaSpecificationExecutor<ExamSchedule>
{

	public List<ExamSchedule> findByExamDate(Date examDate);
	public List<ExamSchedule> findByRoundId(String roundId);
    public List<ExamSchedule> findByExamDateAndRoundId(Date examDate, String roundId);
    
    @Query(value = "SELECT es from ExamSchedule es "
    		+ "INNER JOIN ExamLocation el on el.locationId = es.locationId "
    		+ "where el.provinceCode = :provinceCode and el.orgCode = :examOrg "
    		+ " order by es.scheduleId desc", nativeQuery = false)
    public List<ExamSchedule> findByProvinceCodeAndExamOrg(@Param("provinceCode") String provinceCode,
    												@Param("examOrg") String examOrg);

    @Query(value = "SELECT es from ExamSchedule es "
    		+ "INNER JOIN ExamLocation el on el.locationId = es.locationId "
    		+ "where el.provinceCode = :provinceCode "
    		+ " order by es.scheduleId desc", nativeQuery = false)
    public List<ExamSchedule> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
    @Query(value = "SELECT es from ExamSchedule es "
    		+ "JOIN ExamLocation el on el.locationId = es.locationId "
    		+ "where el.orgCode = :examOrg "
    		+ " order by es.scheduleId desc", nativeQuery = false)
    public List<ExamSchedule> findByExamOrg(@Param("examOrg") String examOrg);
    
    @Query(value = "SELECT es.* "
    		+ " from sales_license.exam_schedule es "
    		+ " left join sales_license.exam_location el "
    		+ "        on el.location_id = es.location_id"
    		+ " order by es.schedule_id desc", nativeQuery = true)
    public List<ExamSchedule> findAll();
    
    @Query(value = "SELECT es.* "
    		+ " from sales_license.exam_schedule es "
    		+ " left join sales_license.exam_location el "
    		+ "        on el.location_id = es.location_id"
    		+ " order by es.schedule_id desc", nativeQuery = true)
    public List<ExamSchedule> findById(@Param("scheduleId") int scheduleId);
	
}
