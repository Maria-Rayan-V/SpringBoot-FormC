package ivfrt.frt.frro.web.formc.tracking;

import java.awt.Color;
import java.io.IOException;
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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;







public class PdfExporterDepartureDetails {

	List<DepartDetailsPdf> departObj;

	public PdfExporterDepartureDetails(List<DepartDetailsPdf> departuredetailsObj) {
		this.departObj=departuredetailsObj;
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
        PdfWriter writer= PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        
        
        Font font16B = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font16B.setSize(16);
        font16B.setColor(Color.BLACK);
        if(departObj.isEmpty()) {
        	 Paragraph p = new Paragraph("Record not found", font16B);
             p.setAlignment(Paragraph.ALIGN_CENTER);
             p.setSpacingBefore(10);
             document.add(p);
        	
        }else{
        
        
        Resource resource1 = new ClassPathResource("classpath:watermark.jpg");
	    Image image = Image.getInstance(resource1.getFilename()); 
		  
		  
      // Image image = Image.getInstance("src//main//resources//templates//watermark.jpg");
       PdfContentByte canvas = writer.getDirectContentUnder();
       image.scaleAbsolute(550, 700);
       image.setAbsolutePosition(20, 20);
       canvas.saveState();
       PdfGState state = new PdfGState();
       //state.setFillOpacity(0.1f);
       canvas.setGState(state);
       canvas.addImage(image);
       canvas.restoreState();
		
        
        
        Font font2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font2.setSize(12);
        font2.setColor(Color.BLACK);
        
        Font font14B = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font14B.setSize(14);
        font14B.setColor(Color.BLACK);
         
        Paragraph p = new Paragraph("Departure Details", font16B);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p.setSpacingBefore(10);
        document.add(p);
        
        
        DepartDetailsPdf pdobj1=departObj.get(0);  
        Paragraph accomdtl = new Paragraph(pdobj1.getAccom_details(), font2);
        accomdtl.setAlignment(Paragraph.ALIGN_CENTER);
        accomdtl.setFirstLineIndent(20);
        accomdtl.setSpacingBefore(15);
        document.add(accomdtl);
        
        Font fontTitle= new Font(Font.HELVETICA, 12, Font.BOLD,Color.BLACK) ;
        Paragraph appid = new Paragraph("Application Id : "+departObj.get(0).getForm_c_appl_id(), fontTitle);
        appid.setAlignment(Paragraph.ALIGN_CENTER);
        appid.setSpacingBefore(20);
        document.add(appid);
        
        Paragraph p1 = new Paragraph("Applicant Details", font14B);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p1);
        
        PdfPTable pt = new PdfPTable(4);
        pt.setWidthPercentage(100f);
        pt.setWidths(new float[] {2.0f, 4.5f, 2.0f, 4.5f});
        pt.setSpacingBefore(10);
         
        writeApplicantDetails(pt);
        document.add(pt);
        
        
        Paragraph p3 = new Paragraph("Departure Details", font14B);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        p3.setSpacingBefore(20);
        document.add(p3);
        
        PdfPTable dep = new PdfPTable(4);
        dep.setWidthPercentage(100f);
        dep.setWidths(new float[] {2.0f, 4.0f, 2.0f,2.5f});
        dep.setSpacingBefore(5);
        writeDepartureDeatails(dep);
        document.add(dep);
        }
        document.close();
		
	}
	
	
	
	private void writeDepartureDeatails(PdfPTable dep) {
		DepartDetailsPdf pdobj=departObj.get(0);  
		
		
		 PdfPCell cell = new PdfPCell();
		 //cell.setBackgroundColor(Color.BLUE);
		 cell.setPadding(4);
		 Font fontLevel = FontFactory.getFont(FontFactory.HELVETICA);
		 fontLevel.setColor(Color.BLACK);
		 Font font = FontFactory.getFont(FontFactory.HELVETICA);
		 font.setColor(Color.BLUE);
		 
		 cell.setPhrase(new Phrase("Date of Departure", fontLevel));
		 dep.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getDate_of_departure(), font));
		 dep.addCell(cell);
		 cell.setPhrase(new Phrase("Time of Departure", fontLevel));
		 dep.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getTime_of_departure(), font));
		 dep.addCell(cell);
		
		 cell.setPhrase(new Phrase("Remark", fontLevel));
		 dep.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getDeparture_remark(), font));
		 dep.addCell(cell);
		 cell.setPhrase(new Phrase("Entred on", fontLevel));
		 dep.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getEntered_on(), font));
		 dep.addCell(cell);
		
		
	}

	private void writeApplicantDetails(PdfPTable pt) {
		DepartDetailsPdf pdobj=departObj.get(0);   
		
		 PdfPCell cell = new PdfPCell();
		 //cell.setBackgroundColor(Color.BLUE);
		 cell.setPadding(4);
		 Font fontLevel = FontFactory.getFont(FontFactory.HELVETICA);
		 fontLevel.setColor(Color.BLACK);
		 Font font = FontFactory.getFont(FontFactory.HELVETICA);
		 font.setColor(Color.BLUE);
		 
		 cell.setPhrase(new Phrase("Surname", fontLevel));
		 pt.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getSurname(), font));
		 pt.addCell(cell);
		 cell.setPhrase(new Phrase("Given Name", fontLevel));
		 pt.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getGiven_name(), font));
		 pt.addCell(cell);
		
		 cell.setPhrase(new Phrase("Nationality", fontLevel));
		 pt.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getNationality(), font));
		 pt.addCell(cell);
		 cell.setPhrase(new Phrase("Date of Birth", fontLevel));
		 pt.addCell(cell);
		 cell.setPhrase(new Phrase(pdobj.getDob(), font));
		 pt.addCell(cell);
		
	}

	public List<DepartDetailsPdf> getDepartObj() {
		return departObj;
	}

	public void setDepartObj(List<DepartDetailsPdf> departObj) {
		this.departObj = departObj;
	}
	
	

}
	
	
	
