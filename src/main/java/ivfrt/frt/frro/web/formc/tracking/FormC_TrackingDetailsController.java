package ivfrt.frt.frro.web.formc.tracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.formc.FormC;
import ivfrt.frt.frro.web.formc.mainform.FormC_MainFormRepository;
import ivfrt.frt.frro.web.jwt.JwtTokenUtil;
import ivfrt.frt.frro.web.utilities.ConstantUtil;

@Validated
@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class FormC_TrackingDetailsController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;
	
	@Autowired
	private FormC_TrackingDetailsRepositry trackRepo;
	@Autowired
	FormC_MainFormRepository mainFormRepo;
	
	@GetMapping("/formc/temp-app-details")
	public  ResponseEntity<?> fetchTempApplicationDetails(HttpServletRequest req, @Valid @RequestParam String accoCode,@RequestParam String frroFroCode ) throws Exception {
		HashMap<String, String> resultMap = new HashMap<>();
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new Exception("Please Re-login.");
			}
			List<FormC_TrackingDetails> trackDetails=trackRepo.fetchTempApplicationDetails(accoCode, frroFroCode);
			return new ResponseEntity<List<FormC_TrackingDetails>>(trackDetails,HttpStatus.OK);	
		} catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error In temp application details.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		
		}
	}
	
	@GetMapping("/formc/app-details-pending-checkout")
	public  ResponseEntity<?> fetchCheckOutPendingsAppDetails(HttpServletRequest req, @Valid @RequestParam String accoCode,@RequestParam String frroFroCode ) throws Exception {
		HashMap<String, String> resultMap = new HashMap<>();
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new Exception("Please Re-login.");
			}
			List<FormC_TrackingDetails> trackDetails=trackRepo.fetchCheckOutPendingsApplicationDetails(accoCode, frroFroCode);
			return new ResponseEntity<List<FormC_TrackingDetails>>(trackDetails,HttpStatus.OK);	
		} catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error In pending checkout.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		
		}
	}
	
	
	@GetMapping("/formc/get-departed-details")
	public  ResponseEntity<?> fetchDepartedDetails(HttpServletRequest req, @Valid @RequestParam String accoCode,@RequestParam String frroFroCode ) throws Exception {
		HashMap<String, String> resultMap = new HashMap<>();
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new Exception("Please Re-login.");
			}
			List<DepartDetailsPdf> trackDetails=trackRepo.fetchDeparturedApplicationDetails(accoCode, frroFroCode);
			return new ResponseEntity<List<DepartDetailsPdf>>(trackDetails,HttpStatus.OK);	
		} catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error In departed details.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		
		}
	}
	
	
	
	
	
	
}
