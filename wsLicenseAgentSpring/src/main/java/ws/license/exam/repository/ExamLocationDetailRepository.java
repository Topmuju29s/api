package ws.license.exam.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ws.license.exam.entities.ExamLocationDetail;

@Repository
public interface ExamLocationDetailRepository extends JpaRepository<ExamLocationDetail, Integer> 
{
	
//	@Query(value = "SELECT es from ExamSchedule es "
//    		+ "INNER JOIN ExamLocation el on el.locationId = es.locationId "
//    		+ "where el.provinceCode = :provinceCode and el.orgCode = :examOrg "
//    		+ " order by es.scheduleId desc", nativeQuery = false)
//	@Query(value = "SELECT el, er.provinceName from ExamLocation el "
//			+ "left join ExamRegion er on er.provinceCode = el.provinceCode "
//			+ " order by el.locationId desc", nativeQuery = true)

	@Query(value = "select el.*, er.province_name, er.region as region_code, er.region as region_name, el.location_type as location_type_name, eo.org_name "
			+ " from sales_license.exam_location el "
			+ "		  left join sales_license.exam_region er "
			+ "		    on er.province_code = el.province_code"
			+ "		  left join sales_license.exam_organizer eo"
			+ "         on eo.org_code = el.org_code"
			+ " order by location_id asc ", nativeQuery = true)
    public List<ExamLocationDetail> findExamLocationAll();
	
	@Query(value = "select el.*, er.province_name, er.region as region_code, er.region as region_name, el.location_type as location_type_name, eo.org_name "
			+ " from sales_license.exam_location el "
			+ "		  left join sales_license.exam_region er "
			+ "		    on er.province_code = el.province_code"
			+ "		  left join sales_license.exam_organizer eo"
			+ "         on eo.org_code = el.org_code"
			+ " where el.location_id = :locationId "
			+ " order by location_id asc ", nativeQuery = true)
	public List<ExamLocationDetail> findExamLocationById(@Param("locationId") int locationId);
	
	@Modifying
	@Query(value = "update sales_license.exam_location SET last_update = current_timestamp where location_id = :locationId ", nativeQuery = true)
	@Transactional
	public void updateExamLocation(@Param("locationId") int locationId);

	
}
