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

import ivfrt.frt.frro.web.masters.model.IcpType;
import ivfrt.frt.frro.web.masters.model.Icps;
import ivfrt.frt.frro.web.masters.repositry.IcpTypeRepository;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class IcpTypeController {
	 @Autowired
	 private IcpTypeRepository icpTypeRepo;

	
	 @GetMapping("/masters/icptype") 
	 public ResponseEntity<?> fetchCountryList(){
		 List<IcpType> res=new ArrayList<IcpType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= icpTypeRepo.findAll(); //return "hello"; }
			 return new ResponseEntity<List<IcpType>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	 
	 @GetMapping("/masters/icptype/{icpType}")
	  public  ResponseEntity<?> fetchCountryByCode(@PathVariable @Valid @Size(min=1,max=1,message = "The icp Type '${validatedValue}' not accepted it must be between {min} and {max} characters long") String icpType){
		 List<IcpType> res=new ArrayList<IcpType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= icpTypeRepo.findGenderByCode(icpType);
			 return new ResponseEntity<List<IcpType>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	 
	 
	 @GetMapping("/icptype") 
	 public ResponseEntity<?> fetchCountryListJWT(){
		 List<IcpType> res=new ArrayList<IcpType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= icpTypeRepo.findAll(); //return "hello"; }
			 return new ResponseEntity<List<IcpType>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	 
	 @GetMapping("/icptype/{icpType}")
	  public  ResponseEntity<?> fetchCountryByCodeJWT(@PathVariable @Valid @Size(min=1,max=1,message = "The icp Type '${validatedValue}' not accepted it must be between {min} and {max} characters long") String icpType){
		 List<IcpType> res=new ArrayList<IcpType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= icpTypeRepo.findGenderByCode(icpType);
			 return new ResponseEntity<List<IcpType>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
}
