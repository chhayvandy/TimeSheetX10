package com.x10.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

@Entity
@Table(name="UserAddOrUpdate")
public class UserUpdate_TB implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="id")
	private Integer id;
	
	@Column(name="username")
	private String Username;

	@Column(name="email")
	private String Email;
	
	
	@Column(name="dateInsert")
	private String DateInsert;
	
	@Column(name="timeInsert")
	private String TimeInsert;

	
	@Column(name="month_year")
	private String MonthYear;
	

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	public String getMonthYear() {
		return MonthYear;
	}
	public void setMonthYear(String MonthYear) {
		this.MonthYear = MonthYear;
	}
	
	public String getTimeInsert() {
		return TimeInsert;
	}
	public void setTimeInsert(String TimeInsert) {
		this.TimeInsert = TimeInsert;
	}
	public String getDateInsert() {
		return DateInsert;
	}
	public void setDateInsert(String DateInsert) {
		this.DateInsert = DateInsert;
	}
	


	

}
