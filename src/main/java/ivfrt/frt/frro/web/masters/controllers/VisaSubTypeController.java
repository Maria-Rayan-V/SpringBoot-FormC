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

import ivfrt.frt.frro.web.masters.model.VisaSubType;
import ivfrt.frt.frro.web.masters.repositry.VisaSubTypeRepository;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class VisaSubTypeController {

	@Autowired
	private VisaSubTypeRepository visasubtyperepo;
	
	@GetMapping("/masters/visa-subtype")
	public ResponseEntity<?> fetchListVisaSubType(){
		 List<VisaSubType> res=new ArrayList<VisaSubType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visasubtyperepo.findAll();
		 return new ResponseEntity<List<VisaSubType>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/masters/visa-subtype/{visaType}")
	public ResponseEntity<?> fetchVisaSubTypeByVisaType(@PathVariable @Valid @Size(min = 1,max=3,message = "The Visa Type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String visaType) {
		 List<VisaSubType> res=new ArrayList<VisaSubType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visasubtyperepo.findVisaSubTypeByVisaType(visaType);
		 return new ResponseEntity<List<VisaSubType>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/masters/visasubtype/{purposeCode}")
	public ResponseEntity<?> fetchPurposeByPurposeCode(@PathVariable @Valid @Size(min = 1,max=5,message = "The Purpose code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String purposeCode) {
		 List<VisaSubType> res=new ArrayList<VisaSubType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visasubtyperepo.fetchPurposeByPurposeCode(purposeCode);
		 return new ResponseEntity<List<VisaSubType>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	
	@GetMapping("/visa-subtype")
	public ResponseEntity<?> fetchListVisaSubTypeJWT(){
		 List<VisaSubType> res=new ArrayList<VisaSubType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visasubtyperepo.findAll();
		 return new ResponseEntity<List<VisaSubType>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	@GetMapping("/visa-subtype/{visaType}")
	public ResponseEntity<?> fetchVisaSubTypeByVisaTypeJWT(@PathVariable @Valid @Size(min = 1,max=3,message = "The Visa Type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String visaType) {
		 List<VisaSubType> res=new ArrayList<VisaSubType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visasubtyperepo.findVisaSubTypeByVisaType(visaType);
		 return new ResponseEntity<List<VisaSubType>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/visasubtype/{purposeCode}")
	public ResponseEntity<?> fetchPurposeByPurposeCodeJWT(@PathVariable @Valid @Size(min = 1,max=5,message = "The Purpose code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String purposeCode) {
		 List<VisaSubType> res=new ArrayList<VisaSubType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= visasubtyperepo.fetchPurposeByPurposeCode(purposeCode);
		 return new ResponseEntity<List<VisaSubType>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
}
