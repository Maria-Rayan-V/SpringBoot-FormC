package ivfrt.frt.frro.web.formc.report;

import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import ivfrt.frt.frro.web.formc.tracking.DepartDetailsPdf;

public class MisReportPdfExporter {

   private  List<FormC_MisReport>	dataObject;
   private  String reportType;
   private  String accom_details;
   private  String heading2;
	
   
  
public MisReportPdfExporter() {
	super();
}





public MisReportPdfExporter(List<FormC_MisReport> dataObject, String reportType, String accom_details,
		String heading2) {
	super();
	this.dataObject = dataObject;
	this.reportType = reportType;
	this.accom_details = accom_details;
	this.heading2 = heading2;
}





public void export(HttpServletResponse response) throws DocumentException, IOException {

		Document document = new Document(PageSize.A4);
        PdfWriter writer= PdfWriter.getInstance(document, response.getOutputStream());
     
        document.open();
        
        Font font16B = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font16B.setSize(16);
        font16B.setColor(Color.BLACK);
        
       
        
        if(dataObject.isEmpty() ) {
        	String msg="";
        	 if(reportType.equals("NORECORD")) {
        		
             }else {
            	 msg="!!ERROR!!"; 
             }
        	 Paragraph p = new Paragraph(msg, font16B);
            p.setAlignment(Paragraph.ALIGN_CENTER);
            p.setSpacingBefore(10);
            document.add(p);
            Font fontTitle= new Font(Font.HELVETICA, 12, Font.BOLD,Color.BLACK) ;
            Paragraph appid = new Paragraph(heading2, fontTitle);
            appid.setAlignment(Paragraph.ALIGN_CENTER);
            appid.setSpacingBefore(5);
            document.add(appid);
        }else {
        	
       
        
        Resource resource1 = new ClassPathResource("classpath:watermark.jpg");
        
	     Image image = Image.getInstance(resource1.getFilename()); 
		  
		  
	       PdfContentByte canvas = writer.getDirectContentUnder();
	       image.scaleAbsolute(550, 700);
	       image.setAbsolutePosition(20, 20);
	       canvas.saveState();
	       PdfGState state = new PdfGState();
	       state.setFillOpacity(0.9f);
	       canvas.addImage(image);
	       canvas.restoreState();
		
	       
	        Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font2.setSize(12);
	        font2.setColor(Color.BLACK);
	        Font font14B = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font14B.setSize(14);
	        font14B.setColor(Color.BLACK);
        
	        
	        if(reportType.equals("ARRIVAL")) {
	            Paragraph p = new Paragraph("List of Form C Based on Arrival Date", font16B);
	            p.setAlignment(Paragraph.ALIGN_CENTER);
	            p.setSpacingBefore(10);
	            document.add(p);
	        }else if(reportType.equals("DEPART_DTL")){
        	    Paragraph p = new Paragraph("List of Form C Based on Departure Date", font16B);
	            p.setAlignment(Paragraph.ALIGN_CENTER);
	            p.setSpacingBefore(10);
	            document.add(p);
	        }
	        else if(reportType.equals("ARRIVAL_ENTREDON")){
        	    Paragraph p = new Paragraph("List of Form C fed", font16B);
	            p.setAlignment(Paragraph.ALIGN_CENTER);
	            p.setSpacingBefore(10);
	            document.add(p);
	        }
	        
		        Font fontTitle= new Font(Font.HELVETICA, 12, Font.BOLD,Color.BLACK) ;
	            Paragraph appid = new Paragraph(heading2, fontTitle);
	            appid.setAlignment(Paragraph.ALIGN_CENTER);
	            appid.setSpacingBefore(5);
	            document.add(appid);
	            
	            Paragraph accomdtl = new Paragraph(accom_details, font2);
	            accomdtl.setAlignment(Paragraph.ALIGN_CENTER);
	            accomdtl.setFirstLineIndent(10);
	            accomdtl.setSpacingBefore(15);
	            document.add(accomdtl);
	            
	            
	        
	            if(reportType.equals("ARRIVAL") || reportType.equals("ARRIVAL_ENTREDON")) {

	            	 PdfPTable arival = new PdfPTable(7);
	            	 arival.setWidthPercentage(100f);
	            	 arival.setWidths(new float[] {1.0f, 2.3f, 2.0f, 3.5f, 2.5f,2.0f,2.0f});
	            	 arival.setSpacingBefore(5);
	                 writeArrivalDeatails(arival);
	                 document.add(arival);
	            
	            	
	            }else if(reportType.equals("DEPART_DTL")) {
	            	
	            	PdfPTable depart = new PdfPTable(9);
	            	depart.setWidthPercentage(100f);
	            	depart.setWidths(new float[] {1.5f, 2.3f, 2.0f, 3.5f, 2.5f,2.0f,2.0f,3.0f,2.0f});
	            	depart.setSpacingBefore(5);
	                 writeDepartDeatails(depart);
	                 document.add(depart);
	            	
	            }
	            
	}
            document.close();
        
  }





private void writeDepartDeatails(PdfPTable depart) {
	 PdfPCell cell = new PdfPCell();
	 cell.setBackgroundColor(Color.lightGray);
	 cell.setPadding(9);
	 Font fontLevel = FontFactory.getFont(FontFactory.HELVETICA);
	 fontLevel.setColor(Color.BLACK);
	 fontLevel.setSize(9);
	 Font font = FontFactory.getFont(FontFactory.HELVETICA);
	 font.setColor(Color.BLACK);
	 font.setSize(7);
	 
	 
	 
	 cell.setPhrase(new Phrase("S.No.", fontLevel));
	 depart.addCell(cell);
	
	 cell.setPhrase(new Phrase("Application id", fontLevel));
	 depart.addCell(cell);
	
	 cell.setPhrase(new Phrase("Passport No.", fontLevel));
	 depart.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Name ", fontLevel));
	 depart.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Nationality", fontLevel));
	 depart.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Arrival", fontLevel));
	 depart.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Departure", fontLevel));
	 depart.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Departure Remark", fontLevel));
	 depart.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Entered On", fontLevel));
	 depart.addCell(cell);
	 
	 int sno=1;
	 cell.setBackgroundColor(null);
	 
	 for (FormC_MisReport departObj : dataObject) {

		
		 
		 cell.setPhrase(new Phrase(String.valueOf(sno), font));
		 depart.addCell(cell);
		
		 cell.setPhrase(new Phrase(departObj.getForm_c_appl_id(), font));
		 depart.addCell(cell);
		
		 cell.setPhrase(new Phrase(departObj.getPassport_number(), font));
		 depart.addCell(cell);
		 
		 cell.setPhrase(new Phrase(departObj.getGiven_name()+"  " +departObj.getSurname(), font));
		 depart.addCell(cell);
		 
		 cell.setPhrase(new Phrase(departObj.getNationality(), font));
		 depart.addCell(cell);
		 
		 cell.setPhrase(new Phrase(departObj.getDate_of_arrival_in_hotel()==null?"":departObj.getDate_of_arrival_in_hotel()+" "+departObj.getTime_of_arrival_in_hotel()==null?"":departObj.getTime_of_arrival_in_hotel(), font));
		 depart.addCell(cell);
		 
		 
		 
		 
		 cell.setPhrase(new Phrase(departObj.getDate_of_departure()==null?"":departObj.getDate_of_departure().toString()+" "+departObj.getTime_of_departure()==null?"":departObj.getTime_of_departure(), font));
		 depart.addCell(cell);
		 
		 cell.setPhrase(new Phrase(departObj.getDeparture_remark(), font));
		 depart.addCell(cell);
		 
		 cell.setPhrase(new Phrase(departObj.getEntered_on()==null?"":departObj.getEntered_on().toString(), font));
		 depart.addCell(cell);
		
		 
		 sno++;
	
	}
	
}





private void writeArrivalDeatails(PdfPTable arival) {
	
	 PdfPCell cell = new PdfPCell();
	 cell.setBackgroundColor(Color.lightGray);
	 cell.setPadding(7);
	 Font fontLevel = FontFactory.getFont(FontFactory.HELVETICA);
	 fontLevel.setColor(Color.BLACK);
	 fontLevel.setSize(9);
	 Font font = FontFactory.getFont(FontFactory.HELVETICA);
	 font.setColor(Color.BLACK);
	 font.setSize(8);
	 
	 
	 cell.setPhrase(new Phrase("S.No.", fontLevel));
	 arival.addCell(cell);
	
	 cell.setPhrase(new Phrase("Application id", fontLevel));
	 arival.addCell(cell);
	
	 cell.setPhrase(new Phrase("Passport No.", fontLevel));
	 arival.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Name ", fontLevel));
	 arival.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Nationality", fontLevel));
	 arival.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Arrival", fontLevel));
	 arival.addCell(cell);
	 
	 cell.setPhrase(new Phrase("Entered On", fontLevel));
	 arival.addCell(cell);
	 int sno=1;
	 //cell.setBackgroundColor(Color.WHITE);
	 cell.setBackgroundColor(null);
	 for (FormC_MisReport dataObj : dataObject) {
		
		 
		 cell.setPhrase(new Phrase(String.valueOf(sno), font));
		 arival.addCell(cell);
		
		 cell.setPhrase(new Phrase(dataObj.getForm_c_appl_id(), font));
		 arival.addCell(cell);
		
		 cell.setPhrase(new Phrase(dataObj.getPassport_number(), font));
		 arival.addCell(cell);
		 
		 cell.setPhrase(new Phrase(dataObj.getGiven_name()+"  " +dataObj.getSurname(), font));
		 arival.addCell(cell);
		 
		 cell.setPhrase(new Phrase(dataObj.getNationality(), font));
		 arival.addCell(cell);
		 
		 cell.setPhrase(new Phrase(dataObj.getDate_of_arrival_in_hotel()==null?"":dataObj.getDate_of_arrival_in_hotel()+" "+dataObj.getTime_of_arrival_in_hotel(), font));
		 arival.addCell(cell);
		 
		 cell.setPhrase(new Phrase(dataObj.getEntered_on()==null?"":dataObj.getEntered_on().toString(), font));
		 arival.addCell(cell);
		 sno++;
	}
	 
}





public List<FormC_MisReport> getDataObject() {
	return dataObject;
}





public void setDataObject(List<FormC_MisReport> dataObject) {
	this.dataObject = dataObject;
}





public String getReportType() {
	return reportType;
}





public void setReportType(String reportType) {
	this.reportType = reportType;
}





public String getAccom_details() {
	return accom_details;
}





public void setAccom_details(String accom_details) {
	this.accom_details = accom_details;
}





public String getHeading2() {
	return heading2;
}





public void setHeading2(String heading2) {
	this.heading2 = heading2;
}





	

}
