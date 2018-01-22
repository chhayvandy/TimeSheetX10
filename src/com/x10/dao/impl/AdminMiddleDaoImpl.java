package com.x10.dao.impl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.itextpdf.text.DocumentException;

//import com.imake.BeanUtils;
//import com.imake.dao.CustomerDao;
//import com.imake.entity.DbCustomer;
//import com.imake.model.CustomerModel;

import com.x10.BeanUtils;
import com.x10.controller.ConvertPDFtoImage;
import com.x10.controller.testItext1;
import com.x10.dao.AdminMiddleDao;
import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
import com.x10.entity.TimeSheet_DB;
import com.x10.entity.UserUpdate_TB;
import com.x10.entity.UsersAdmins;

@Repository
public class AdminMiddleDaoImpl implements AdminMiddleDao {
	
	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public Map<String, Object> insertDate( List<Db_date> date) {
		Map<String, Object> map = new HashMap<String, Object>();
//		System.out.println(date.size());
		
		Session session = sessionFactory.openSession();
		Date date1 = new Date();
		
		for(int i=0;i<date.size();i++){
			
//			System.out.println(date.get(i).getDate());
			
		if(date.get(i).getDate().equals("")){
			map.put("success", false);
			map.put("msg", "Customer Code can't be null.");
		}
		else if(date.get(i).getDescript().equals("")){
			map.put("success", false);
			map.put("msg", "FirstName can't be null.");
		}
		else{
			try{
				Db_date dbDate = new Db_date();
				session.beginTransaction();
				
				if(BeanUtils.isNotEmpty(date.get(i).getDate()))
				{
					
					if(date.get(i).getDate().getHours()>0&& date.get(i).getDate().getHours()<7){
						
						int day = date.get(i).getDate().getDate()-1;
						int month = date.get(i).getDate().getMonth()+1;
						int year = date.get(i).getDate().getYear()+1900;
						
						String startDateString = year+"-"+month+"-"+day;
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
						Date   startDate = df.parse(startDateString);
						dbDate.setDate(startDate);
					}
					else if(date.get(i).getDate().getHours()== 0 && date.get(i).getDate().getMinutes()>0){
						
						int day = date.get(i).getDate().getDate()-1;
						int month = date.get(i).getDate().getMonth()+1;
						int year = date.get(i).getDate().getYear()+1900;
						
						String startDateString = year+"-"+month+"-"+day;
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
						Date   startDate = df.parse(startDateString);
						dbDate.setDate(startDate);
					}
					else{
						dbDate.setDate((date.get(i).getDate()));
					}
					
				}
				if(BeanUtils.isNotEmpty(date.get(i).getDescript()))
				{
					dbDate.setDescript(date.get(i).getDescript());
				}

				session.save(dbDate);
				session.flush();
				session.getTransaction().commit();
				map.put("success", true);
				map.put("msg", "New Customer Added");
			}catch (Exception e){
				map.put("success", false);
				map.put("msg", "Insert fail");
				e.printStackTrace();
			}
			
		}
	}
		session.close();
		
		return map;

	}
	@Override
	public List<Map<String, Object>> loadUserSent() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("FROM  UserUpdate_TB");

