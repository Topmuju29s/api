package ws.license.exam.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;
//import org.springframework.format.annotation.DateTimeFormat;

//import lombok.Getter;
//import lombok.Setter;

@Entity
@Table(name = "exam_round")
public class ExamRound 
{
	private static final long serialVersionUID = 1L;
	@Id 
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name = "round_id", unique = true, nullable = false, length = 2)
    private String roundId;
	
	@Column(name = "time_str", length = 20)
    private String timeStr;
	
	@Column(name = "create_user_code", length = 10)
    private String createUserCode;
	
	@CreationTimestamp
	@Column(name = "create_time")
	private Timestamp createTime;
    
    @Column(name = "update_user_code", length = 10)
    private String updateUserCode;
    
    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public ExamRound() {}
    public ExamRound(String roundId,String timeStr,String createUserCode,String updateUserCode) 
    {
    	this.roundId = roundId;
    	this.timeStr = timeStr;
    	this.createUserCode = createUserCode; 
    	this.updateUserCode = updateUserCode;   
    }    
    
    public void setRoundId(String roundId) throws Exception
    {
        this.roundId = roundId;    
    }
    public String getRoundId()
    {
        return roundId;   
    }

    public void setTimeStr(String timeStr) throws Exception
    {
       this.timeStr = timeStr;    
    }
    public String getTimeStr()
    {
        return timeStr;    
    }

    public void setCreateUserCode(String createUserCode) throws Exception
    {
        this.createUserCode = createUserCode;    
    }
    public String getCreateUserCode()
    {
        return createUserCode;    
    }

    public void setCreateTime(Timestamp createTime) throws Exception
    {
        this.createTime = createTime;    
    }
    public Timestamp getCreateTime()
    {
        return createTime;    
    }

    public void setUpdateUserCode(String updateUserCode) throws Exception
    {
        this.updateUserCode = updateUserCode;    
    }
    public String getUpdateUserCode()
    {
        return updateUserCode;    
    }

    public void setLastUpdate(Timestamp lastUpdate) throws Exception
    {
        this.lastUpdate = lastUpdate;    
    }
    public Timestamp getLastUpdate()
    {
        return lastUpdate;    
    }
  
}