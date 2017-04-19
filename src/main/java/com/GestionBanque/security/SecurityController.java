package com.GestionBanque.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/login")
	public String  login ()
	{return  "login" ;}
	@RequestMapping("/")
	public String  home ()
	{return  "redirect:/operations" ;}
	@RequestMapping("/accessDeniedPage")
	public String accessDenied()
	{return "accessDenied";}
}
