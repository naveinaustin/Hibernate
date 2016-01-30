package test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_COMMUNITY")
@SuppressWarnings("serial")
public class Community
{
	Long id;
	String name;
	String description;
	
	@Id
	@Column(name = "community_id")
	public Long getId()
    {
    	return id;
    }
	public void setId(Long id)
    {
    	this.id = id;
    }
	
	@Column(name = "community_name")
	public String getName()
    {
    	return name;
    }
	public void setName(String name)
    {
    	this.name = name;
    }
	
	@Column(name = "community_desc")
	public String getDescription()
    {
    	return description;
    }
	public void setDescription(String description)
    {
    	this.description = description;
    }
	
	
}
