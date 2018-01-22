package com.x10.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x10.dao.UserDao;
import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
import com.x10.entity.TimeSheet_DB;
import com.x10.entity.UserUpdate_TB;
import com.x10.entity.UsersAdmins;
import com.x10.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dateDao;
	
	
	@Override
	public List<Map<String, Object>> loadHoliday(Db_date date) {
		// TODO Auto-generated method stub
		return dateDao.loadHoliday(date);
	}

	@Override
	public Map<String, Object> insertTimeSheet(List<TimeSheet_DB> data) {
		// TODO Auto-generated method stub
		return dateDao.insertTimeSheet(data);
	}

	@Override
	public List<Map<String, Object>> loadDataSent(TimeSheet_DB data) {
		// TODO Auto-generated method stub
		return dateDao.loadDataSent(data);
	}

	@Override
	public Map<String, Object> updateTimeSheet(List<TimeSheet_DB> data) {
		// TODO Auto-generated method stub
		return dateDao.updateTimeSheet(data);
	}

	@Override
	public Map<String, Object> insertUserUpdate(UserUpdate_TB data) {
		// TODO Auto-generated method stub
		return dateDao.insertUserUpdate(data);
	}

	@Override
	public List<Map<String, Object>> updateFinalSentTimeSheet(List<TimeSheetSent_DB> data) {
		// TODO Auto-generated method stub
		return dateDao.updateFinalSentTimeSheet(data);
	}
	
	@Override
	public Map<String, Object> insertFinalSentTimeSheet(List<TimeSheetSent_DB> data) {
		return dateDao.insertFinalSentTimeSheet(data);
	}

	@Override
	public Map<String, Object> checkEmail(UsersAdmins data) {
		
		return dateDao.checkEmail(data);
	}


}
