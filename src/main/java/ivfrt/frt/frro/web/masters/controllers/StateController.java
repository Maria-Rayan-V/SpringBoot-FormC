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

import ivfrt.frt.frro.web.masters.model.SplCategory;
import ivfrt.frt.frro.web.masters.model.State;
import ivfrt.frt.frro.web.masters.repositry.StateRepositry;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class StateController {

	@Autowired
	private StateRepositry stateRepositry;
	
	@GetMapping("/masters/state")
	public List<State> fetchStateList(){
		return stateRepositry.findAll();
	}
	
	@GetMapping("/state")
	public ResponseEntity<?> fetchStateListJWT(){
		 List<State> res=new ArrayList<State>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findAll();
		return new ResponseEntity<List<State>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	@GetMapping("/masters/state/{stateCode}")
	public ResponseEntity<?> fetchStateByCode(@PathVariable @Valid @Size(min = 1,max=3,message = "The State code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String stateCode) {
		 List<State> res=new ArrayList<State>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findByStateCode(stateCode);
			 return new ResponseEntity<List<State>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	@GetMapping("/state/{stateCode}")
	public ResponseEntity<?> fetchStateByCodeJWT(@PathVariable @Valid @Size(min = 1,max=3,message = "The State code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String stateCode) {
		 List<State> res=new ArrayList<State>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findByStateCode(stateCode);
			 return new ResponseEntity<List<State>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
}
