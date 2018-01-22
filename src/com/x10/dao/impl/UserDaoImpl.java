package com.x10.dao.impl;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.ArrayList;
import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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

//import com.imake.entity.DbCustomer;
import com.x10.BeanUtils;
import com.x10.dao.UserDao;
import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
//import com.x10.model.CustomerModel;
import com.x10.entity.TimeSheet_DB;
import com.x10.entity.UserUpdate_TB;
import com.x10.entity.UsersAdmins;

//import io.jsonwebtoken.lang.Collections;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
    @Override
	public List<Map<String, Object>> loadHoliday(Db_date date) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("FROM  Db_date  "
					+ "WHERE YEAR(date) =:Year AND MONTH(date) =:Month");
//			query.setParameter("ID", model.getId());
//			System.out.println(model.getCustomerSince().getDate());
//			System.out.println(model.getCustomerSince().getMonth());
//			System.out.println(model.getCustomerSince().getYear()+1900);
			
			query.setParameter("Month",date.getDate().getMonth()+1);
			query.setParameter("Year", date.getDate().getYear()+1900);
			List<Db_date> dbCustomer1 = new ArrayList<Db_date>();
			dbCustomer1 = query.list();
//			System.out.println(dbCustomer1.size());
			
			  int d; 
			for(int i=0;i<dbCustomer1.size();i++){
				map = new HashMap<String, Object>(); 
			    d = dbCustomer1.get(i).getDate().getDate();
//			    System.out.println(d);
				map.put("dayIn", d);
				list.add(map);
//				System.out.println(list);
			}
//			System.out.println(list);
			
			return list;
		
	    }

	@Override
	public Map<String, Object> insertTimeSheet(List<TimeSheet_DB> data) {
		Map<String, Object> map = new HashMap<String, Object>();
		Session session = sessionFactory.openSession();
		try{
//			System.out.println(data.size());
//			System.out.println("-----------------------------------");
//				System.out.println(data.get(0).getDate());
//				System.out.println(data.get(0).getEmail());
		
				session.beginTransaction();
//				TimeSheetSent_DB db_data ;
				Query query = session.createQuery("from TimeSheet_DB where   MONTH(Date)=:Month "
						+ " AND YEAR(Date)=:Year AND Email =:Email");
				
				query.setParameter("Month",   data.get(0).getDate().getMonth()+1);
				query.setParameter("Year",    data.get(0).getDate().getYear()+1900);
				query.setParameter("Email",   data.get(0).getEmail());
//				System.out.println("data111  :"+ query.uniqueResult());
				
				
				List<TimeSheet_DB> DB_data = new ArrayList<TimeSheet_DB>();
				
				DB_data = query.list();
				
				TimeSheet_DB db_data1 = new TimeSheet_DB();
				
//				System.out.println("-----------------------------------");
//				for(int k=0;k<DB_data.size();k++)
//				{
////					System.out.println("data111  :"+ DB_data.get(k).getDate().getDate());
//				}
				int count = 0;
				
		for(int i=0;i<data.size();i++)
			{
				count = 0;
				
				for(int k=0;k<DB_data.size();k++)
				{
					if(data.get(i).getDate().getDate()==DB_data.get(k).getDate().getDate())
					{
						count++;
						break;
					}
				}
				
				
				if(count==0)
				
				{
				TimeSheet_DB db_data = new TimeSheet_DB();
				if(BeanUtils.isNotEmpty(data.get(i).getTimeIn()))
				{
					db_data.setTimeIn(data.get(i).getTimeIn());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getTimeOut()))
				{
					db_data.setTimeOut(data.get(i).getTimeOut());
				}
				if(BeanUtils.isNotEmpty(data.get(i).gethours()))
				{
					db_data.sethours(data.get(i).gethours());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getBreakTime()))
				{
					db_data.setBreakTime(data.get(i).getBreakTime());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getproject()))
				{
					db_data.setproject(data.get(i).getproject());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getdescript()))
				{
					db_data.setdescript(data.get(i).getdescript());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getFeatures()))
				{
					db_data.setFeatures(data.get(i).getFeatures());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getActivity()))
				{
					db_data.setActivity(data.get(i).getActivity());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getUsername()))
				{
					db_data.setUsername(data.get(i).getUsername());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getEmail()))
				{
					db_data.setEmail(data.get(i).getEmail());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getDate()))
				{
					db_data.setDate(data.get(i).getDate());
				}
				session.save(db_data);
