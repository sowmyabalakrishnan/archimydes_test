package com.archimydes.userstory.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.archimydes.userstory.domain.UserStory;
import com.archimydes.userstory.repository.UserRepository;
import com.archimydes.userstory.repository.UserStoryRepository;

@RestController
public class UserStoryController {
	
	@Autowired // To get the bean called userStoryRepository
    // Which is auto-generated by Spring; we will use it to handle the data
private UserStoryRepository userStoryRepository;

public UserStoryController(UserStoryRepository userStoryRepository, UserRepository userRepository) {
    this.userStoryRepository = userStoryRepository;
}

	  @GetMapping(path="/api/getStories/{userName}")
	  public @ResponseBody Collection<UserStory> getStories(@PathVariable String userName) {
    	System.out.println("In UserStoryController :: getStories() for "+userName);   
        	return userStoryRepository.findAllByCreatedBy(userName);    		
    	}
	  
	  @GetMapping(path="/api/getAllStories")
	  public @ResponseBody Collection<UserStory> getAllStories() {
    	System.out.println("In UserStoryController :: getAllStories()");
    	return userStoryRepository.findAll(Sort.by(Sort.Direction.ASC, "status"));
    	}
	  
	  @GetMapping("/api/getStory/{id}")
	    public ResponseEntity<?> getStory(@PathVariable String id) {
		  System.out.println("In UserStoryController :: getStory/{id}() -- "+id);
	        Optional<UserStory> group = userStoryRepository.findById(Integer.parseInt(id));
	        return group.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	  
	  @PostMapping(path="/api/story")
	  public ResponseEntity<UserStory> createStory(@Valid @RequestBody UserStory reqUserStory) throws URISyntaxException  {
		  System.out.println("In UserStoryController :: createStory() :: reqUserStory -- "+reqUserStory);
	        UserStory result = userStoryRepository.save(reqUserStory);
	        return ResponseEntity.created(new URI("/api/story" + result.getId())).body(result);
        }
	  
	  @PutMapping(path="/api/story/{id}")
	    public ResponseEntity<UserStory> updateGroup(@Valid @RequestBody UserStory reqStory) {
	        System.out.println("Request to update User Story: {}"+reqStory);
	        UserStory result = userStoryRepository.save(reqStory);
	        return ResponseEntity.ok().body(result);
	    }		
}
