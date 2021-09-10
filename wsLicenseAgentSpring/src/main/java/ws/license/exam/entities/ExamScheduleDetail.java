package ws.license.exam.entities;

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

import ws.license.exam.configuration.Jsondb;

@Entity(name = "exam_schedule_detail")
@Table(name = "exam_schedule")
public class ExamScheduleDetail 
{
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "schedule_id", unique = true, nullable = false)
    private int scheduleId;
	
	@Column(name = "location_id")
    private int locationId;
	
	private String provinceCode;	
	
	private String provinceName;
	
	private String regionCode;    

	private String regionName;
	
	private String locationType;
	
	private String locationTypeName;
	
	private String locationDetail;	

	private String orgCode;
	
	private String orgName;

	@Column(name = "altered_location_id")
    private int alteredLocationId;
	
	@Column(name = "exam_date")
    private Date examDate;
	
	@Column(name = "round_id", length = 2)
    private String roundId;
	
	private String timeStr;
   
	@Column(name = "max_applicant")
    private int maxApplicant;

	@Column(name = "apply_open_date")
    private Date applyOpenDate;
	
	@Column(name = "apply_close_date")
    private Date applyCloseDate;
	
	@Column(name = "open_status",length = 1)
    private String openStatus;
	
	@Column(name = "receive_date")
    private Date receiveDate;    

	@Column(name = "receive_time", length = 6)
    private String receiveTime;
   
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

	
	public ExamScheduleDetail(){}
	
	public ExamScheduleDetail(int scheduleId, int locationId, int alteredLocationId, Date examDate, String roundId,
						int maxApplicant, Date applyOpenDate, Date applyCloseDate, String openStatus, 
						Date receiveDate, String receiveTime, String createUserCode,String updateUserCode) 
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
	
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		Jsondb region = new Jsondb();
		try {
			regionName = region.getRegionFromJson().get(Integer.parseInt(regionName)).getRegionName();
		} catch (Exception e){
			regionName = "";
		}
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocationTypeName() {
		Jsondb exam = new Jsondb();
		String locationTypeName = "";
		try {
		locationTypeName = exam.getExamTypeFromJson().get(Integer.parseInt(locationType) -1 ).getExamTypeName();
		} catch (Exception e) {
			locationTypeName = "";
		}
		return locationTypeName;
	}

	public void setLocationTypeName(String locationTypeName) {
		this.locationTypeName = locationTypeName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}	
	
	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}
	
	
	
}
