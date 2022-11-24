package ivfrt.frt.frro.web.formc.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import ivfrt.frt.frro.web.formc.tracking.DepartDetailsPdf;
import ivfrt.frt.frro.web.formc.tracking.FormC_TrackingDetailsRepositry;
import ivfrt.frt.frro.web.formc.tracking.PdfExporterDepartureDetails;
import ivfrt.frt.frro.web.jwt.JwtTokenUtil;
import ivfrt.frt.frro.web.utilities.ConstantUtil;

@Validated
@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class FormC_MisReportController {
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;
	
	@Autowired
	FormC_MisReportRepository misrepo;
	@Autowired
	private FormC_TrackingDetailsRepositry trackRepo;

	@PostMapping("/formc/generate-arrivaldetails-pdf")
	 public void exportArrivalDetails(HttpServletRequest req,  HttpServletResponse response, @Valid @RequestBody FormC_MisReport reqParameter) throws Exception {
	       
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		
		    response.setContentType("application/pdf");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=ArrivalDetails_" +user+ ".pdf";
	        response.setHeader(headerKey, headerValue);
	        List<FormC_MisReport> arriavalDetail=new ArrayList<FormC_MisReport>();
	        try {
		        arriavalDetail=misrepo.exportPdfOfArrivalDetailsList(reqParameter) ;
		        String accomDetails=misrepo.fetchAccomDetails(reqParameter.getAcco_code(), reqParameter.getFrro_fro_code());
		        String heading2="Duriing "+reqParameter.getFrom_date()+" To "+reqParameter.getTo_date();
		        MisReportPdfExporter exporter = new MisReportPdfExporter(arriavalDetail,"ARRIVAL",accomDetails,heading2);
		        exporter.export(response);
	        }catch(Exception e) {
	        	MisReportPdfExporter exporter = new MisReportPdfExporter(arriavalDetail,"ARRIVAL","",e.getMessage());
		        exporter.export(response);
	        }
	         
	    }
	
	
	@PostMapping("/formc/generate-departuredetails-pdf")
	 public void exportDepartDetails(HttpServletRequest req,  HttpServletResponse response, @Valid @RequestBody FormC_MisReport reqParameter) throws DocumentException, IOException  {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		    response.setContentType("application/pdf");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=ArrivalDetails_" +user+ ".pdf";
	        response.setHeader(headerKey, headerValue);
	        List<FormC_MisReport> departDetail=new ArrayList<FormC_MisReport>();
	        try {
		        departDetail=misrepo.exportPdfOfDepartureDetailsList(reqParameter) ;
		      if(departDetail.size()>0)  {
		        String accomDetails=misrepo.fetchAccomDetails(reqParameter.getAcco_code(), reqParameter.getFrro_fro_code());
		        String heading2="From "+reqParameter.getFrom_date()+" To "+reqParameter.getTo_date();
		        MisReportPdfExporter exporter = new MisReportPdfExporter(departDetail,"DEPART_DTL",accomDetails,heading2);
		        exporter.export(response);
		      }else {

			        String heading2="No Record found  From "+reqParameter.getFrom_date()+" To "+reqParameter.getTo_date()+".";
			        MisReportPdfExporter exporter = new MisReportPdfExporter(departDetail,"NORECORD",null,heading2);
			        exporter.export(response);
		      }
	        }
	        catch(Exception e ) {
	        
	        	  MisReportPdfExporter exporter = new MisReportPdfExporter(departDetail,"DEPART_DTL","ERROR",e.getMessage());
	        	  exporter.export(response);	
	        }
	    }
	
	
	@PostMapping("/formc/generate-arrival-by-entredon-pdf")
	 public void exportArrivalDetailsFeedingDeate(HttpServletRequest req,  HttpServletResponse response, @Valid @RequestBody FormC_MisReport reqParameter) throws DocumentException, IOException  {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		    response.setContentType("application/pdf");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=ArrivalDetails_" +user+ ".pdf";
	        response.setHeader(headerKey, headerValue);
	        List<FormC_MisReport> departDetail=new ArrayList<FormC_MisReport>();
	        try {
		        departDetail=misrepo.exportPdfOfArrivalDetailsListByEntredOn(reqParameter) ;
		      if(departDetail.size()>0)  {
		        String accomDetails=misrepo.fetchAccomDetails(reqParameter.getAcco_code(), reqParameter.getFrro_fro_code());
		        String heading2="From "+reqParameter.getFrom_date()+" To "+reqParameter.getTo_date();
		        MisReportPdfExporter exporter = new MisReportPdfExporter(departDetail,"ARRIVAL_ENTREDON",accomDetails,heading2);
		        exporter.export(response);
		      }else {

			        String heading2="No Record found  From "+reqParameter.getFrom_date()+" To "+reqParameter.getTo_date()+".";
			        MisReportPdfExporter exporter = new MisReportPdfExporter(departDetail,"NORECORD",null,heading2);
			        exporter.export(response);
		      }
	        }
	        catch(Exception e ) {
	        	  MisReportPdfExporter exporter = new MisReportPdfExporter(departDetail,"ARRIVAL_ENTREDON","ERROR",e.getMessage());
	        	  exporter.export(response);	
	        }
	    }
	@GetMapping("/formc/departuredetails/pdf/{applicationid}")
	 public void exportToPDF(HttpServletResponse response, @PathVariable @NotBlank @Pattern(regexp = ConstantUtil.APPID,message = "Invalid Application id.") String applicationid) throws Exception {
	        response.setContentType("application/pdf");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=ApplicationForm" + applicationid + ".pdf";
	        response.setHeader(headerKey, headerValue);
	        List<DepartDetailsPdf> departDetailsPdf=new ArrayList<DepartDetailsPdf>();
	        departDetailsPdf=trackRepo.exportPdfOfDepartDetails(applicationid); 
	        PdfExporterDepartureDetails exporter = new PdfExporterDepartureDetails(departDetailsPdf);
	        exporter.export(response);
	         
	    }
}