			List<UserUpdate_TB> dbCustomer1 = new ArrayList<UserUpdate_TB>();
			dbCustomer1 = query.list();
			  int id;
			  String Username;
			  String Email ;
			  String TimeInsert;
			  String DateInsert;
			  String MonthYear;
			  
			  
			for(int i=0;i<dbCustomer1.size();i++){
				
				map = new HashMap<String, Object>();
				id      = dbCustomer1.get(i).getId();
			    Username = dbCustomer1.get(i).getUsername();
			    Email    = dbCustomer1.get(i).getEmail();
			    TimeInsert = dbCustomer1.get(i).getTimeInsert();
			    DateInsert = dbCustomer1.get(i).getDateInsert();
			    MonthYear = dbCustomer1.get(i).getMonthYear();
//			    System.out.println(d);
			    map.put("id", id);
				map.put("Username", Username);
				map.put("Email", Email);
				map.put("TimeInsert", TimeInsert);
				map.put("DateInsert", DateInsert);
				map.put("MonthYear", MonthYear);
				list.add(map);
				Collections.sort(list, new Comparator<Map<String, Object>> () {
			         public int compare(Map<String, Object> m1, Map<String, Object> m2) {
//			             return ((Integer) m1.get("day")).compareTo((Integer) m2.get("day")); //ascending order
			             return ((Integer) m2.get("id")).compareTo((Integer) m1.get("id")); //descending order
			         }
			     });
				
				
			}
//			System.out.println("----------------------------------list");
//			System.out.println(list);
			return list;
	}
	@SuppressWarnings("deprecation")
	@Override
	public List<Map<String, Object>> loadAdminTableUserSent(TimeSheetSent_DB data) {
		
//		System.out.println(data.getDate().getMonth()+1);
//		System.out.println(data.getDate().getYear()+1900);
//		System.out.println(data.getEmail());
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("FROM  TimeSheetSent_DB  "
					+ "WHERE YEAR(Date) =:Year AND MONTH(Date) =:Month AND Email =:Email");

			query.setParameter("Month",   data.getDate().getMonth()+1);
			query.setParameter("Year",    data.getDate().getYear()+1900);
			query.setParameter("Email",   data.getEmail());
			

			List<TimeSheetSent_DB> dbCustomer1 = new ArrayList<TimeSheetSent_DB>();
			dbCustomer1 = query.list();
			
//			System.out.println(dbCustomer1.size());
//			System.out.println(dbCustomer1.get(0).getDate().getDate());
			
			int day;
			String TimeIn;
			String TimeOut;
			int hours;
			int BreakTime;
			String project;
			String descript;
			String Features;
			for(int i=0;i<dbCustomer1.size();i++){
				
				map = new HashMap<String, Object>();
				day    = dbCustomer1.get(i).getDate().getDate();
				TimeIn = dbCustomer1.get(i).getTimeIn();
				TimeOut = dbCustomer1.get(i).getTimeOut();
			    hours = dbCustomer1.get(i).gethours();
				BreakTime = dbCustomer1.get(i).getBreakTime();
				project = dbCustomer1.get(i).getproject();
				descript = dbCustomer1.get(i).getdescript();
				Features = dbCustomer1.get(i).getFeatures();
				
				map.put("day", day);
				map.put("TimeIn", TimeIn);
				map.put("TimeOut", TimeOut);
				map.put("hours", hours);
				map.put("BreakTime", BreakTime);
				map.put("project", project);
				map.put("descript", descript);
				map.put("Features", Features);
				list.add(map);
				
			     Collections.sort(list, new Comparator<Map<String, Object>> () {
			         public int compare(Map<String, Object> m1, Map<String, Object> m2) {
			             return ((Integer) m1.get("day")).compareTo((Integer) m2.get("day")); //ascending order
			             //return ((Integer) m2.get("num")).compareTo((Integer) m1.get("num")); //descending order
			         }
			     });
			}
//			System.out.println(list);
			return list;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> loadAsPDF(List<TimeSheetSent_DB> data) throws MalformedURLException, IOException, DocumentException {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = null;
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM  Db_date  "
					+ "WHERE YEAR(date) =:Year AND MONTH(date) =:Month");
			query.setParameter("Month",data.get(0).getDate().getMonth()+1);
			query.setParameter("Year", data.get(0).getDate().getYear()+1900);
			List<Db_date> dbCustomer1 = new ArrayList<Db_date>();
			dbCustomer1 = query.list();
			  int d; 
//			  System.out.println("-------holiday----------------");
			for(int i=0;i<dbCustomer1.size();i++){
				map = new HashMap<String, Object>(); 
			    d = dbCustomer1.get(i).getDate().getDate();
//			    System.out.println(d);
				map.put("dayIn", d);
				list.add(map);
//				System.out.println(list);
			}
			
			int year = data.get(0).getDate().getYear()+1900;
			int month = data.get(0).getDate().getMonth()+1;
			
			Calendar calendar = Calendar.getInstance();
		    // Note that month is 0-based in calendar, bizarrely.
		    calendar.set(year, month - 1, 1);
		    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		    for (int day = 1; day <= daysInMonth; day++) {
		    	map = new HashMap<String, Object>();
		        calendar.set(year, month - 1, day);
		        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		        if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
//		        	System.out.println(day);
		        	map.put("dayIn", day);
		        	
		            list.add(map);
		        }
		    }
//		    System.out.println("-----------fdsafads------------");
		    testItext1 a = new testItext1();
		   String fileName = a.mainClass(list,data);
//		   ConvertPDFtoImage img = new ConvertPDFtoImage();
//		   img.convert(fileName);
//		   System.out.println("-----------filename------------");
//		   System.out.println(fileName);
		   map = new HashMap<String, Object>();
		   map.put("fileName", fileName);
