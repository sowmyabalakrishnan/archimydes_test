/**
 * 
 */
package com.archimydes.userstory.controller;

import org.springframework.data.repository.CrudRepository;

import com.archimydes.userstory.domain.UserStory;


/**
 * @author Sowmya
 *
 */

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userStoryRepository
	// CRUD refers Create, Read, Update, Delete

	public interface UserStoryRepository extends CrudRepository<UserStory, Integer> {

	}

