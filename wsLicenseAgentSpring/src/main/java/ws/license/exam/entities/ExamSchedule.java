package ws.license.exam.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "exam_schedule")
@Table(name = "exam_schedule")
public class ExamSchedule implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "schedule_id", unique = true, nullable = false)
    private int scheduleId;

    @Column(name = "location_id")
    private int locationId;
//	@OneToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//	@JoinColumn(name = "location_id", referencedColumnName="location_id")
//	private ExamLocation examLocation;

	@Column(name = "altered_location_id")
    private int alteredLocationId;
	
	@Column(name = "exam_date")
    private Date examDate;
	
	@Column(name = "round_id", length = 2)
    private String roundId;
   
	@Column(name = "max_applicant")
    private int maxApplicant;
  
	@Column(name = "apply_open_date")
    private Date applyOpenDate;
	
	@Column(name = "apply_close_date")
    private Date applyCloseDate;
	
	@Column(name = "open_status",length = 1)
    private String openStatus;
   
	@Column(name = "create_user_code", length = 10)
    private String createUserCode;
        
    @Column(name = "receive_date")
    private Date receiveDate;
                 
    @Column(name = "receive_time", length = 6)
    private String receiveTime;

	@CreationTimestamp
	@Column(name = "create_time")
	private Timestamp createTime;
	
	@Column(name = "update_user_code", length = 10)
    private String updateUserCode;
	
	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;   
	
	//private String provinceCode;
	
	
	public ExamSchedule(){}
	
	public ExamSchedule(int scheduleId, int locationId, int alteredLocationId, Date examDate, String roundId,
			int maxApplicant, Date applyOpenDate, Date applyCloseDate, String openStatus, 
            Date receiveDate, String receiveTime, String createUserCode,String updateUserCode, String provinceCode) 
	{
		this.scheduleId = scheduleId;
		this.locationId = locationId;
		this.alteredLocationId = alteredLocationId;
		this.examDate = examDate;
		this.roundId = roundId;
		this.maxApplicant = maxApplicant;
		this.applyOpenDate = applyOpenDate;
		this.applyCloseDate = applyCloseDate;
		this.openStatus = openStatus;
        this.receiveDate = receiveDate;
        this.receiveTime = receiveTime;
		this.createUserCode = createUserCode;
		this.updateUserCode = updateUserCode;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getAlteredLocationId() {
		return alteredLocationId;
	}

	public void setAlteredLocationId(int alteredLocationId) {
		this.alteredLocationId = alteredLocationId;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getRoundId() {
		return roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}

	public int getMaxApplicant() {
		return maxApplicant;
	}

	public void setMaxApplicant(int maxApplicant) {
		this.maxApplicant = maxApplicant;
	}

	public Date getApplyOpenDate() {
		return applyOpenDate;
	}

	public void setApplyOpenDate(Date applyOpenDate) {
		this.applyOpenDate = applyOpenDate;
	}

	public Date getApplyCloseDate() {
		return applyCloseDate;
	}

	public void setApplyCloseDate(Date applyCloseDate) {
		this.applyCloseDate = applyCloseDate;
	}

	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}
        
    public Date getReceiveDate(){
        return receiveDate;
    }
    
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
    
    public String getReceiveTime(){
        return receiveTime;
    }
    
    public void setReceiveTime(String receiveTime){
        this.receiveTime = receiveTime;
    }

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
//	public ExamLocation getExamLocaiton() {
//		return examLocation;
//	}
//
//	public void setExamLocaiton(ExamLocation examLocaiton) {
//		this.examLocation = examLocaiton;
//	}
	
//	public String getProvinceCode() {
//		return provinceCode;
//	}
//
//	public void setProvinceCode(String provinceCode) {
//		this.provinceCode = provinceCode;
//	}

	
}
