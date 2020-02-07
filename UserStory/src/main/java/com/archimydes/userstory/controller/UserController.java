package com.archimydes.userstory.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.archimydes.userstory.domain.LoginUserDetails;
import com.archimydes.userstory.domain.User;
import com.archimydes.userstory.domain.UserRoles;
import com.archimydes.userstory.domain.UserStory;
import com.archimydes.userstory.domain.types.RoleType;
import com.archimydes.userstory.repository.UserRepository;
import com.archimydes.userstory.repository.UserStoryRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@SessionAttributes({"currentUser"})
@RestController
public class UserController { 
	
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}
	
@RequestMapping(value = "/api/login", method = RequestMethod.POST)
public ResponseEntity<?> postLogin(@Valid @RequestBody User reqUser) {
	System.out.println("*** Inside postLogin() *** reqUser.getName(),reqUser.getPassword -- "+reqUser.getName()+" , "+reqUser.getPassword());
	User user = userRepository.findByName(reqUser.getName());
	if(reqUser.getPassword().equals(user.getPassword()) && isRolePresent(user.getUserRoles(),RoleType.ROLE_USER))
	{
		System.out.println("Authenticated the useer.. User role present for logged in user");
		return ResponseEntity.ok().body(user);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
}

@RequestMapping(value = "/api/admin-login", method = RequestMethod.POST)
public ResponseEntity<User> adminLogin(@Valid @RequestBody User reqUser) {
	User user = userRepository.findByName(reqUser.getName());	 
	System.out.println("*** Inside adminLogin() *** reqUser.getPassword(),user.getName(),user.getPassword() -- "+reqUser.getPassword()+" , "+user.getName()+" , "+user.getPassword());
	if(reqUser.getPassword().equals(user.getPassword()) && isRolePresent(user.getUserRoles(),RoleType.ROLE_ADMIN))
	{
		System.out.println("Authenticated the useer..Admin role present for logged in user");
		return ResponseEntity.ok().body(user);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
}

private boolean isRolePresent(Set<UserRoles> userRolesSet, RoleType roleAdmin) {
    boolean isRolePresent = false;
    for (UserRoles userRole : userRolesSet) {
    	 System.out.println("userRole -- "+userRole.getName());
      isRolePresent = userRole.getName().equals(roleAdmin);
      System.out.println("isRolePresent -- "+isRolePresent);
      if (isRolePresent) break;
    }
    return isRolePresent;
  }


@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
public String loginError(Model model) {
    System.out.println("Login attempt failed");
    model.addAttribute("error", "true");
    return "login";
}
@RequestMapping(value = "/api/logout", method = RequestMethod.GET)
public String logout(SessionStatus session) {
    SecurityContextHolder.getContext().setAuthentication(null);
    session.setComplete();
    return "redirect:/";
}

}