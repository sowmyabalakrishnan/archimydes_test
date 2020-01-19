package com.archimydes.userstory.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class LoginController {
	
	// Login form  
    @RequestMapping("/login.html")  
    public String login1() {  
        return "login.html";  
    }  
	/*
	 * // Login form with error
	 * 
	 * @RequestMapping("/login-error.html") public String loginError(Model model) {
	 * model.addAttribute("loginError", true); return "login.html"; }
	 */
    
	// Login form  
    @RequestMapping("/login")  
    public String login2() {  
        return "login.html";  
    } 
    
	// Login form  
    @RequestMapping("/admin-login")  
    public String login() {  
        return "admin-login.html";  
    } 

}
