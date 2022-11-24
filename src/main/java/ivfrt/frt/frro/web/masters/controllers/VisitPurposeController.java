package ivfrt.frt.frro.web.masters.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.masters.model.VisitPurpose;
import ivfrt.frt.frro.web.masters.repositry.VisitPurposeRepository;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class VisitPurposeController {
	 @Autowired
	 private VisitPurposeRepository visitpurposerepo;

	
	 
	 
	 @GetMapping("/masters/visit-purpose") 
	 public ResponseEntity<?> fetchCountryListJWT(){
		 List<VisitPurpose> res=new ArrayList<VisitPurpose>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visitpurposerepo.findAll(); //return "hello"; }
		 return new ResponseEntity<List<VisitPurpose>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	 

	 
	 @GetMapping("/masters/visit-purpose/{purposeCode}")
	  public  ResponseEntity<?> fetchCountryByCode(@PathVariable @Valid @Size(min = 1,max=2,message = "The Purpose code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String purposeCode){
		 List<VisitPurpose> res=new ArrayList<VisitPurpose>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visitpurposerepo.findVisitPurposeByCode(purposeCode);
		 return new ResponseEntity<List<VisitPurpose>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
		 
		 @GetMapping("/visit-purpose") 
		 public ResponseEntity<?> fetchCountryList(){
			 List<VisitPurpose> res=new ArrayList<VisitPurpose>();
			 HashMap<String, String> resultMap = new HashMap<>();
			 try {
			 res= visitpurposerepo.findAll(); //return "hello"; }
			 return new ResponseEntity<List<VisitPurpose>>(res,HttpStatus.OK);
			 }catch(Exception e) {
					resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
					resultMap.put("message", e.getMessage());
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
				}
			 }
		 

		 
		 @GetMapping("/visit-purpose/{purposeCode}")
		  public  ResponseEntity<?> fetchCountryByCodeJWT(@PathVariable @Valid @Size(min = 1,max=2,message = "The Purpose code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String purposeCode){
			 List<VisitPurpose> res=new ArrayList<VisitPurpose>();
			 HashMap<String, String> resultMap = new HashMap<>();
			 try {
			 res= visitpurposerepo.findVisitPurposeByCode(purposeCode);
			 return new ResponseEntity<List<VisitPurpose>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
	 }
}
