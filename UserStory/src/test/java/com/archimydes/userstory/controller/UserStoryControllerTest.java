/**
 * 
 */
package com.archimydes.userstory.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.archimydes.userstory.domain.UserStory;
import com.archimydes.userstory.repository.UserStoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sowmya
 *
 */
@SpringBootTest
class UserStoryControllerTest {
	
	
	
	
	@Autowired
	private UserStoryRepository repository;
	@Before
	public void setUp() {
		//repository.clear();
	}

	/**
	 * Test method for {@link com.archimydes.userstory.controller.UserStoryController#getStories()}.
	 */
	/*
	 * @Test final void testGetStories() { fail("Not yet implemented"); // TODO }
	 */
	/**
	 * Test method for {@link com.archimydes.userstory.controller.UserStoryController#createStory(com.archimydes.userstory.domain.UserStory)}.
	 */
	private UserStory injectUserStory() {
		UserStory userStory = new UserStory();
		/*
		 * userStory.setDescription("Test description");
		 * userStory.setComplexity("Medium"); userStory.setCost(50);
		 * userStory.setCreatedBy("Sowmya"); userStory.setEta("27-Jan-2019");
		 * userStory.setStatus("New");
		 * userStory.setSummary("Summary summary Test summary");
		 * userStory.setType("Change Request");
		 */
		/*
		 * userStory.setDescription("Need to be fixed on high priority");
		 * userStory.setComplexity("High"); userStory.setCost(500);
		 * userStory.setCreatedBy("Test User1"); userStory.setEta("31-Jan-2019");
		 * userStory.setStatus("Assigned"); userStory.setSummary("Test Summary");
		 * userStory.setType("Bug Fix");
		 */
		/*
		 * userStory.setDescription("Test description"); userStory.setComplexity("Low");
		 * userStory.setCost(10); userStory.setCreatedBy("Sowmya");
		 * userStory.setEta("27-Feb-2019"); userStory.setStatus("Approved");
		 * userStory.setSummary("Test summary"); userStory.setType("Enhancement");
		 */
		
		userStory.setDescription("Test description one");
		 userStory.setComplexity("Low");
	  	  userStory.setCost(20);
	  	  userStory.setCreatedBy("TestUser1");
	  	  userStory.setEta("27-Feb-2019");
	  	  userStory.setStatus("Rejected");
	  	  userStory.setSummary("Test summary one");
	  	  userStory.setType("New Requirement");
	  	    	    	
	  	  System.out.println("In UserStoryController :: createStory() -- User Story created successfully");
    	
    	return repository.save(userStory);
    }
	@Test
	final void testCreateStory() throws Exception{
		UserStory userStory = injectUserStory();
		/*
		 * //assertNoUserStories(); UserStory userStory = generateTestUserStory();
		 * //System.out.println("*******userStory -> "+ userStory.getId());
		 * System.out.println("*******userStory JSON -> "+ toJsonString(userStory));
		 * createOrder(toJsonString(userStory)); assertCountIs(1);
		 * //assertAllButIdsMatchBetweenStories(userStory, getCreatedOrder());
		 */	}
	
	private MockHttpServletRequestBuilder createOrder(String payload) throws Exception {
    	return post("/api/createStory", payload);
    }
	
	  private void assertNoUserStories() {
	    	assertCountIs(0);
	    }
	    
	    private void assertCountIs(int count) {
	    	Assert.assertEquals(count, StreamSupport.stream(repository.findAll().spliterator(), false).count());
	    }
	    protected static String toJsonString(final Object obj) throws JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		}
	
	

}
