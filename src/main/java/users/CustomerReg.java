package users;

public class CustomerReg extends Register{
	
	private String username;

	private String fname;
	private String lname;
	
	
	

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String email) {
		
		this.username =email;
	}

	@Override
	public String toString() {
		return "CustomerReg [username=" + username + ", fname=" + fname + ", lname=" + lname + ", getEmail()="
				+ getEmail() + ", getPhoneNumber()=" + getPhoneNumber() + ", getPassword()=" + getPassword()
				+ ", address()=" + address() + ", getPobox()=" + getPobox() + ", getCountry()=" + getCountry()
				+ ", getCity()=" + getCity() + ", getProvince()=" + getProvince() + ", getHouse()=" + getHouse()
				+ ", getStreetName()=" + getStreetName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}





	
	
	
	
	

}
