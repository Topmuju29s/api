package ws.license.exam.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ws.license.exam.configuration.Jsondb;

@Entity
@Table(name = "exam_location")
public class ExamLocation 
{	
	
    private static final long serialVersionUID = 1L;
    @Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "location_id", unique = true, nullable = false)
    private int locationId;
    
    @Column(name = "orgCode", length = 2)
    private String orgCode;
    
    @Transient
    private String orgName;

	@Column(name = "province_code", length = 3)
    private String provinceCode;
    
	@Transient
    private String provinceName;
    
	@Transient
    private String regionCode;    

    @Transient
	private String regionName;

	@Column(name = "location_detail", length = 300)
    private String locationDetail;

    @Column(name = "location_type", length = 1)
    private String locationType;
    
    @Transient
    private String locationTypeName;

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

	public ExamLocation() {}

	public ExamLocation(int locationId, String orgCode, String provinceCode, String locationDetail, String locationType,
						String createUserCode, String updateUserCode) 
	{
		this.locationId = locationId;
		this.orgCode = orgCode;
		this.provinceCode = provinceCode;
		this.locationDetail = locationDetail;
		this.locationType = locationType;
		this.createUserCode = createUserCode;
		this.updateUserCode = updateUserCode;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
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
		String regionName = "";
		try {
			regionName = region.getRegionFromJson().get(Integer.parseInt(regionCode)).getRegionName();
		} catch (Exception e){
			regionName = "";
		}
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
}
