package ws.license.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@AllArgsConstructor
@Embeddable
public class Region {

	private String regionCode;
	private String regionName;
	
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
