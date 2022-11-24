package ivfrt.frt.frro.web.masters.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sun.istack.NotNull;

import ivfrt.frt.frro.web.formc.FormC;
import ivfrt.frt.frro.web.masters.model.CityDistrict;
import ivfrt.frt.frro.web.masters.repositry.CityDistrictRepositry;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class CityDistrictController {

	@Autowired
	private CityDistrictRepositry cityRepositry;
	
	
	@GetMapping("/masters/city")
	public ResponseEntity<?>  fetchStateList(){
		List<CityDistrict> res=new ArrayList<CityDistrict>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			res= cityRepositry.findAll();
			return new ResponseEntity<List<CityDistrict>>(res,HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/masters/city/{cityCode}")
	public ResponseEntity<?> fetchStateByCode(  @PathVariable @Valid @Size(min = 1,max=4,message = "The city code '${validatedValue}' not accepted it must be between {min} and {max} characters long")  String cityCode) {
		List<CityDistrict> res=new ArrayList<CityDistrict>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			res=cityRepositry.findByCityCode(cityCode);
			return new ResponseEntity<List<CityDistrict>>(res,HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			
		}
	}
	@GetMapping("/masters/city-list/{stateCode}")
	public ResponseEntity<?> fetchCityListByStateCode( @PathVariable @Valid @Size(min = 1,max=3,message = "The state code '${validatedValue}' not accepted it must be between {min} and {max} characters long") String stateCode) {
		List<CityDistrict> res=new ArrayList<CityDistrict>();
		HashMap<String, String> resultMap = new HashMap<>();
		try {
		res= cityRepositry.findCityListByState(stateCode);
		return new ResponseEntity<List<CityDistrict>>(res,HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			
		
		}
	}
}
