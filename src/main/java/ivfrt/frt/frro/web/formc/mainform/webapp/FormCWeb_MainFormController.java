package ivfrt.frt.frro.web.formc.mainform.webapp;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.jwt.JwtTokenUtil;
import ivfrt.frt.frro.web.service.imagesave.ImageSaveRepository;
import ivfrt.frt.frro.web.utilities.ConstantUtil;

@Validated
@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class FormCWeb_MainFormController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	FormCWeb_MainFormRepository mainFormRepo;
	@Autowired
	ImageSaveRepository imagerepo;

	@PostMapping("/formc/genrate-appid-web")
	public ResponseEntity<?> genrateAppid(HttpServletRequest req, @Valid @RequestBody FormCWeb_GenAppID genAppIdModel)
			throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		genAppIdModel.setEntered_by(user);
		HashMap<String, String> resultMap = new HashMap<>();
		FormCWeb_GenAppID resObj = new FormCWeb_GenAppID();
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			 resObj = mainFormRepo.genrateAppiD(genAppIdModel);
		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in genrate-appid");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		return new ResponseEntity<FormCWeb_GenAppID>(resObj, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/formc/delete-pending-appid/{applicationid}") 
	 public ResponseEntity<?> deleteAppid(HttpServletRequest req,
			  @PathVariable @NotBlank @Pattern(regexp = ConstantUtil.APPID,message = "Invalid Application Id.") String applicationid) throws Exception {
		
		HashMap<String, String> resultMap = new HashMap<>();
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		
		int status=0;
		try {
			String appUser = mainFormRepo.fetchUserId(applicationid);
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			if(!user.equalsIgnoreCase(appUser)) {
				throw new Exception("Invalid Application Id/User.");
			}
			
			status=mainFormRepo.deletePendingApplication(applicationid);
			
		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in delete application id.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		if(status==1) {
			return new ResponseEntity<String>("Application deleted successfully.", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Somthing went wrong", HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	

	
	
}
