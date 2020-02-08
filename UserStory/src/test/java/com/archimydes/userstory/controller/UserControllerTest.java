/**
 * 
 */
package com.archimydes.userstory.controller;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.archimydes.userstory.domain.User;
import com.archimydes.userstory.domain.UserRoles;
import com.archimydes.userstory.domain.UserStory;
import com.archimydes.userstory.domain.types.RoleType;
import com.archimydes.userstory.repository.UserRepository;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;

/**
 * @author Sowmya
 *
 */
public class UserControllerTest {
	/*
	 * @Autowired private UserRepository repository;
	 * 
	 * @Autowired private UserController userController;
	 */
	
	 User reqUser = new User();
	 User reqAdminUser = new User();
	 User reqNonExistingUser = new User();
	 UserRoles roles = new UserRoles();
	 Set<UserRoles> roleSet = new HashSet<>();
	
	@Before
	public void setUp() {		
		    reqUser.setName("testuser2");
		    reqUser.setPassword("bbb");
		    
		    reqAdminUser.setName("testuser11");
		    reqAdminUser.setPassword("aaa");
		    
		    reqNonExistingUser.setName("testuser22");
		    reqNonExistingUser.setPassword("bbb");
		    
		    roles.setId(2);
		    roles.setName(RoleType.ROLE_USER);
		   
		    roleSet.add(roles);
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
	   // Assert.assertEquals(true, result.getBody());
	    System.out.println("result.getBody().getId().intValue() -- "+result.getBody().getId().intValue());
	    Assert.assertEquals(2, result.getBody().getId().intValue());
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
		  System.out.println("result.getBody().getId().intValue() -- "+result.getBody().getId().intValue()); 
		  Assert.assertEquals(1, result.getBody().getId().intValue());
		 }
	
	@Test
	public void testPostLoginUserHasUserRole() throws URISyntaxException 
	{
		/*
		 * HttpEntity<User> request = new HttpEntity<>(reqUser);
		 * 
		 * RestTemplate restTemplate = new RestTemplate(); final String baseUrl =
		 * "http://localhost:8080/api/login"; URI uri = new URI(baseUrl);
		 * 
		 * ResponseEntity<User> result = restTemplate.postForEntity(uri, request,
		 * User.class);
		 * 
		 * //Verify request succeed
		 * System.out.println("result.getStatusCodeValue() -- "+result.
		 * getStatusCodeValue()); Assert.assertEquals(200, result.getStatusCodeValue());
		 * // Assert.assertEquals(true, result.getBody());
		 * System.out.println("result.getBody().getId().intValue() -- "+result.getBody()
		 * .getId().intValue()); Assert.assertEquals(2,
		 * result.getBody().getId().intValue());
		 * System.out.println("result.getBody().getUserRoles() -- "+result.getBody().
		 * getUserRoles().forEach(roles).); Assert.assertEquals(roleSet.,
		 * result.getBody().getUserRoles().);
		 */}	
	
	
	
	@Test
	public void testAdminLoginUserHasUserRole() throws URISyntaxException 
	{
		/*
		 * HttpEntity<User> request = new HttpEntity<>(reqUser);
		 * 
		 * RestTemplate restTemplate = new RestTemplate(); final String baseUrl =
		 * "http://localhost:8080/api/login"; URI uri = new URI(baseUrl);
		 * 
		 * ResponseEntity<User> result = restTemplate.postForEntity(uri, request,
		 * User.class);
		 * 
		 * //Verify request succeed
		 * System.out.println("result.getStatusCodeValue() -- "+result.
		 * getStatusCodeValue()); Assert.assertEquals(200, result.getStatusCodeValue());
		 * // Assert.assertEquals(true, result.getBody());
		 * System.out.println("result.getBody().getId().intValue() -- "+result.getBody()
		 * .getId().intValue()); Assert.assertEquals(2,
		 * result.getBody().getId().intValue());
		 * System.out.println("result.getBody().getUserRoles() -- "+result.getBody().
		 * getUserRoles().forEach(roles).); Assert.assertEquals(roleSet.,
		 * result.getBody().getUserRoles().);
		 */}	
	@Test
	public void testAdminLoginUserHasInsufficientRoles() throws URISyntaxException 
	{
		/*
		 * HttpEntity<User> request = new HttpEntity<>(reqUser);
		 * 
		 * RestTemplate restTemplate = new RestTemplate(); final String baseUrl =
		 * "http://localhost:8080/api/login"; URI uri = new URI(baseUrl);
		 * 
		 * ResponseEntity<User> result = restTemplate.postForEntity(uri, request,
		 * User.class);
		 * 
		 * //Verify request succeed
		 * System.out.println("result.getStatusCodeValue() -- "+result.
		 * getStatusCodeValue()); Assert.assertEquals(200, result.getStatusCodeValue());
		 * // Assert.assertEquals(true, result.getBody());
		 * System.out.println("result.getBody().getId().intValue() -- "+result.getBody()
		 * .getId().intValue()); Assert.assertEquals(2,
		 * result.getBody().getId().intValue());
		 * 
		 */}
	
	@Test
	public void testUserLoginUserInvalidCredentials() throws URISyntaxException 
	{
		/*
		 * HttpEntity<User> request = new HttpEntity<>(reqUser);
		 * 
		 * RestTemplate restTemplate = new RestTemplate(); final String baseUrl =
		 * "http://localhost:8080/api/login"; URI uri = new URI(baseUrl);
		 * 
		 * ResponseEntity<User> result = restTemplate.postForEntity(uri, request,
		 * User.class);
		 * 
		 * //Verify request succeed
		 * System.out.println("result.getStatusCodeValue() -- "+result.
		 * getStatusCodeValue()); Assert.assertEquals(200, result.getStatusCodeValue());
		 * // Assert.assertEquals(true, result.getBody());
		 * System.out.println("result.getBody().getId().intValue() -- "+result.getBody()
		 * .getId().intValue()); Assert.assertEquals(2,
		 * result.getBody().getId().intValue());
		 * 
		 */}
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
		       System.out.println("Exception -- ");
		       Assert.assertEquals(404, ex.getRawStatusCode());
		    }
		  
		 }
}
