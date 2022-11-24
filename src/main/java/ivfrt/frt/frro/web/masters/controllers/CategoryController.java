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

import ivfrt.frt.frro.web.masters.model.Category;
import ivfrt.frt.frro.web.masters.repositry.CategoryRepository;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryRepository catyrepo;
	
	@GetMapping("/category")
	public ResponseEntity<?>  fetchStateListJWT(){
		List<Category> res=new ArrayList<Category>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			res= catyrepo.findAll();
			return new ResponseEntity<List<Category>>(res,HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "error in category ");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/masters/category")
	public ResponseEntity<?>  fetchStateList(){
		List<Category> res=new ArrayList<Category>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			res= catyrepo.findAll();
			return new ResponseEntity<List<Category>>(res,HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "error in category ");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@GetMapping("/category/{userCatCode}")
	public ResponseEntity<?> fetchStateByCodeJWT(  @PathVariable @Valid @Size(min = 1,max=1,message = "The Category code '${validatedValue}' not accepted it must be  {max} characters long")  String userCatCode) {
		List<Category> res=new ArrayList<Category>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			res=catyrepo.findCategory(userCatCode);
			return new ResponseEntity<List<Category>>(res,HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in category");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			
		}
	}
	
	@GetMapping("/masters/category/{userCatCode}")
	public ResponseEntity<?> fetchStateByCode(  @PathVariable @Valid @Size(min = 1,max=1,message = "The Category code '${validatedValue}' not accepted it must be  {max} characters long")  String userCatCode) {
		List<Category> res=new ArrayList<Category>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			res=catyrepo.findCategory(userCatCode);
			return new ResponseEntity<List<Category>>(res,HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message","error in category-");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			
		}
	}
	
	@GetMapping("/categoryDesc/{catCode}")
	public ResponseEntity<?> fetchCityListByStateCodeJWT(@PathVariable @Valid @Size(min = 1,max=1,message = "The Category code '${validatedValue}' not accepted it must be  {max} characters long") String catCode) {
		List<Category> res=new ArrayList<Category>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
		res= catyrepo.findCategoryDescByCode(catCode);
		return new ResponseEntity<List<Category>>(res,HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			
		
		}
	}
	
	@GetMapping("/masters/categoryDesc/{catCode}")
	public ResponseEntity<?> fetchCityListByStateCode(@PathVariable @Valid @Size(min = 1,max=1,message = "The Category code '${validatedValue}' not accepted it must be  {max} characters long") String catCode) {
		List<Category> res=new ArrayList<Category>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
		res= catyrepo.findCategoryDescByCode(catCode);
		return new ResponseEntity<List<Category>>(res,HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in categoryDesc");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			
		
		}
	}
	
	
	//for show list of category according to visa type and cat code
	@GetMapping("masters/category/{userCatCode}/{visatype}")
	public ResponseEntity<?> fetchCategoryByVisa(  @PathVariable @Valid @Size(min = 1,max=1,message = "The Category code '${validatedValue}' not accepted it must be  {max} characters long")  String userCatCode,
			@PathVariable @Valid @Size(min = 1,message = "The Category code '${validatedValue}' not accepted it must be  {max} characters long")  String visatype) {
		List<Category> res=new ArrayList<Category>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			res=catyrepo.findCategoryVisaType(userCatCode,visatype);
			return new ResponseEntity<List<Category>>(res,HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message","Error in masters/category");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			
		}
	}
	
	
}
