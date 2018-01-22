package com.x10.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.io.IOUtils;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.x10.entity.Db_date;
import com.x10.entity.TimeSheetSent_DB;
import com.x10.entity.UsersAdmins;
import com.x10.service.AdminMiddleService;



@Controller
public class AdminMiddleController {

	@Autowired
	private AdminMiddleService adminmiddleService;
	
	@RequestMapping(value="/api/AdminMiddleController/loadUserSent", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> loadUserSent(){
		
		return adminmiddleService.loadUserSent();
	}
	
	@RequestMapping(value="/api/AdminMiddleController/insertDate", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> insertDate(@RequestBody List<Db_date> date){
			System.out.println(date.get(0).getDate().getHours());
			System.out.println(date.get(0).getDate().getDate());
		return adminmiddleService.insertDate(date);
	}
	@RequestMapping(value="/api/AdminMiddleController/loadAdminTableUserSent", method=RequestMethod.POST)
	public @ResponseBody List<Map<String , Object>> loadAdminTableUserSent(@RequestBody TimeSheetSent_DB data){
		
		return adminmiddleService.loadAdminTableUserSent(data);
	}
	@RequestMapping(value="/api/AdminMiddleController/loadAsPDF", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> loadAsPDF(@RequestBody List<TimeSheetSent_DB> data) throws MalformedURLException, IOException, DocumentException{
		
//		testItext1.mainClass();
		
		return adminmiddleService.loadAsPDF(data);
	}
	
	@RequestMapping(value="/api/AdminMiddleController/AddMembers", method=RequestMethod.POST)
	public @ResponseBody Map<String , Object> AddMembers(@RequestBody List<UsersAdmins> data){
		
		return adminmiddleService.AddMembers(data);
	}
	
	@RequestMapping(value="/api/AdminMiddleController/getMembers", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getMembers(){
		return adminmiddleService.getMembers();
	}
	
	@RequestMapping(value="/api/AdminMiddleController/updateMember", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateMember(@RequestBody UsersAdmins data){

		return adminmiddleService.updateMember(data);
	}
	
	@RequestMapping(value="/api/AdminMiddleController/deleteMember", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteMember(@RequestBody UsersAdmins data){

		return adminmiddleService.deleteMember(data);
	}
	
	
	@RequestMapping(value="/api/AdminMiddleController/digitalSignPDF{fileName}",  method=RequestMethod.GET)
	public void digitalSignPDF(@PathVariable("fileName") String fileName ,HttpServletRequest request,HttpServletResponse response)
	{
		FileInputStream inputStream = null;
		BufferedInputStream bInputStream = null;
		
		BufferedOutputStream bOutputStream = null;
//		System.out.println("body :" +  data);
			try {
//				String fileName = request.getParameter("fileName");
				response.setContentType("application/pdf");
//				response.setHeader("Content-Disposition", "attachment; filename=\"" + "download_result" + ".pdf" + "\"");
//				System.out.println(System.getProperty("user.home"));
				File output = new File(System.getProperty("user.home") + "/Downloads/Timesheet/"+ fileName+".pdf");
//				File output = new File("/Users/X10/Downloads/Timesheet/"+data+".jpg");
				//output.set("Content-Type", "application/pdf");
			    //ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + naam + ".pdf" + "\"");
				
		        
				inputStream = new FileInputStream(output);
				bInputStream = new BufferedInputStream(inputStream);
				//ByteArrayOutputStream bArrOutputStream = new ByteArrayOutputStream();
				//ObjectOutputStream bObjOutputStream = new ObjectOutputStream(bArrOutputStream);				
				bOutputStream = new BufferedOutputStream(response.getOutputStream());
				
				byte[] b = new byte[1024];
				while(bInputStream.read(b) != -1 ){
					bOutputStream.write(b);
					//System.out.println(new String(b));
				}
				bOutputStream.flush();
				
				
				//IOUtils.copy(inputStream, response.getOutputStream());
				
//				File file = new File(System.getProperty("user.home")+"/Downloads/Timesheet/"+"vandy"+".jpg");
//
//	            FileInputStream inputStream = new FileInputStream(file);
//	            IOUtils.copy(inputStream, response.getOutputStream());
//				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Expection :" +e );	
			}finally{
				
					try {
						if(bOutputStream != null)bOutputStream.close();
						if(bInputStream != null)bInputStream.close();
						if(inputStream != null)inputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} 
		
	}
	
	


