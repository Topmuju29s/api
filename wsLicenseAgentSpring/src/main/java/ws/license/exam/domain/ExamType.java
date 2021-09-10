package ws.license.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@Embeddable
public class ExamType {

	private String examTypeId;
	private String examTypeName;
	
	public String getExamTypeId() {
		return examTypeId;
	}
	public void setExamTypeId(String examTypeId) {
		this.examTypeId = examTypeId;
	}
	public String getExamTypeName() {
		return examTypeName;
	}
	public void setExamTypeName(String regionName) {
		this.examTypeName = regionName;
	}

}
