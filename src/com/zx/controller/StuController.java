package com.zx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stu")
public class StuController {
	
	@RequestMapping("a")
	public String a(String userName,String password){
		System.out.println(userName);
		System.out.println(password);
		return "cla/a";
	}
}
