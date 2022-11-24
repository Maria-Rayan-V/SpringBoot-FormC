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

import ivfrt.frt.frro.web.masters.model.Gender;
import ivfrt.frt.frro.web.masters.repositry.GenderRepositry;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class GenderController {
	 @Autowired
	 private GenderRepositry genderRepo;

	
	 @GetMapping("/masters/gender") 
	 public ResponseEntity<?> fetchCountryList(){
		 List<Gender> res=new ArrayList<Gender>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
				res= genderRepo.findAll(); //return "hello"; }
				return new ResponseEntity<List<Gender>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	 
	 @GetMapping("/masters/gender/{genderCode}")
	  public  ResponseEntity<?> fetchCountryByCode(@PathVariable @Valid @Size(min=1,max=1,message = "The city code '${validatedValue}' not accepted it must be  {max} characters long") String genderCode){
		 List<Gender> res=new ArrayList<Gender>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
				res= genderRepo.findGenderByCode(genderCode);
		 return new ResponseEntity<List<Gender>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
}
