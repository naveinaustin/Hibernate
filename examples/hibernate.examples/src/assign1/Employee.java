package assign1;

import java.util.Date;

public class Employee 
{
	private String empId;
	private String name;
	private Date dob;
	private String address;
	
	public Employee( String empId, String name, Date dob, String address )
	{
		this.empId = empId;
		this.name = name;
		this.dob = dob;
		this.address = address;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
