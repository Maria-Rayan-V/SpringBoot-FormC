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

import ivfrt.frt.frro.web.masters.model.District;
import ivfrt.frt.frro.web.masters.repositry.DistrictRepositry;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class DistrictController {

	@Autowired
	private DistrictRepositry distRepositry;
	
	@GetMapping("/masters/district")
	public ResponseEntity<?> fetchStateList(){
		 List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res= distRepositry.findAll();
		 return new ResponseEntity<List<District>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/district")
	public ResponseEntity<?> fetchStateListJWT(){
		 List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res=distRepositry.findAll();
		 return new ResponseEntity<List<District>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/masters/district/{distCode}")
	public ResponseEntity<?> fetchDistrictByCode(@PathVariable @Valid @Size(min=1, max=3,message="The district code '$(validatevalue)' not accepted it must be between{min} and {max} characters long") String distCode) {
		 List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res= distRepositry.findByDistrictCode(distCode);
		return new ResponseEntity<List<District>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	@GetMapping("/district/{distCode}")
	public ResponseEntity<?>  fetchDistrictByCodeJWT(@PathVariable @Valid @Size(min=1, max=3,message="The district code '$(validatevalue)' not accepted it must be between {min} and {max} characters long") String distCode) {
		 List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res= distRepositry.findByDistrictCode(distCode);
		return new ResponseEntity<List<District>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/masters/district/{stateCode}/{distCode}")
	public ResponseEntity<?>  fetchDistrictOfState(@PathVariable @Valid @Size(min=1, max=3,message="The state code not accepted it must be between{min} and {max} characters long") String stateCode,
			@PathVariable @Valid @Size(min=1, max=3,message="The district code not accepted it must be between {min} and {max} characters long") String distCode) {
		 List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res= distRepositry.findDistrictOfState(stateCode, distCode);
		return new ResponseEntity<List<District>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	
	@GetMapping("/masters/district-list/{stateCode}")
	public ResponseEntity<?> fetchStateByCode(@PathVariable @Valid @Size(min=1, max=3,message="The state code '$(validatevalue)' not accepted it must be between{min} and {max} characters long") String stateCode) {
		 List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res= distRepositry.findDistrictListByState(stateCode);
		return new ResponseEntity<List<District>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	
	
	
	
	@GetMapping("/district-list/{stateCode}/{nationality}")
	public ResponseEntity<?> fetchStateByCodeJWT(@PathVariable @Valid @Size(min=1,max=3,message = "The nationality code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String stateCode, @PathVariable String nationality) {
		List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res= distRepositry.findDistrictListByStateToken(stateCode, nationality);
		return new ResponseEntity<List<District>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	@GetMapping("/masters/district-list/{stateCode}/{nationality}")
	public ResponseEntity<?> fetchStateByCode(@PathVariable @Valid @Size(min=1,max=3,message = "The nationality code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String stateCode, @PathVariable String nationality) {
		List<District> res=new ArrayList<District>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
		res= distRepositry.findDistrictListByStateToken(stateCode, nationality);
		return new ResponseEntity<List<District>>(res,HttpStatus.OK);
	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
}
