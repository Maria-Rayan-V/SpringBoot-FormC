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

import ivfrt.frt.frro.web.masters.model.DocType;
import ivfrt.frt.frro.web.masters.repositry.DocTypeRepository;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class DocTypeController {
	 @Autowired
	 private DocTypeRepository docTypeRepo;

	
	 @GetMapping("/masters/doctype") 
	 public ResponseEntity<?> fetchCountryList(){
		 List<DocType> res=new ArrayList<DocType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
				res= docTypeRepo.findAll();
				return new ResponseEntity<List<DocType>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
	 
	 @GetMapping("/masters/doctype/{doc_type_code}")
	  public  ResponseEntity<?> fetchCountryByCode(@PathVariable @Valid @Size(min = 1,max=3,message = "The doc type code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String doc_type_code){
		 List<DocType> res=new ArrayList<DocType>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
				res= docTypeRepo.findDocTypeByCode(doc_type_code);
				return new ResponseEntity<List<DocType>>(res,HttpStatus.OK);
		 }catch(Exception e) {
				resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
				resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			}
		 }
}
