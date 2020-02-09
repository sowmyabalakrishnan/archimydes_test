package com.archimydes.userstory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import com.archimydes.userstory.domain.User;
import com.archimydes.userstory.domain.UserRoles;
import com.archimydes.userstory.domain.types.RoleType;
import com.archimydes.userstory.repository.UserRepository;

import javax.validation.Valid;
import java.util.Set;

@RestController
public class UserController { 
	
	private UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}
	
@RequestMapping(value = "/api/login", method = RequestMethod.POST)
public ResponseEntity<?> postLogin(@RequestBody User reqUser) {
	User user = userRepository.findByName(reqUser.getName());
	if(user!=null && reqUser.getPassword().equals(user.getPassword()) && isRolePresent(user.getUserRoles(),RoleType.ROLE_USER))
	{
		return ResponseEntity.ok().body(user);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
}

@RequestMapping(value = "/api/admin-login", method = RequestMethod.POST)
public ResponseEntity<User> adminLogin(@Valid @RequestBody User reqUser) {
	User user = userRepository.findByName(reqUser.getName());	 
	if(user!=null && reqUser.getPassword().equals(user.getPassword()) && isRolePresent(user.getUserRoles(),RoleType.ROLE_ADMIN))
	{
		return ResponseEntity.ok().body(user);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
}

private boolean isRolePresent(Set<UserRoles> userRolesSet, RoleType roleAdmin) {
    boolean isRolePresent = false;
    for (UserRoles userRole : userRolesSet) {
      isRolePresent = userRole.getName().equals(roleAdmin);
      if (isRolePresent) break;
    }
    return isRolePresent;
  }


@RequestMapping(value = "/api/logout", method = RequestMethod.GET)
public String logout(SessionStatus session) {
    SecurityContextHolder.getContext().setAuthentication(null);
    session.setComplete();
    return "redirect:/";
}
//@SessionAttributes({"currentUser"})
}