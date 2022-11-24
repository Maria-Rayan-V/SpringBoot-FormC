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

import ivfrt.frt.frro.web.masters.model.JournyMode;
import ivfrt.frt.frro.web.masters.model.LtvCategory;
import ivfrt.frt.frro.web.masters.repositry.LtvCategoryRepositry;

@RestController
@Validated 
@CrossOrigin (origins = "http://localhost:4200")
public class LtvCategoryController {

	@Autowired
	private LtvCategoryRepositry ltvCatRepositry;
	
	@GetMapping("/masters/ltv-category")
	public List<LtvCategory> fetchStateList(){
		return ltvCatRepositry.findAll();
	}
	
	@GetMapping("/masters/ltv-category/{ltvCatCode}")
	public List<LtvCategory> fetchStateByCode(@PathVariable @Valid @Size(min = 1,max=2,message = "The ltv category code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String ltvCatCode) {
		return ltvCatRepositry.findByLTVCatCode(ltvCatCode);
	}
	
	@GetMapping("/masters/ltv-category-by-country/{ltvCatNationality}")
	public ResponseEntity<?> fetchIcpsByType(@PathVariable @Valid @Size(min = 1,max=3,message = "The nationality code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String ltvCatNationality){
		 List<LtvCategory> res=new ArrayList<LtvCategory>();
		 HashMap<String, String> resultMap = new HashMap<>();
		 try {
			 res=ltvCatRepositry.findLtvCatListByNationality(ltvCatNationality);
		return new ResponseEntity<List<LtvCategory>>(res,HttpStatus.OK);

	 }catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	 }
	
}
