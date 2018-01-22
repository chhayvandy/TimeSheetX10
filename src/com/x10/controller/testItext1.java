package com.x10.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.x10.entity.TimeSheetSent_DB;

public class testItext1 {
	
	public  String mainClass(List<Map<String, Object>> list,List<TimeSheetSent_DB> data)
	{
//	    Document document = new Document(PageSize.A4, 30, 30, 60, 40);
	    Document document = new Document(PageSize.A4, 30, 30, 20, 40);
	   
//		String home = System.getProperty("user.home");
		
		

	       String path = System.getProperty("user.home");
	       File dir=new File(path+"/Downloads/Timesheet");
	       if(dir.exists()){
//	           System.out.println("A folder with name 'new folder' is already exist in the path "+path);
	       }else{
	           dir.mkdir();
	       }
		
	       String[] month_name = {"January",      
	    		   "February",
	    		   "March",        
	    		   "April",        
	    		   "May",          
	    		   "June",         
	    		   "July",         
	    		   "August",       
	    		   "September",    
	    		   "October",      
	    		   "November",     
	    		   "December"};   
		int month = data.get(0).getDate().getMonth()+1;
		int year  = data.get(0).getDate().getYear()+1900;
		String fileName = data.get(0).getUsername()+"_"+month+"-"+year;
		File file = new File(path+"/Downloads/Timesheet/" + fileName + ".pdf");
	
		try{
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			
		        Image img;
				try {
					img = Image.getInstance("/Users/vandychhay/Documents/workspace/testTS1/WebContent/resources/images/X10.png");
//					 img.setAbsolutePosition(10f, 10f);
					 img.getLeft(225f);
					 img.setAlignment(Image.ALIGN_CENTER);
					 
					 img.scaleAbsolute(100, 50);
					 document.add(img);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
			
			PdfPTable table = new PdfPTable(7);
			PdfPTable table_timesheet = new PdfPTable(1);
//			table_timesheet.
			table_timesheet.setWidthPercentage(105);
			table_timesheet.setSpacingBefore(10f);
			table_timesheet.setSpacingAfter(0f);
			
			float[] colWidth_upper = {11f};
			table_timesheet.setWidths(colWidth_upper);
			PdfPCell h1 = new PdfPCell(new Paragraph("TIMESHEET"));
			PdfPCell h2 = new PdfPCell(new Paragraph("NAME : "+data.get(0).getUsername()));
			PdfPCell h3 = new PdfPCell(new Paragraph("Month-Year : "+month_name[month-1]+"-"+year));
			h1.setHorizontalAlignment(Element.ALIGN_CENTER);
			h2.setHorizontalAlignment(Element.ALIGN_LEFT);
			h3.setHorizontalAlignment(Element.ALIGN_LEFT);
			h1.setFixedHeight(20f);
			table_timesheet.addCell(h1).setBackgroundColor(BaseColor.ORANGE);
			table_timesheet.addCell(h2).setFixedHeight(20f);
			table_timesheet.addCell(h3).setFixedHeight(20f);
			document.add(table_timesheet);

			
			table.setWidthPercentage(105);
			table.setSpacingBefore(0f);
			table.setSpacingAfter(0f);
			
			float[] colWidth= {1f,1f,1f,1f,1f,3f,4f};
			
			table.setWidths(colWidth);
			PdfPCell c1 = new PdfPCell(new Paragraph("DAY",FontFactory.getFont(FontFactory.TIMES_ROMAN,10)));
			PdfPCell c2 = new PdfPCell(new Paragraph("IN",FontFactory.getFont(FontFactory.TIMES_ROMAN,10)));
			PdfPCell c3 = new PdfPCell(new Paragraph("OUT",FontFactory.getFont(FontFactory.TIMES_ROMAN,10)));
			PdfPCell c4 = new PdfPCell(new Paragraph("HOURS",FontFactory.getFont(FontFactory.TIMES_ROMAN,10)));
			PdfPCell c5 = new PdfPCell(new Paragraph("BREAK",FontFactory.getFont(FontFactory.TIMES_ROMAN,10)));
			PdfPCell c6 = new PdfPCell(new Paragraph("PROJECT",FontFactory.getFont(FontFactory.TIMES_ROMAN,10)));
			PdfPCell c7 = new PdfPCell(new Paragraph("DESCRIPTION",FontFactory.getFont(FontFactory.TIMES_ROMAN,10)));
			
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c7.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setFixedHeight(20f);
			c2.setFixedHeight(20f);
			c3.setFixedHeight(20f);
			c4.setFixedHeight(20f);
			c5.setFixedHeight(20f);
			c6.setFixedHeight(20f);
			c7.setFixedHeight(20f);
			table.addCell(c1).setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c2).setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c3).setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c4).setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c5).setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c6).setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(c7).setBackgroundColor(BaseColor.LIGHT_GRAY);
			
			document.add(table);
			
			PdfPTable table1 = new PdfPTable(7);
			table1.setWidthPercentage(105);
			table1.setSpacingBefore(0f);
			table1.setSpacingAfter(0f);
			table1.setWidths(colWidth);
			for(int i=1;i<=data.size();i++)
			{

				PdfPCell c44;
				PdfPCell c55;
				PdfPCell c11 = new PdfPCell(new Paragraph(""+i,FontFactory.getFont(FontFactory.TIMES_ROMAN,9)));
				PdfPCell c22 = new PdfPCell(new Paragraph(""+data.get(i-1).getTimeIn(),FontFactory.getFont(FontFactory.TIMES_ROMAN,9)));
				PdfPCell c33 = new PdfPCell(new Paragraph(""+data.get(i-1).getTimeOut(),FontFactory.getFont(FontFactory.TIMES_ROMAN,9)));
				if(data.get(i-1).gethours()!=null){
				c44 = new PdfPCell(new Paragraph(""+data.get(i-1).gethours(),FontFactory.getFont(FontFactory.TIMES_ROMAN,9)));
				c55 = new PdfPCell(new Paragraph(""+data.get(i-1).getBreakTime(),FontFactory.getFont(FontFactory.TIMES_ROMAN,9)));
				}
				else{
					c44 = new PdfPCell(new Paragraph(""));
					c55 = new PdfPCell(new Paragraph(""));
				}
				PdfPCell c66 = new PdfPCell(new Paragraph(""+data.get(i-1).getproject(),FontFactory.getFont(FontFactory.TIMES_ROMAN,9)));
				PdfPCell c77 = new PdfPCell(new Paragraph(""+data.get(i-1).getdescript(),FontFactory.getFont(FontFactory.TIMES_ROMAN,9)));
				
				c11.setFixedHeight(15f);
				c22.setFixedHeight(15f);
				c33.setFixedHeight(15f);
				c44.setFixedHeight(15f);
				c55.setFixedHeight(15f);
				c66.setFixedHeight(15f);
				c77.setFixedHeight(15f);
				
				c11.setHorizontalAlignment(Element.ALIGN_CENTER);
				c22.setHorizontalAlignment(Element.ALIGN_CENTER);
				c33.setHorizontalAlignment(Element.ALIGN_CENTER);
				c44.setHorizontalAlignment(Element.ALIGN_CENTER);
				c55.setHorizontalAlignment(Element.ALIGN_CENTER);
//				c66.setHorizontalAlignment(Element.ALIGN_CENTER);
//				c77.setHorizontalAlignment(Element.ALIGN_CENTER);
				boolean holiday = false;
				for(int k=0;k<list.size();k++){
					
					int dayHoliday  = (int) list.get(k).get("dayIn");
					if(i==dayHoliday)
					{
					
						holiday = true;
						break;
					}
				
				}
				if(holiday==true)
				{
				table1.addCell(c11).setBackgroundColor(BaseColor.RED);
				table1.addCell(c22).setBackgroundColor(BaseColor.RED);
				table1.addCell(c33).setBackgroundColor(BaseColor.RED);
				table1.addCell(c44).setBackgroundColor(BaseColor.RED);
				table1.addCell(c55).setBackgroundColor(BaseColor.RED);
				table1.addCell(c66).setBackgroundColor(BaseColor.RED);
				table1.addCell(c77).setBackgroundColor(BaseColor.RED);
				}
				else{
					table1.addCell(c11);
					table1.addCell(c22);
					table1.addCell(c33);
					table1.addCell(c44);
					table1.addCell(c55);
					table1.addCell(c66);
					table1.addCell(c77);
				}
			}
			document.add(table1);
			
			
			int totalHours = 0;
			int totalOT    = 0;
			
			int[] listHours  = new int[32];
			int hh=0;
			int[] listDays   = new int[32];
			for(int i=0;i<data.size();i++)
			{
				
				if(data.get(i).gethours()!=null){
					listDays[hh]  = i+1;
					listHours[hh] =  data.get(i).gethours();
					
					System.out.println(listDays[hh]);
					System.out.println(listHours[hh]);
					hh++;
				}
			}
			System.out.println("length: "+listDays.length);
			
			for(int i=1;i<=hh;i++)
			{
				for(int k=0;k<list.size();k++)
				{
					if(i == (int) list.get(k).get("dayIn"))
					{
						System.out.println("day : "+i);
					}
				}
			}
			
		
			
			PdfPTable table_hours = new PdfPTable(2);
			table_hours.setWidthPercentage(50);
			table_hours.setSpacingBefore(10f);
			table_hours.setSpacingAfter(0f);
			
			float[] colWidth_bottom = {3f,2f};
			table_hours.setWidths(colWidth_bottom);
			PdfPCell b1 = new PdfPCell(new Paragraph("Total Work Hours"));
			PdfPCell b2 = new PdfPCell(new Paragraph(""+totalHours));
			
			b1.setHorizontalAlignment(Element.ALIGN_CENTER);
			b2.setHorizontalAlignment(Element.ALIGN_CENTER);
		
			b1.setFixedHeight(20f);
			b2.setFixedHeight(20f);
			table_hours.addCell(b1);
			table_hours.addCell(b2);
			
			PdfPTable table_OT = new PdfPTable(2);
			table_OT.setWidthPercentage(50);
			table_OT.setSpacingBefore(0f);
			table_OT.setSpacingAfter(0f);
			
//			float[] colWidth_bottom = {3f,2f};
			table_OT.setWidths(colWidth_bottom);
			PdfPCell b11 = new PdfPCell(new Paragraph("Total OT Hours"));
			PdfPCell b22 = new PdfPCell(new Paragraph(""+totalOT));
			
			b11.setHorizontalAlignment(Element.ALIGN_CENTER);
			b22.setHorizontalAlignment(Element.ALIGN_CENTER);
		
			b11.setFixedHeight(20f);
			b22.setFixedHeight(20f);
			table_OT.addCell(b11);
			table_OT.addCell(b22);
			table_OT.setHorizontalAlignment(Element.ALIGN_LEFT);
			table_hours.setHorizontalAlignment(Element.ALIGN_LEFT);
			document.add(table_hours);
			document.add(table_OT);
			
			
			document.add(new Paragraph("             Employee's Signature                                                     "
					+ "                               Signature"));
			
			
			document.add(new Paragraph("                    "+data.get(0).getUsername().toLowerCase()));
			
			
			document.close();
			writer.close();
			
//			ConvertPDFtoImage img = new ConvertPDFtoImage();
//			img.convert(fileName);
		}catch(DocumentException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return fileName;
		
	}
}
