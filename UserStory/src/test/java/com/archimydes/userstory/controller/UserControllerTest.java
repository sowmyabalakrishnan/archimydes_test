package com.archimydes.userstory.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.archimydes.userstory.domain.User;
import com.archimydes.userstory.domain.UserRoles;
import com.archimydes.userstory.domain.types.RoleType;

/**
 * @author Sowmya
 *
 */
public class UserControllerTest {
	
	 User reqUser = new User();
	 User reqAdminUser = new User();
	 User reqNonExistingUser = new User();
	 User reqInvalidUser = new User();
	
	@Before
	public void setUp() {		
		    reqUser.setName("testuser2");
		    reqUser.setPassword("bbb");
		    
		    reqAdminUser.setName("testuser11");
		    reqAdminUser.setPassword("aaa");
		    
		    reqNonExistingUser.setName("testuser22");
		    reqNonExistingUser.setPassword("bbb");
		    
		    reqInvalidUser.setName("testuser2");
		    reqInvalidUser.setPassword("aaa");
		    
	}
	
	@Test
	public void testPostLoginUserExist() throws URISyntaxException 
	{
		HttpEntity<User> request = new HttpEntity<>(reqUser);
		
		RestTemplate restTemplate = new RestTemplate();	     
	    final String baseUrl = "http://localhost:8080/api/login";
	    URI uri = new URI(baseUrl);      
	 
	    ResponseEntity<User> result = restTemplate.postForEntity(uri, request, User.class);
	     
	    //Verify request succeed
	    System.out.println("result.getStatusCodeValue() -- "+result.getStatusCodeValue());
	    Assert.assertEquals(200, result.getStatusCodeValue());
	    Assert.assertEquals(true, reqUser.getPassword().equals(result.getBody().getPassword()));	    
	    Assert.assertEquals(true,isRolePresent(result.getBody().getUserRoles(),RoleType.ROLE_USER));
	    Assert.assertEquals(false,isRolePresent(result.getBody().getUserRoles(),RoleType.ROLE_ADMIN));
	}
	
	@Test
	public void testAdminLoginUserExist() throws URISyntaxException 
	{
		  HttpEntity<User> request = new HttpEntity<>(reqAdminUser);
		  
		  RestTemplate restTemplate = new RestTemplate(); 
		  final String baseUrl = "http://localhost:8080/api/admin-login"; 
		  URI uri = new URI(baseUrl);
		  
		  ResponseEntity<User> result = restTemplate.postForEntity(uri, request, User.class);
		  
		  //Verify request succeed
		  System.out.println("result.getStatusCodeValue() -- "+result.getStatusCodeValue());
		  Assert.assertEquals(200, result.getStatusCodeValue());
		//  Assert.assertEquals(true, result.getBody());
		  Assert.assertEquals(true, reqAdminUser.getPassword().equals(result.getBody().getPassword()));
		  Assert.assertEquals(true,isRolePresent(result.getBody().getUserRoles(),RoleType.ROLE_ADMIN));
		 }
	
	private boolean isRolePresent(Set<UserRoles> userRolesSet, RoleType roleAdmin) {
	    boolean isRolePresent = false;
	    for (UserRoles userRole : userRolesSet) {
	    	System.out.println("userRole.getName(),roleAdmin  -- "+userRole.getName()+" , "+roleAdmin);
	      isRolePresent = userRole.getName().equals(roleAdmin);
	      if (isRolePresent) break;
	    }
	    System.out.println("isRolePresent -- "+isRolePresent);
	    return isRolePresent;
	  }
	
	
	@Test
	public void testPostLoginInvalidCredentials() throws URISyntaxException 
	{
		HttpEntity<User> request = new HttpEntity<>(reqInvalidUser);
		
		RestTemplate restTemplate = new RestTemplate();	     
	    final String baseUrl = "http://localhost:8080/api/login";
	    URI uri = new URI(baseUrl);      
	    try
	    {
	    	restTemplate.postForEntity(uri, request, User.class);
	    	Assert.fail();
	    }	     
	    catch(HttpClientErrorException ex) 
	    {
	       System.out.println("testAdminLoginUserDoesNotExist :: Exception -- ");
	       Assert.assertEquals(404, ex.getRawStatusCode());
	    }
	}
	
	@Test
	public void testAdminLoginUserDoesNotExist() throws URISyntaxException 
	{
		  HttpEntity<User> request = new HttpEntity<>(reqNonExistingUser);
		  
		  RestTemplate restTemplate = new RestTemplate(); 
		  final String baseUrl = "http://localhost:8080/api/admin-login"; 
		  URI uri = new URI(baseUrl);
		  try
		  {
			  restTemplate.postForEntity(uri, request, User.class);
			  Assert.fail();  
		  }
		  catch(HttpClientErrorException ex) 
		    {
		       System.out.println("testAdminLoginUserDoesNotExist :: Exception -- ");
		       Assert.assertEquals(404, ex.getRawStatusCode());
		    }
		  
		 }
	
}
