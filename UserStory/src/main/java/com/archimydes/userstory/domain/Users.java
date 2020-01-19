/**
 * 
 */
package com.archimydes.userstory.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Sowmya
 *
 */
@Entity 
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String fName;
	private String lName;
	private String isAdmin;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
