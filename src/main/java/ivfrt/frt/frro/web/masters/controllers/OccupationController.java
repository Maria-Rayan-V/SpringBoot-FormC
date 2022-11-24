package ivfrt.frt.frro.web.masters.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.masters.model.Occupation;
import ivfrt.frt.frro.web.masters.repositry.OccupationRepository;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class OccupationController {

	
	@Autowired
	private OccupationRepository occupationrepo; 
	
	@GetMapping("/occupation")
	public List<Occupation> fetchAllOccupationJWT(){
		
		return occupationrepo.findAll();
	}
	
	@GetMapping("/masters/occupation")
	public List<Occupation> fetchAllOccupation(){
		
		return occupationrepo.findAll();
	}
	
	
	
	@GetMapping("/occupation/{code}")
	public ResponseEntity<?> fetchOccupationDescJWT(@PathVariable String code){
		 List<Occupation> res=new ArrayList<Occupation>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= occupationrepo.findOccupationByCode(code);
		return new ResponseEntity<List<Occupation>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	@GetMapping("/masters/occupation/{code}")
	public ResponseEntity<?> fetchOccupationDesc(@PathVariable String code){
		 List<Occupation> res=new ArrayList<Occupation>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= occupationrepo.findOccupationByCode(code);
		return new ResponseEntity<List<Occupation>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
}
