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
import ivfrt.frt.frro.web.masters.repositry.CountryRepository;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class CountryController {
	 @Autowired
	 private CountryRepository countryRepo;

	
	 @GetMapping("/masters/country") 
	 public ResponseEntity<?>  fetchCountryList(){
		 List<Country> res=new ArrayList<Country>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		  res= countryRepo.findAll(); 
		  return new ResponseEntity<List<Country>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	 
	 @GetMapping("/country") 
	 public ResponseEntity<?> fetchCountryListJWT(){
		 List<Country> res=new ArrayList<Country>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		  res= countryRepo.findAll();
		  return new ResponseEntity<List<Country>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	 
	 
	 @GetMapping("/masters/country/{countryCode}")
	  public  ResponseEntity<?> fetchCountryByCode(@PathVariable @Valid @Size(min=1, max=3,message="The country code '$(validatevalue)' not accepted it must be between {min} and {max} characters long") String countryCode){
		 List<Country> res=new ArrayList<Country>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= countryRepo.findCountryByCode(countryCode);
		 return new ResponseEntity<List<Country>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	 
	 @GetMapping("/country/{countryCode}")
	  public  ResponseEntity<?> fetchCountryByCodeJWT(@PathVariable @Valid @Size(min=1, max=3,message="The country code '$(validatevalue)' not accepted it must be between {min} and {max} characters long") String countryCode){
		 List<Country> res=new ArrayList<Country>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		 res= countryRepo.findCountryByCode(countryCode);
		 return new ResponseEntity<List<Country>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
}
