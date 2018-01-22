package testItext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class testItext1 {
	
	public static void main(String[] args) throws MalformedURLException, IOException
	{
	    Document document = new Document();
		String home = System.getProperty("user.home");
		String fileName="HelloVandy";
		File file = new File(home+"/Downloads/" + fileName + ".pdf"); 
		try{
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			document.add(new Paragraph("Hello vandy chhay."));
			Image img = Image.getInstance("/Users/X10/Documents/workspace/testTS1/WebContent/resources/images/X10.png");
//			img.setAbsolutePosition(0f, 0f);
			img.scaleAbsolute(100, 50);
			document.add(img);
			document.add(new Paragraph("Sample 1: This is simple image demo."));
	       
			
			
			
			
//			PdfPTable table = new PdfPTable(7);
//			table.setWidthPercentage(105);
//			table.setSpacingBefore(11f);
//			table.setSpacingAfter(0f);
//			
//			float[] colWidth= {1f,2f,2f,2f,2f,3f,3f};
//			
//			table.setWidths(colWidth);
//			PdfPCell c1 = new PdfPCell(new Paragraph("Day"));
//			PdfPCell c2 = new PdfPCell(new Paragraph("In"));
//			PdfPCell c3 = new PdfPCell(new Paragraph("Out"));
//			PdfPCell c4 = new PdfPCell(new Paragraph("Hours"));
//			PdfPCell c5 = new PdfPCell(new Paragraph("Break"));
//			PdfPCell c6 = new PdfPCell(new Paragraph("Project"));
//			PdfPCell c7 = new PdfPCell(new Paragraph("Description"));
//			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
//			c3.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//			c4.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
//			c6.setHorizontalAlignment(Element.ALIGN_MIDDLE);
//			c7.setHorizontalAlignment(Element.ALIGN_CENTER);
//			table.addCell(c1).setBackgroundColor(BaseColor.PINK);
//			table.addCell(c2).setBackgroundColor(BaseColor.PINK);
//			table.addCell(c3).setBackgroundColor(BaseColor.PINK);
//			table.addCell(c4).setBackgroundColor(BaseColor.PINK);
//			table.addCell(c5).setBackgroundColor(BaseColor.PINK);
//			table.addCell(c6).setBackgroundColor(BaseColor.PINK);
//			table.addCell(c7).setBackgroundColor(BaseColor.PINK);
//			document.add(table);
//			
//			PdfPTable table1 = new PdfPTable(7);
//			table1.setWidthPercentage(105);
//			table1.setSpacingBefore(0f);
//			table1.setSpacingAfter(0f);
//			table1.setWidths(colWidth);
//			for(int i=0;i<31;i++)
//			{
//				
//				PdfPCell c11 = new PdfPCell(new Paragraph(""+i));
//				PdfPCell c22 = new PdfPCell(new Paragraph("2"));
//				PdfPCell c33 = new PdfPCell(new Paragraph("3"));
//				PdfPCell c44 = new PdfPCell(new Paragraph("4"));
//				PdfPCell c55 = new PdfPCell(new Paragraph("5"));
//				PdfPCell c66 = new PdfPCell(new Paragraph("6"));
//				PdfPCell c77 = new PdfPCell(new Paragraph("7"));
//				
//				if(i%7==0){
//				table1.addCell(c11).setBackgroundColor(BaseColor.RED);
//				table1.addCell(c22).setBackgroundColor(BaseColor.RED);
//				table1.addCell(c33).setBackgroundColor(BaseColor.RED);
//				table1.addCell(c44).setBackgroundColor(BaseColor.RED);
//				table1.addCell(c55).setBackgroundColor(BaseColor.RED);
//				table1.addCell(c66).setBackgroundColor(BaseColor.RED);
//				table1.addCell(c77).setBackgroundColor(BaseColor.RED);
//				}
//				else{
//					table1.addCell(c11);
//					table1.addCell(c22);
//					table1.addCell(c33);
//					table1.addCell(c44);
//					table1.addCell(c55);
//					table1.addCell(c66);
//					table1.addCell(c77);
//				}
//				
//			}
//			document.add(table1);
			document.close();
			writer.close();
			
		}catch(DocumentException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		
	}
}
