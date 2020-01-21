package com.archimydes.userstory.controller;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.archimydes.userstory.controller.UserStoryRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.archimydes.userstory.domain.UserStory;

@RestController
public class UserStoryController {
	
	@Autowired // To get the bean called userStoryRepository
    // Which is auto-generated by Spring; we will use it to handle the data
private UserStoryRepository userStoryRepository;
	
	  @CrossOrigin(origins = "http://localhost:3000")
	  @GetMapping(path="/api/getStories")
	  public @ResponseBody Collection<UserStory> getStories() {
    	System.out.println("In UserStoryController :: getStories()");
    	// This returns a JSON or XML with the userstories
    	return userStoryRepository.findAll();    
    	}
	  @CrossOrigin(origins = "http://localhost:3000")
	  @GetMapping("/api/getStory/{id}")
	    ResponseEntity<?> getStory(@PathVariable String id) {
		  System.out.println("In UserStoryController :: getStory/{id}() -- "+id);
	        Optional<UserStory> group = userStoryRepository.findById(Integer.parseInt(id));
	        return group.map(response -> ResponseEntity.ok().body(response))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	  
	  @PostMapping(path="/api/createStory") // Map ONLY POST Requests
	  public @ResponseBody String createStory(UserStory reqUserStory) {
		  System.out.println("In UserStoryController :: createStory()");
		  UserStory userStory = new UserStory(); 
	  	  userStory.setComplexity(reqUserStory.getComplexity());
	  	  userStory.setCost(reqUserStory.getCost());
	  	  userStory.setCreatedBy(reqUserStory.getCreatedBy());
	  	  userStory.setDescription(reqUserStory.getDescription());
	  	  userStory.setEta(reqUserStory.getEta());
	  	  userStory.setStatus("New");
	  	  userStory.setSummary(reqUserStory.getSummary());
	  	  userStory.setType(reqUserStory.getType());
	  	  userStoryRepository.save(userStory);	  	    	
	  	  System.out.println("In UserStoryController :: createStory() -- User Story created successfully");
	  	  return "User Story created successfully";
        }
    
	/* To implement assign to admin
	 * public void updateStory(UserStory userStory) {
	 * System.out.println("In UserStoryController :: createUpdateStory()");
	 * if(!(userStory.getId().toString().isEmpty())) {
	 * userStoryRepository.deleteById(userStory.getId());
	 * 
	 * } userStoryRepository.save(userStory); System.out.
	 * println("In UserStoryDAO :: createUpdateStory() -- User Story successfully created"
	 * ); }
	 */
    
	  
		
}
