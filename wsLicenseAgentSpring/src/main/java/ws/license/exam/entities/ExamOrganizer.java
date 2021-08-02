package ws.license.exam.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "exam_organizer")
public class ExamOrganizer 
{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "org_code", unique = true, nullable = false, length = 2)
    private String orgCode;
	
	@Column(name = "org_name", length = 50)
    private String orgName;
	
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

    public ExamOrganizer(){}
    
	public ExamOrganizer(String orgCode, String orgName, String createUserCode, String updateUserCode) 
	{
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.createUserCode = createUserCode;
		this.updateUserCode = updateUserCode;
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
