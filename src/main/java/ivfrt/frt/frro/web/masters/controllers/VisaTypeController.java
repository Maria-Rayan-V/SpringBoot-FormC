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

import ivfrt.frt.frro.web.masters.model.Country;
import ivfrt.frt.frro.web.masters.model.VisaSubType;
import ivfrt.frt.frro.web.masters.model.VisaType;
import ivfrt.frt.frro.web.masters.repositry.CountryRepository;
import ivfrt.frt.frro.web.masters.repositry.VisaTypeRepository;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class VisaTypeController {
	 @Autowired
	 private VisaTypeRepository visatypeRepo;

	
	 @GetMapping("/masters/visa-type") 
	 public ResponseEntity<?> fetchCountryList(){
		 List<VisaType> res=new ArrayList<VisaType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visatypeRepo.findAll(); //return "hello"; }
		  return new ResponseEntity<List<VisaType>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	 
	 @GetMapping("/masters/visa-type/{visaTypeCode}")
	  public  ResponseEntity<?> fetchCountryByCode(@PathVariable @Valid @Size(min = 1,max=3,message = "The Vista Type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String visaTypeCode){
		 List<VisaType> res=new ArrayList<VisaType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visatypeRepo.findVisaTypeByCode(visaTypeCode);
		  return new ResponseEntity<List<VisaType>>(res,HttpStatus.OK);
			 }catch(Exception e) {
					resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
					resultMap.put("message", e.getMessage());
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
				}
			 }
	 
	 @GetMapping("/visa-type") 
	 public ResponseEntity<?> fetchCountryListWithJWT(){
		 List<VisaType> res=new ArrayList<VisaType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visatypeRepo.findAll(); //return "hello"; }
		  return new ResponseEntity<List<VisaType>>(res,HttpStatus.OK);
			 }catch(Exception e) {
					resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
					resultMap.put("message", e.getMessage());
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
				}
			 }
	 
	 @GetMapping("/visa-type/{visaTypeCode}")
	  public  ResponseEntity<?> fetchCountryByCodeWithJWT(@PathVariable @Valid @Size(min = 1,max=3,message = "The Visa type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String visaTypeCode){
		 List<VisaType> res=new ArrayList<VisaType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visatypeRepo.findVisaTypeByCode(visaTypeCode);
		  return new ResponseEntity<List<VisaType>>(res,HttpStatus.OK);
			 }catch(Exception e) {
					resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
					resultMap.put("message", e.getMessage());
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
				}
			 }
}
