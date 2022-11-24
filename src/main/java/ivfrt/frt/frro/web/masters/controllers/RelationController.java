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

import ivfrt.frt.frro.web.masters.model.Relation;
import ivfrt.frt.frro.web.masters.repositry.RelationRepository;
@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class RelationController {

	
	@Autowired
	private RelationRepository relationrepo; 
	
	@GetMapping("/relation")
	public List<Relation> fetchAllOccupationJWT(){
		
		return relationrepo.findAll();
	}
	
	@GetMapping("/relation/{code}")
	public ResponseEntity<?> fetchRelationDescJWT(@PathVariable String code){
		 List<Relation> res=new ArrayList<Relation>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= relationrepo.findRelationByCode(code);
		return new ResponseEntity<List<Relation>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	
	@GetMapping("/masters/relation")
	public List<Relation> fetchAllOccupation(){
		
		return relationrepo.findAll();
	}
	
	@GetMapping("/masters/relation/{code}")
	public ResponseEntity<?> fetchRelationDesc(@PathVariable String code){
		 List<Relation> res=new ArrayList<Relation>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= relationrepo.findRelationByCode(code);
		return new ResponseEntity<List<Relation>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	
}
