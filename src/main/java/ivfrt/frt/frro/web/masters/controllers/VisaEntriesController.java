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

import ivfrt.frt.frro.web.masters.model.State;
import ivfrt.frt.frro.web.masters.model.VisaEntries;
import ivfrt.frt.frro.web.masters.repositry.VisaEntriesRepositry;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class VisaEntriesController {
	 @Autowired
	 private VisaEntriesRepositry visaRepositry;

	
	 @GetMapping("/masters/visa-entries") 
	 public ResponseEntity<?> fetchCountryList(){
		 List<VisaEntries> res=new ArrayList<VisaEntries>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= visaRepositry.findAll(); //return "hello"; }
			 return new ResponseEntity<List<VisaEntries>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	 
	 @GetMapping("/masters/visa-entries/{entryTypeCode}")
	  public  ResponseEntity<?> fetchCountryByCode(@PathVariable @Valid @Size(min = 1,max=1,message = "The Entry Type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String entryTypeCode){
		 List<VisaEntries> res=new ArrayList<VisaEntries>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= visaRepositry.findEntryTypeNameByCode(entryTypeCode);
			 return new ResponseEntity<List<VisaEntries>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
}
