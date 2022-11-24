package ivfrt.frt.frro.web.masters.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.masters.model.MannerOfAcquringCitizenShip;
import ivfrt.frt.frro.web.masters.repositry.MannerOfAcquringCitizenShipRepository;

@RestController
@Validated
public class MannerOfAcquringCitizenShipController {

	@Autowired
	MannerOfAcquringCitizenShipRepository mannerRepo;
	
	@GetMapping("/acquiring-nationality")
	public List<MannerOfAcquringCitizenShip> fetchAllStatusJWT(){
		
		return mannerRepo.findAll();
	}
	
	@GetMapping("/acquiring-nationality/{mannerOfAcqNatCode}")
	public ResponseEntity<?> fetchMaritalStatusDescJWT(@PathVariable @Valid  String mannerOfAcqNatCode){
		 List<MannerOfAcquringCitizenShip> res=new ArrayList<MannerOfAcquringCitizenShip>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= mannerRepo.findByCode(mannerOfAcqNatCode);
		return new ResponseEntity<List<MannerOfAcquringCitizenShip>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/masters/acquiring-nationality")
	public List<MannerOfAcquringCitizenShip> fetchAllStatus(){
		
		return mannerRepo.findAll();
	}
	
	@GetMapping("/masters/acquiring-nationality/{mannerOfAcqNatCode}")
	public ResponseEntity<?> fetchMaritalStatusDesc(@PathVariable @Valid  String mannerOfAcqNatCode){
		 List<MannerOfAcquringCitizenShip> res=new ArrayList<MannerOfAcquringCitizenShip>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= mannerRepo.findByCode(mannerOfAcqNatCode);
		return new ResponseEntity<List<MannerOfAcquringCitizenShip>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
}
