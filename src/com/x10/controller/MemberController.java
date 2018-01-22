package com.x10.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
import com.x10.entity.UsersAdmins;
//import com.x10.service.AdminMiddleService;
import com.x10.service.UserService;

@Controller
public class MemberController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/api/MemberController/checkEmail", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkEmail(@RequestBody UsersAdmins data){
//		 System.out.println("hello world");
		return userService.checkEmail(data);
	}
	
	
}