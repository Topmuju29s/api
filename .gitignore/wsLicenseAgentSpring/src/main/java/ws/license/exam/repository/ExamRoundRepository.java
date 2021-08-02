package ws.license.exam.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import ws.license.exam.entities.ExamRound;

@Repository
public interface ExamRoundRepository extends JpaRepository<ExamRound, String>/*, JpaSpecificationExecutor<ExamRound> */
{
	@Query(value="SELECT e.roundId, e.timeStr, e.createUserCode, e.createTime, e.updateUserCode, e.lastUpdate FROM ExamRound e")
    public List<ExamRound> searchDataAll(); 
	
//	@Query(value = "SELECT e.roundId, e.timeStr, e.createUserCode, e.createTime, e.updateUserCode, e.lastUpdate FROM ExamRound e where e.roundId = (:roundId)", nativeQuery = true)
//	ExamRound findInfoById(@Param("id") String id);
	
//	@Modifying
//	@Query(
//	  value = 
//	    "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
//	  nativeQuery = true)
//	void insertUser(@Param("name") String name, @Param("age") Integer age, 
//	  @Param("status") Integer status, @Param("email") String email);
}
