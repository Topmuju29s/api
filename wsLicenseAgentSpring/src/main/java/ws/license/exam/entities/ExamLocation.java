package ws.license.exam.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "exam_location")
@XmlRootElement
public class ExamLocation implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "location_id", unique = true, nullable = false) 
    private int locationId; 
    
    @Column(name = "orgCode", length = 2)
    private String orgCode;

    @Column(name = "province_code", length = 3)
    private String provinceCode;

    @Column(name = "location_detail", length = 300)
    private String locationDetail;

    @Column(name = "location_type", length = 1)
    private String locationType;

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
        
//    private ExamSchedule getExamSchedule() {
//                   return examSchedule;
//    }
//    
//    private void setExamSchedule(ExamLocation examLocation) {
//                    this.examSchedule = examSchedule;
//    }
//    
    
}
