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

import ivfrt.frt.frro.web.masters.model.LtvCategory;
import ivfrt.frt.frro.web.masters.model.MaritalStatus;
import ivfrt.frt.frro.web.masters.repositry.MaritalStatusRepositry;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@Validated
public class MaritalStatusController {

	@Autowired
	private MaritalStatusRepositry maritalstatusrepo; 
	
	@GetMapping("/masters/marital-status")
	public List<MaritalStatus> fetchAllStatus(){
		
		return maritalstatusrepo.findAll();
	}
	
	@GetMapping("/masters/marital-status/{mStatusCode}")
	public ResponseEntity<?> fetchMaritalStatusDesc(@PathVariable @Valid @Size(min=1,max=1,message= "The marital status code '${validatedValue}' not accepted it must be between {min} and {max} characters long")  String mStatusCode){
		 List<MaritalStatus> res=new ArrayList<MaritalStatus>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= maritalstatusrepo.findByMaritalStatusCode(mStatusCode);
		return new ResponseEntity<List<MaritalStatus>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
}
