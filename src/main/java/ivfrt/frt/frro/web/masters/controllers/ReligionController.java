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

import ivfrt.frt.frro.web.masters.model.Religion;
import ivfrt.frt.frro.web.masters.repositry.ReligionRepository;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class ReligionController {

	
	@Autowired
	private ReligionRepository religionrepo; 
	
	@GetMapping("/religion")
	public List<Religion> fetchAllOccupationJWT(){
		
		return religionrepo.findAll();
	}
	
	@GetMapping("/religion/{code}")
	public ResponseEntity<?> fetchReligionDescJWT(@PathVariable String code){
		 List<Religion> res=new ArrayList<Religion>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= religionrepo.findReligionByCode(code);
		return new ResponseEntity<List<Religion>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	
	@GetMapping("/masters/religion")
	public List<Religion> fetchAllOccupation(){
		
		return religionrepo.findAll();
	}
	
	@GetMapping("/masters/religion/{code}")
	public ResponseEntity<?> fetchReligionDesc(@PathVariable String code){
		 List<Religion> res=new ArrayList<Religion>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= religionrepo.findReligionByCode(code);
		return new ResponseEntity<List<Religion>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	
}
