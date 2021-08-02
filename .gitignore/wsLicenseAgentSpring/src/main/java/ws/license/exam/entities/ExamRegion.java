package ws.license.exam.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "exam_region")
public class ExamRegion {
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name = "province_code", unique = true, nullable = false, length = 3)
	private String provinceCode;

	@Column(name = "province_name", length = 100)
	private String provinceName;
	
	@Column(name = "region", length = 1)
	private String region;

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

	public ExamRegion(){}
	
	public ExamRegion(String provinceCode, String provinceName, String region, String createUserCode,String updateUserCode) 
	{
		this.provinceCode = provinceCode;
		this.provinceName = provinceName;
		this.region = region;
		this.createUserCode = createUserCode;
		this.updateUserCode = updateUserCode;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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
	
	
}