//				
			}
			}
		
		session.flush();
		session.getTransaction().commit();
		map.put("success", true);
		map.put("msg", "New Customer Added");
			}
		catch (Exception e){
				map.put("success", false);
				map.put("msg", "Insert fail");
				e.printStackTrace();
			
		}
	
		session.close();
		
		return map;
	}

	@Override
	public List<Map<String, Object>> loadDataSent(TimeSheet_DB data) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
			
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			
			Query query = session.createQuery("FROM  TimeSheet_DB  "
					+ "WHERE YEAR(Date) =:Year AND MONTH(Date) =:Month AND Email =:Email");
			
//			
//			System.out.println(data.getEmail());
//			System.out.println(data.getDate().getMonth()+1);
//			System.out.println(data.getDate().getYear()+1900);
			
			
			query.setParameter("Month",data.getDate().getMonth()+1);
			query.setParameter("Email",data.getEmail());
			query.setParameter("Year", data.getDate().getYear()+1900);
			List<TimeSheet_DB> dbCustomer1 = new ArrayList<TimeSheet_DB>();
			dbCustomer1 = query.list();
//			System.out.println(dbCustomer1.size());
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

	@Override
	public Map<String, Object> updateTimeSheet(List<TimeSheet_DB> data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();
//			System.out.println(data.size());
			for(int i=0;i<data.size();i++){
				
//				System.out.println(data.get(i).getDate());
		       
			try{
				session.beginTransaction();
				TimeSheet_DB db_data1 = new TimeSheet_DB();
				
				
				Query query = session.createQuery("from TimeSheet_DB where  DAY(Date) =:Day AND MONTH(Date)=:Month "
						+ " AND YEAR(Date)=:Year AND Email =:Email");
				
//				System.out.println("date  :"+data.get(i).getDate().getDate());
//				System.out.println("Email  :"+data.get(i).getEmail());
				query.setParameter("Day",  data.get(i).getDate().getDate());
				query.setParameter("Month",  data.get(i).getDate().getMonth()+1);
				query.setParameter("Year",  data.get(i).getDate().getYear()+1900);
				query.setParameter("Email", data.get(i).getEmail());
//				System.out.println("data111  :"+ query.uniqueResult());
				db_data1 =(TimeSheet_DB) query.uniqueResult();
				
				
				if(BeanUtils.isNotEmpty(data.get(i).getTimeIn())){
					db_data1.setTimeIn(data.get(i).getTimeIn());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getTimeOut())){
					db_data1.setTimeOut(data.get(i).getTimeOut());
				}
				if(BeanUtils.isNotEmpty(data.get(i).gethours())){
					db_data1.sethours(data.get(i).gethours());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getBreakTime())){
					db_data1.setBreakTime(data.get(i).getBreakTime());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getproject())){
					db_data1.setproject(data.get(i).getproject());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getdescript())){
					db_data1.setdescript(data.get(i).getdescript());
				}
				if(BeanUtils.isNotEmpty(data.get(i).getActivity())){
					db_data1.setActivity(data.get(i).getActivity());
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

			
			}
			
			session.close();
		
