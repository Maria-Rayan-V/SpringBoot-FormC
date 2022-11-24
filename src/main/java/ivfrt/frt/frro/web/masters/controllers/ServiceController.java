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
import ivfrt.frt.frro.web.masters.model.Service;
import ivfrt.frt.frro.web.masters.repositry.ServiceRepository;


@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class ServiceController {
	 @Autowired
	 private ServiceRepository serviceRepo;

	
	 @GetMapping("/masters/service") 
	 public List<Service> fetchServiceList(){
		  return serviceRepo.findAll(); //return "hello"; }
		 }
	 
	 @GetMapping("/app-service") 
	 public List<Service> fetchServiceListApp(){
		  return serviceRepo.findAllForMobile(); //return "hello"; }
		 }
	 
	 
	 @GetMapping("/app-service/{serviceCode}")
	  public  ResponseEntity<?> fetchServiceByCodeApp(@PathVariable @Valid @Size(min = 1,max=2,message = "The Service code '${validatedValue}' not accepted it must be between {min} and {max} characters long")  String serviceCode){
		 List<Service> res=new ArrayList<Service>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= serviceRepo.findServiceByCode(serviceCode);
		 return new ResponseEntity<List<Service>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	 
	 @GetMapping("/masters/service/{serviceCode}")
	  public  ResponseEntity<?> fetchServiceByCode(@PathVariable @Valid @Size(min = 1,max=2,message = "The Service code '${validatedValue}' not accepted it must be between {min} and {max} characters long")  String serviceCode){
		 List<Service> res=new ArrayList<Service>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= serviceRepo.findServiceByCode(serviceCode);
		 return new ResponseEntity<List<Service>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
}