//		   System.out.println("-----------map------------");
//		   System.out.println(map);

			
			
		return map;
	}
	@Override
	public Map<String, Object> AddMembers(List<UsersAdmins> data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
//		System.out.println(date.size());
		
		Session session = sessionFactory.openSession();
		
		
		for(int i=0;i<data.size();i++){
			
//			System.out.println(data.get(i).getDate());
			
		
			try{
				UsersAdmins dataUser = new UsersAdmins();
				session.beginTransaction();
				
				if(BeanUtils.isNotEmpty(data.get(i).getUsername()))
				{
					dataUser.setUsername(data.get(i).getUsername());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getEmail()))
				{
					dataUser.setEmail(data.get(i).getEmail());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getPosition()))
				{
					dataUser.setPosition(data.get(i).getPosition());
				}
				session.save(dataUser);
				session.flush();
				session.getTransaction().commit();
				map.put("success", true);
				map.put("msg", "New Customer Added");
			}catch (Exception e){
				map.put("success", false);
				map.put("msg", "Insert fail");
				e.printStackTrace();
			}
			
		}
	
		session.close();
		
		return map;
	}
	@Override
	public List<Map<String, Object>> getMembers() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("FROM  UsersAdmins");
//			UsersAdmins dataUser = new UsersAdmins();
			List<UsersAdmins> dbCustomer1 = new ArrayList<UsersAdmins>();
			dbCustomer1 = query.list();
			  int id;
			  String Username;
			  String Email ;
			  String Position;
			  
			  
			for(int i=0;i<dbCustomer1.size();i++){
				
				map = new HashMap<String, Object>();
				id      = dbCustomer1.get(i).getId();
			    Username = dbCustomer1.get(i).getUsername();
			    Email    = dbCustomer1.get(i).getEmail();
			    Position = dbCustomer1.get(i).getPosition();
			   
//			    System.out.println(d);
			    map.put("id", id);
				map.put("username", Username);
				map.put("email", Email);
				map.put("position", Position);
				
				list.add(map);
				Collections.sort(list, new Comparator<Map<String, Object>> () {
			         public int compare(Map<String, Object> m1, Map<String, Object> m2) {
			             return ((String) m1.get("position")).compareTo((String) m2.get("position")); //ascending order
//			             return ((Integer) m2.get("id")).compareTo((Integer) m1.get("id")); //descending order
			         }
			     });
				
				
			}
//			System.out.println("----------------------------------list");
//			System.out.println(list);
			return list;
	}
	@Override
	public Map<String, Object> updateMember(UsersAdmins data) {
		
		
Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();
//			
			try{
				session.beginTransaction();
//				System.out.println("-----id--------------");
				
//				System.out.println(data.getId());
				UsersAdmins db_data1 = new UsersAdmins();
				Query query = session.createQuery("from UsersAdmins  where  id =:Id ");
				
				query.setParameter("Id",  data.getId());
//				System.out.println("data111  :"+ query.uniqueResult());
				db_data1 =(UsersAdmins) query.uniqueResult();
				if(BeanUtils.isNotEmpty(data.getId())){
					db_data1.setId(data.getId());
				}
				if(BeanUtils.isNotEmpty(data.getEmail())){
					db_data1.setEmail(data.getEmail());
				}
				if(BeanUtils.isNotEmpty(data.getUsername())){
					db_data1.setUsername(data.getUsername());
				}
				if(BeanUtils.isNotEmpty(data.getPosition())){
					db_data1.setPosition(data.getPosition());
				}
				session.update(db_data1);
				session.flush();
				session.getTransaction().commit();
				map.put("success", true);
				map.put("msg", "Customer Updated");
				
				
			
			}catch (Exception e){
				map.put("success", false);
				map.put("msg", "Update fail");
				e.printStackTrace();
			}

			session.close();
		
//		}
		
		
		return map;
		
		
	}
	@Override
	public Map<String, Object> deleteMember(UsersAdmins data) {
Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();

		try{
			UsersAdmins dbCustomer = new UsersAdmins();
			session.beginTransaction();
			dbCustomer.setId(data.getId());
			
			session.delete(dbCustomer);
			session.flush();
			session.getTransaction().commit();
			map.put("success", true);
			map.put("msg", "Customer Removed");
		
		}catch (Exception e){
			map.put("success", false);
			map.put("msg", "Remove fail");
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		
		
		return map;
	}
	

}