//		}
		
		
		return map;
	}

	@Override
	public Map<String, Object> insertUserUpdate(UserUpdate_TB data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();
		Date date1 = new Date();

			try{
				UserUpdate_TB db_data = new UserUpdate_TB();
				
				session.beginTransaction();
				
				if(BeanUtils.isNotEmpty(data.getEmail()))
				{
					db_data.setEmail(data.getEmail());
				}
				if(BeanUtils.isNotEmpty(data.getDateInsert()))
				{
					db_data.setDateInsert(data.getDateInsert());
				}
				if(BeanUtils.isNotEmpty(data.getUsername()))
				{
					db_data.setUsername(data.getUsername());
				}
				if(BeanUtils.isNotEmpty(data.getTimeInsert()))
				{
					db_data.setTimeInsert(data.getTimeInsert());
				}
				if(BeanUtils.isNotEmpty(data.getMonthYear()))
				{
					db_data.setMonthYear(data.getMonthYear());
				}
				
				session.save(db_data);
				session.flush();
				session.getTransaction().commit();
				map.put("success", true);
				map.put("msg", "New Customer Added");
			}catch (Exception e){
				map.put("success", false);
				map.put("msg", "Insert fail");
				e.printStackTrace();
			}
			
//		}
	
		session.close();
		
		return map;
		
		
	}

	@Override
	public List<Map<String, Object>> updateFinalSentTimeSheet(List<TimeSheetSent_DB> data) {
		
		Map<String, Object> map ;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Session session = sessionFactory.openSession();
		try{
//			System.out.println(data.size());
//			for(int i=0;i<data.size();i++){
//			System.out.println("-----------------------------------");
//				System.out.println(data.get(0).getDate());
//				System.out.println(data.get(0).getEmail());
		
				session.beginTransaction();
				TimeSheetSent_DB db_data1 ;
				Query query = session.createQuery("from TimeSheetSent_DB where   MONTH(Date)=:Month "
						+ " AND YEAR(Date)=:Year AND Email =:Email");
				
				query.setParameter("Month",   data.get(0).getDate().getMonth()+1);
				query.setParameter("Year",    data.get(0).getDate().getYear()+1900);
				query.setParameter("Email",   data.get(0).getEmail());
//				System.out.println("data111  :"+ query.uniqueResult());
				
				
				List<TimeSheetSent_DB> DB_data = new ArrayList<TimeSheetSent_DB>();
				
				DB_data = query.list();
//				System.out.println("-----------------------------------");
//				System.out.println("data111  :"+ DB_data);
				db_data1 = new TimeSheetSent_DB();
				if(DB_data.size()>0){
				
				for(int i=0;i<data.size();i++)
				{
					for(int k=0;k<DB_data.size();k++)
					{
						if(data.get(i).getDate().getDate()==DB_data.get(k).getDate().getDate())
						{
							map = new HashMap<String, Object>();
							db_data1 = DB_data.get(k);
//							System.out.println("vandy");
							
							if(BeanUtils.isNotEmpty(data.get(i).getTimeIn())){
								db_data1.setTimeIn(data.get(i).getTimeIn());
							}
							if(BeanUtils.isNotEmpty(data.get(i).getTimeOut())){
								db_data1.setTimeOut(data.get(i).getTimeOut());
							}
							if(BeanUtils.isNotEmpty(data.get(i).gethours())){
								db_data1.sethours(data.get(i).gethours());
							}
							if(BeanUtils.isNotEmpty(data.get(i).getBreakTime())){
								db_data1.setBreakTime(data.get(i).getBreakTime());
							}
							if(BeanUtils.isNotEmpty(data.get(i).getproject())){
								db_data1.setproject(data.get(i).getproject());
							}
							if(BeanUtils.isNotEmpty(data.get(i).getdescript())){
								db_data1.setdescript(data.get(i).getdescript());
							}
							if(BeanUtils.isNotEmpty(data.get(i).getActivity())){
								db_data1.setActivity(data.get(i).getActivity());
							}
							if(BeanUtils.isNotEmpty(data.get(i).getFeatures())){
								db_data1.setFeatures(data.get(i).getFeatures());
							}
							map.put("day", data.get(i).getDate().getDate());
							list.add(map);
						}
							
						}
					
					}
//				System.out.println(list);
				
				session.update(db_data1);
				session.flush();
				session.getTransaction().commit();
				
				}
			}catch (Exception e){
				
				e.printStackTrace();
			}
			session.close();
		
		return list;
		
			}

	@Override
	public Map<String, Object> insertFinalSentTimeSheet(List<TimeSheetSent_DB> data) {
		

		Map<String, Object> map = new HashMap<String, Object>();
		
		Session session = sessionFactory.openSession();
		try{
//			System.out.println(data.size());
//			for(int i=0;i<data.size();i++){
//			System.out.println("-----------------------------------");
//				System.out.println(data.get(0).getDate());
//				System.out.println(data.get(0).getEmail());
		
				session.beginTransaction();
//				TimeSheetSent_DB db_data ;
				Query query = session.createQuery("from TimeSheetSent_DB where   MONTH(Date)=:Month "
						+ " AND YEAR(Date)=:Year AND Email =:Email");
				
				query.setParameter("Month",   data.get(0).getDate().getMonth()+1);
				query.setParameter("Year",    data.get(0).getDate().getYear()+1900);
				query.setParameter("Email",   data.get(0).getEmail());
//				System.out.println("data111  :"+ query.uniqueResult());
				
				
				List<TimeSheetSent_DB> DB_data = new ArrayList<TimeSheetSent_DB>();
				
				DB_data = query.list();
//				System.out.println("-----------------------------------");
//				System.out.println("data111  :"+ DB_data);
				
				TimeSheetSent_DB db_data = new TimeSheetSent_DB();
				
//				System.out.println("size : "+DB_data.size());
//				if(DB_data.size()>0){
				for(int i=0;i<data.size();i++)
				{  
					int count = 0 ;
					for(int k=0;k<DB_data.size();k++)
					{
						if(data.get(i).getDate().getDate()==DB_data.get(k).getDate().getDate())
						{
							count ++;
						}
					}
					if(count==0)
					{
					
					db_data = data.get(i);
//					session.beginTransaction();
					
					if(BeanUtils.isNotEmpty(data.get(i).getTimeIn()))
					{
						db_data.setTimeIn(data.get(i).getTimeIn());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getTimeOut()))
					{
						db_data.setTimeOut(data.get(i).getTimeOut());
					}
					if(BeanUtils.isNotEmpty(data.get(i).gethours()))
					{
						db_data.sethours(data.get(i).gethours());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getBreakTime()))
					{
						db_data.setBreakTime(data.get(i).getBreakTime());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getproject()))
					{
						db_data.setproject(data.get(i).getproject());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getdescript()))
					{
						db_data.setdescript(data.get(i).getdescript());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getFeatures()))
					{
						db_data.setFeatures(data.get(i).getFeatures());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getActivity()))
					{
						db_data.setActivity(data.get(i).getActivity());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getUsername()))
					{
						db_data.setUsername(data.get(i).getUsername());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getEmail()))
					{
						db_data.setEmail(data.get(i).getEmail());
					}
					if(BeanUtils.isNotEmpty(data.get(i).getDate()))
					{
						db_data.setDate(data.get(i).getDate());
					}
					
					session.save(db_data);
					session.flush();
					
					}
				}

				session.getTransaction().commit();
				map.put("success", true);
				map.put("msg", "New Customer Added");
				
				
			}catch (Exception e){
				map.put("success", false);
				map.put("msg", "Insert fail");
				e.printStackTrace();
			}
			session.close();
		
		return map;
		
	}

	@Override
	public Map<String, Object> checkEmail(UsersAdmins data) {
		
		Map<String, Object> map = null;
		
		Session session = sessionFactory.openSession();
		try{
				session.beginTransaction();
//				
				Query query = session.createQuery("from UsersAdmins ");
//				System.out.println("----------emafkldaf-------------");
//				System.out.println(data.getEmail());
				
				List<UsersAdmins> data_db = new ArrayList<UsersAdmins>();
				data_db = query.list();
//				System.out.println(data_db.get(0).getEmail());
				
				int count =0;
//				String Position = null;
				
				for(int i=0;i<data_db.size();i++)
				{
					if(data.getEmail().equals(data_db.get(i).getEmail()))
					{ 
//						System.out.println("have");
						map = new HashMap<String, Object>();
//						System.out.println(data_db.get(i).getPosition());
						map.put("position", data_db.get(i).getPosition());
//						System.out.println(map);
						break;
					}
					else
					{
						count++;
					}
				}
				if(count==data_db.size())
				{ 
//					System.out.println("no");
					map = new HashMap<String, Object>();
					map.put("position", "No");
				}
			}catch (Exception e){
//				map = new HashMap<String, Object>();
//				System.out.println("no!");
			
				e.printStackTrace();
			}
			session.close();
//			System.out.println(map);
		
		return map;
	}
	
}