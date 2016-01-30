package assign1;

import java.util.Set;

public class Company 
{
	private String name;
	private String address;
	private Set employees;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Set getEmployee() {
		return employees;
	}
	public void setEmployee(Set employees) {
		this.employees = employees;
	}
}
