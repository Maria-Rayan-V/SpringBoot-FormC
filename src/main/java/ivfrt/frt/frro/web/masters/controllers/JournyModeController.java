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

import ivfrt.frt.frro.web.masters.model.IcpType;
import ivfrt.frt.frro.web.masters.model.JournyMode;
import ivfrt.frt.frro.web.masters.model.MaritalStatus;
import ivfrt.frt.frro.web.masters.repositry.JournyModeRepositry;
import ivfrt.frt.frro.web.masters.repositry.MaritalStatusRepositry;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@Validated
public class JournyModeController {

	@Autowired
	private JournyModeRepositry journyModeRepositry; 
	
	@GetMapping("/masters/journy-mode")
	public ResponseEntity<?> fetchAllStatus(){
		 List<JournyMode> res=new ArrayList<JournyMode>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= journyModeRepositry.findAll();
			 return new ResponseEntity<List<JournyMode>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	@GetMapping("/masters/journy-mode/{journyMode}")
	public ResponseEntity<?> fetchMaritalStatusDesc( @PathVariable @Valid @Size(min=1,max=2,message = "The Journy Mode code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String journyMode){
		 List<JournyMode> res=new ArrayList<JournyMode>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= journyModeRepositry.findModeDescByCode(journyMode);
		 return new ResponseEntity<List<JournyMode>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
	
	@GetMapping("/journy-mode")
	public ResponseEntity<?> fetchAllStatusJWT(){
		 List<JournyMode> res=new ArrayList<JournyMode>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= journyModeRepositry.findAll();
			 return new ResponseEntity<List<JournyMode>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	@GetMapping("/journy-mode/{journyMode}")
	public ResponseEntity<?> fetchMaritalStatusDescJWT( @PathVariable @Valid @Size(min=1,max=2,message = "The Journy Mode code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String journyMode){
		 List<JournyMode> res=new ArrayList<JournyMode>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= journyModeRepositry.findModeDescByCode(journyMode);
		 return new ResponseEntity<List<JournyMode>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
}