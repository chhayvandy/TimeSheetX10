package com.x10.dao;

import java.util.List;
import java.util.Map;
import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
import com.x10.entity.TimeSheet_DB;
import com.x10.entity.UserUpdate_TB;
import com.x10.entity.UsersAdmins;

//import com.imake.model.CustomerModel;

public interface UserDao {

	List<Map<String, Object>> loadHoliday(Db_date date);

	Map<String, Object> insertTimeSheet(List<TimeSheet_DB> data);

	List<Map<String, Object>> loadDataSent(TimeSheet_DB data);

	Map<String, Object> updateTimeSheet(List<TimeSheet_DB> data);

	Map<String, Object> insertUserUpdate(UserUpdate_TB data);

	List<Map<String, Object>> updateFinalSentTimeSheet(List<TimeSheetSent_DB> data);
	
	Map<String, Object> insertFinalSentTimeSheet(List<TimeSheetSent_DB> data);

	Map<String, Object> checkEmail(UsersAdmins data);

//	Map<String, Object> removeCustomer(Integer id);
//
//	Map<String, Object> updateCustomer(CustomerModel model);

}