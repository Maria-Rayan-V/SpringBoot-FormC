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
import ivfrt.frt.frro.web.masters.model.Icps;
import ivfrt.frt.frro.web.masters.repositry.IcpsRepositry;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class IcpsController {

	@Autowired
	private IcpsRepositry stateRepositry;
	
	@GetMapping("/masters/icps")
	public ResponseEntity<?> fetchStateList(){
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findAll();
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	@GetMapping("/masters/icps-name-bysrno/{icpsSrNo}")
	public ResponseEntity<?> fetchStateByCode(@PathVariable @Valid @Size(min=1,max=3,message = "The icps Serial Number '${validatedValue}' not accepted it must be between {min} and {max} characters long") String icpsSrNo) {
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findByStateCode(icpsSrNo);
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	@GetMapping("/masters/icps-list-bytype/{icpTypeCode}")
	public ResponseEntity<?> fetchIcpsByType(@PathVariable @Valid @Size(min=1,max=1,message = "The icp Type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String icpTypeCode){
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findIcpsByType(icpTypeCode);
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	
	
	@GetMapping("/icps")
	public ResponseEntity<?> fetchStateListJWT(){
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findAll();
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	@GetMapping("/icps-name-bysrno/{icpsSrNo}")
	public ResponseEntity<?> fetchStateByCodeJWT(@PathVariable @Valid @Size(min=1,max=3,message = "The icps Serial Number '${validatedValue}' not accepted it must be between {min} and {max} characters long") String icpsSrNo) {
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findByStateCode(icpsSrNo);
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	
	@GetMapping("/icps-list-bytype/{icpTypeCode}")
	public ResponseEntity<?> fetchIcpsByTypeJWT(@PathVariable @Valid @Size(min=1,max=1,message = "The icp Type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String icpTypeCode){
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findIcpsByType(icpTypeCode);
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	//All Places of arrival
	@GetMapping("/icps-list")
	public ResponseEntity<?> fetchIcpsByTypeJWT(){
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findAllIcps();
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	@GetMapping("/masters/icps-list")
	public ResponseEntity<?> fetchIcpsByType(){
		 List<Icps> res=new ArrayList<Icps>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res= stateRepositry.findAllIcps();
			 return new ResponseEntity<List<Icps>>(res,HttpStatus.OK);

		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
}
