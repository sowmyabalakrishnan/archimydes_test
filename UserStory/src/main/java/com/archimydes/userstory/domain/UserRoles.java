package com.archimydes.userstory.domain;

import javax.persistence.*;

import com.archimydes.userstory.domain.types.RoleType;

@Entity
public class UserRoles {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer role_id;
    @Enumerated(EnumType.STRING)
    private RoleType name;
    public UserRoles() {}
    public UserRoles(RoleType name) {
        this.name = name;
    }
	/**
	 * @return the id
	 */
	public Integer getId() {
		return role_id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.role_id = id;
	}
	/**
	 * @return the name
	 */
	public RoleType getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(RoleType name) {
		this.name = name;
	}

}
