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
import ivfrt.frt.frro.web.masters.repositry.SplCategoryRepository;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@Validated
public class SplCategoryController {
	 @Autowired
	 private SplCategoryRepository splCatRepo;

	
	 
	 
	 	@GetMapping("/masters/spl-category") 
	 	public ResponseEntity<?> fetchCountryListJWT(){
	 		 List<SplCategory> res=new ArrayList<SplCategory>();
			 HashMap<String, String> resultMap = new HashMap<>();
			 try {
				 res= splCatRepo.findAll(); 
				 return new ResponseEntity<List<SplCategory>>(res,HttpStatus.OK);

			 }catch(Exception e) {
					resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
					resultMap.put("message", e.getMessage());
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
				}
			 }
	 

	 
	 @GetMapping("/masters/spl-category/{splCatCode}")
	  public  List<SplCategory> fetchCountryByCodeJWT(@PathVariable @Valid @Size(min = 1,max=2,message = "The Special category code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String splCatCode){
		 return splCatRepo.findSplCatByCode(splCatCode);
	 }
}
