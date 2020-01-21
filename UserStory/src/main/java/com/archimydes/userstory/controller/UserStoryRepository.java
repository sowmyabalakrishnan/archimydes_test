/**
 * 
 */
package com.archimydes.userstory.controller;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archimydes.userstory.domain.UserStory;


/**
 * @author Sowmya
 *
 */

	// This will be AUTO IMPLEMENTED by Spring into a Bean called userStoryRepository
	// CRUD refers Create, Read, Update, Delete

	public interface UserStoryRepository extends JpaRepository<UserStory, Integer> {
	}

