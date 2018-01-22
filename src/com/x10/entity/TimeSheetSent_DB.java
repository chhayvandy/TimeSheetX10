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
@Table(name="TimeSheetSent_DB")
public class TimeSheetSent_DB implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="Username")
	private String Username;

	@Column(name="Email")
	private String Email;
	
	
	@Column(name="Date")
	private Date Date;
	
	@Column(name="TimeIn")
	private String TimeIn;
	
	@Column(name="TimeOut")
	private String TimeOut;
	
	@Column(name="hours")
	private Integer Hours;
	
	@Column(name="BreakTime")
	private Integer BreakTime;
	
	@Column(name="project")
	private String Project;
	
	@Column(name="descript")
	private String Descript;
	
	@Column(name="Features")
	private String Features;
	
	@Column(name="Activity")
	private String Activity;

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

	public Date getDate() {
		return Date;
	}

	public void setDate(Date Date) {
		this.Date = Date;
	}

	public String getTimeIn() {
		return TimeIn;
	}

	public void setTimeIn(String TimeIn) {
		this.TimeIn = TimeIn;
	}

	public String getTimeOut() {
		return TimeOut;
	}

	public void setTimeOut(String TimeOut) {
		this.TimeOut = TimeOut;
	}

	public Integer gethours() {
		return Hours;
	}

	public void sethours(Integer Hours) {
		this.Hours = Hours;
	}

	public Integer getBreakTime() {
		return BreakTime;
	}

	public void setBreakTime(Integer BreakTime) {
		this.BreakTime = BreakTime;
	}

	public String getproject() {
		return Project;
	}

	public void setproject(String Project) {
		this.Project = Project;
	}

	public String getdescript() {
		return Descript;
	}

	public void setdescript(String Descript) {
		this.Descript = Descript;
	}

	public String getFeatures() {
		return Features;
	}

	public void setFeatures(String Features) {
		this.Features = Features;
	}
	public String getActivity() {
		return Activity;
	}

	public void setActivity(String Activity) {
		this.Activity = Activity;
	}

}
