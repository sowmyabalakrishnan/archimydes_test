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
public class UserStory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String createdBy;
	private String summary;
	private String description;
	private String type;
	private String complexity;
	private String eta;
	private Integer cost;
	private String status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
