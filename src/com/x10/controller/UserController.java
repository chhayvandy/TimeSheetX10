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
import com.x10.entity.TimeSheet_DB;
import com.x10.entity.UserUpdate_TB;
//import com.x10.service.AdminMiddleService;
import com.x10.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/api/UserController/loadHoliday", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> loadHoliday(@RequestBody Db_date date){
		 System.out.println("hello world");
		return userService.loadHoliday(date);
	}
	
	@RequestMapping(value="/api/UserController/loadDataSent", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> loadDataSent(@RequestBody TimeSheet_DB data){
		 System.out.println("hello world");
		return userService.loadDataSent(data);
	}
	
	@RequestMapping(value="/api/UserController/insertTimeSheet", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> insertTimeSheet(@RequestBody List<TimeSheet_DB> data){
		return userService.insertTimeSheet(data);
	}
	@RequestMapping(value="/api/UserController/updateTimeSheet", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> updateTimeSheet(@RequestBody List<TimeSheet_DB> data){
		return userService.updateTimeSheet(data);
	}
	
	@RequestMapping(value="/api/UserController/insertUserUpdate", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> InsertUserUpdate(@RequestBody UserUpdate_TB data){
		return userService.insertUserUpdate(data);
	}
	
	@RequestMapping(value="/api/UserController/updateFinalSentTimeSheet", method=RequestMethod.POST)
	public @ResponseBody List<Map<String , Object>> updateFinalSentTimeSheet(@RequestBody List<TimeSheetSent_DB> data){
		return  userService.updateFinalSentTimeSheet(data);
	}
	
	@RequestMapping(value="/api/UserController/insertFinalSentTimeSheet", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> insertFinalSentTimeSheet(@RequestBody List<TimeSheetSent_DB> data){
		return userService.insertFinalSentTimeSheet(data);
	}
//	
	
//	@RequestMapping(value="/api/CustomerController/updateCustomer", method=RequestMethod.POST)
//	public @ResponseBody Map<String , Object> updateCustomer(@RequestBody CustomerModel model){
//		return customerService.updateCustomer(model);
//	}
//	
//	@RequestMapping(value="/api/CustomerController/removeCustomer/{id}", method=RequestMethod.DELETE)
//	public @ResponseBody Map<String , Object> removeCustomer(@PathVariable("id") Integer id){
//		return customerService.removeCustomer(id);
//	}
}
