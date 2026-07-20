package users;

import java.sql.Date;

public class Employee extends Register{
	
	private int ssn;
	private String branchNo;
	private String departementname;
	private String fname;
	private String lname;
	private Date birthdate;
	private String position;
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getDepartementname() {
		return departementname;
	}
	public void setDepartementname(String departementname) {
		this.departementname = departementname;
	}
	@Override
	public String toString() {
		return "Employee [ssn=" + ssn + ", branchNo=" + branchNo + ", departementname=" + departementname + ", fname="
				+ fname + ", lname=" + lname + ", birthdate=" + birthdate + ", position=" + position + ", getEmail()="
				+ getEmail() + ", getPhoneNumber()=" + getPhoneNumber() + ", getPassword()=" + getPassword()
				+ ", address()=" + address() + ", getPobox()=" + getPobox() + ", getCountry()=" + getCountry()
				+ ", getCity()=" + getCity() + ", getProvince()=" + getProvince() + ", getHouse()=" + getHouse()
				+ ", getStreetName()=" + getStreetName() + "]";
	}
	
	
	
	
	

}
