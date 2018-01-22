package com.x10.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.x10.dao.AdminMiddleDao;
import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
import com.x10.entity.UsersAdmins;
//import com.imake.model.CustomerModel;
import com.x10.service.AdminMiddleService;

@Service
public class AdminMiddleServiceImpl implements AdminMiddleService {
	
	@Autowired
	private AdminMiddleDao dateDao;

//	@Override
//	public List<Map<String, Object>> loadCustomer(CustomerModel model) {
//		// TODO Auto-generated method stub
//		return customerDao.loadCustomer(model);
//	}

	@Override
	public Map<String, Object> insertDate(List<Db_date> date) {
		// TODO Auto-generated method stub
//		System.out.println(date);
		
		return dateDao.insertDate(date);
	}

	@Override
	public List<Map<String, Object>> loadUserSent() {
		// TODO Auto-generated method stub
		return dateDao.loadUserSent();
	}

	@Override
	public List<Map<String, Object>> loadAdminTableUserSent(TimeSheetSent_DB data) {
		// TODO Auto-generated method stub
		return dateDao.loadAdminTableUserSent(data);
	}

	@Override
	public Map<String, Object> loadAsPDF(List<TimeSheetSent_DB> data) throws MalformedURLException, IOException, DocumentException {
		// TODO Auto-generated method stub
		
//		
		return dateDao.loadAsPDF(data);
	}

	@Override
	public Map<String, Object> AddMembers(List<UsersAdmins> data) {
		
		return dateDao.AddMembers(data);
	}

	@Override
	public List<Map<String, Object>> getMembers() {
	
		return dateDao.getMembers();
	}

	@Override
	public Map<String, Object> updateMember(UsersAdmins data) {
		// TODO Auto-generated method stub
		return dateDao.updateMember(data);
	}

	@Override
	public Map<String, Object> deleteMember(UsersAdmins data) {
		
		return dateDao.deleteMember(data);
	}

//	@Override
//	public Map<String, Object> removeCustomer(Integer id) {
//		// TODO Auto-generated method stub
//		return customerDao.removeCustomer(id);
//	}
//
//	@Override
//	public Map<String, Object> updateCustomer(CustomerModel model) {
//		// TODO Auto-generated method stub
//		return customerDao.updateCustomer(model);
//	}

}
