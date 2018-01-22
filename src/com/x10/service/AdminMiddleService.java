package com.x10.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.DocumentException;
//import com.imake.model.CustomerModel;
import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
import com.x10.entity.UsersAdmins;

public interface AdminMiddleService{

//	List<Map<String, Object>> loadCustomer(CustomerModel model);

	Map<String, Object> insertDate(List<Db_date> date);

	List<Map<String, Object>> loadUserSent();
	List<Map<String, Object>> loadAdminTableUserSent(TimeSheetSent_DB data);

	Map<String, Object> loadAsPDF(List<TimeSheetSent_DB> data) throws MalformedURLException, IOException, DocumentException;

	Map<String, Object> AddMembers(List<UsersAdmins> data);

	List<Map<String, Object>> getMembers();

	Map<String, Object> updateMember(UsersAdmins data);

	Map<String, Object> deleteMember(UsersAdmins data);

//	Map<String, Object> removeCustomer(Integer id);
//
//	Map<String, Object> updateCustomer(CustomerModel model);

}

