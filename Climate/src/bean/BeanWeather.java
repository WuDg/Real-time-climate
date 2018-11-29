package bean;

public class BeanWeather {
	String siteName;
	String CODMn;
	String attribute;
	String dateTime;
	String level;
	String status;
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getCODMn() {
		return CODMn;
	}
	public void setCODMn(String cODMn) {
		CODMn = cODMn;
	}
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BeanWether1 [siteName=" + siteName + ", CODMn=" + CODMn + ", attribute=" + attribute + ", dateTime="
				+ dateTime + ", level=" + level + ", status=" + status + "]";
	}
	
}
