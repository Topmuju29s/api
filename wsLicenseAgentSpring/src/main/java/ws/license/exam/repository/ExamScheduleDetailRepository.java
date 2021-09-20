package ws.license.exam.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ws.license.exam.entities.ExamScheduleDetail;

@Repository
public interface ExamScheduleDetailRepository extends JpaRepository<ExamScheduleDetail, Integer>,JpaSpecificationExecutor<ExamScheduleDetail>
{

	public List<ExamScheduleDetail> findByExamDate(Date examDate);
	public List<ExamScheduleDetail> findByRoundId(String roundId);
    public List<ExamScheduleDetail> findByExamDateAndRoundId(Date examDate, String roundId);
    
    @Query(value = "SELECT es.*, el.province_code, er.province_name, er.region as region_code, er.region as region_name, "
    		+ "			   el.location_type, el.location_type as location_type_name, eo.org_code, eo.org_name, ero.time_str,"
    		+ "			   el.location_detail "
    		+ " from sales_license.exam_schedule es "
    		+ " left join sales_license.exam_location el "
    		+ "        on el.location_id = es.location_id "
    		+ " left join sales_license.exam_organizer eo "
    		+ "		   on eo.org_code = el.org_code "
    		+ " left join sales_license.exam_region er "
    		+ " 	   on er.province_code = el.province_code "
    		+ " left join sales_license.exam_round ero "
    		+ "	       on ero.round_id = es.round_id "
    		+ " where el.province_code = :provinceCode and el.org_code = :examOrg "
    		+ " order by es.schedule_id asc", nativeQuery = true)
    public List<ExamScheduleDetail> findByProvinceCodeAndExamOrg(@Param("provinceCode") String provinceCode,
    												@Param("examOrg") String examOrg);

    @Query(value = "SELECT es.*, el.province_code, er.province_name, er.region as region_code, er.region as region_name, "
    		+ "			   el.location_type, el.location_type as location_type_name, eo.org_code, eo.org_name, ero.time_str,"
    		+ "			   el.location_detail "
    		+ " from sales_license.exam_schedule es "
    		+ " left join sales_license.exam_location el "
    		+ "        on el.location_id = es.location_id "
    		+ " left join sales_license.exam_organizer eo "
    		+ "		   on eo.org_code = el.org_code "
    		+ " left join sales_license.exam_region er "
    		+ " 	   on er.province_code = el.province_code "
    		+ " left join sales_license.exam_round ero "
    		+ "	       on ero.round_id = es.round_id "
    		+ " where el.province_code = :provinceCode"
    		+ " order by es.schedule_id asc", nativeQuery = true)
    public List<ExamScheduleDetail> findByProvinceCode(@Param("provinceCode") String provinceCode);
    
    @Query(value = "SELECT es.*, el.province_code, er.province_name, er.region as region_code, er.region as region_name, "
    		+ "			   el.location_type, el.location_type as location_type_name, eo.org_code, eo.org_name, ero.time_str,"
    		+ "			   el.location_detail "
    		+ " from sales_license.exam_schedule es "
    		+ " left join sales_license.exam_location el "
    		+ "        on el.location_id = es.location_id "
    		+ " left join sales_license.exam_organizer eo "
    		+ "		   on eo.org_code = el.org_code "
    		+ " left join sales_license.exam_region er "
    		+ " 	   on er.province_code = el.province_code "
    		+ " left join sales_license.exam_round ero "
    		+ "	       on ero.round_id = es.round_id "
    		+ " where el.org_code = :examOrg"
    		+ " order by es.schedule_id asc", nativeQuery = true)
    public List<ExamScheduleDetail> findByExamOrg(@Param("examOrg") String examOrg);
    
    @Query(value = "SELECT es.*, el.province_code, er.province_name, er.region as region_code, er.region as region_name, "
    		+ "			   el.location_type, el.location_type as location_type_name, eo.org_code, eo.org_name, ero.time_str,"
    		+ "			   el.location_detail "
    		+ " from sales_license.exam_schedule es "
    		+ " left join sales_license.exam_location el "
    		+ "        on el.location_id = es.location_id "
    		+ " left join sales_license.exam_organizer eo "
    		+ "		   on eo.org_code = el.org_code "
    		+ " left join sales_license.exam_region er "
    		+ " 	   on er.province_code = el.province_code"
    		+ " left join sales_license.exam_round ero "
    		+ "	       on ero.round_id = es.round_id "
    		+ " order by es.schedule_id asc", nativeQuery = true)
    public List<ExamScheduleDetail> findAll();
    
    @Query(value = "SELECT es.*, el.province_code, er.province_name, er.region as region_code, er.region as region_name, "
    		+ "			   el.location_type, el.location_type as location_type_name, eo.org_code, eo.org_name, ero.time_str,"
    		+ "			   el.location_detail "
    		+ " from sales_license.exam_schedule es "
    		+ " left join sales_license.exam_location el "
    		+ "        on el.location_id = es.location_id "
    		+ " left join sales_license.exam_organizer eo "
    		+ "		   on eo.org_code = el.org_code "
    		+ " left join sales_license.exam_region er "
    		+ " 	   on er.province_code = el.province_code"
    		+ " left join sales_license.exam_round ero "
    		+ "	       on ero.round_id = es.round_id "
    		+ " where es.schedule_id = :scheduleId "
    		+ " order by es.schedule_id asc", nativeQuery = true)
    public List<ExamScheduleDetail> findById(@Param("scheduleId") int scheduleId);
	
}
