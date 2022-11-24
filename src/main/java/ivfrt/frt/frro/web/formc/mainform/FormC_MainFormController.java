package ivfrt.frt.frro.web.formc.mainform;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.jwt.JwtTokenUtil;
import ivfrt.frt.frro.web.service.imagesave.ImageSaveRepository;

@Validated
@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class FormC_MainFormController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Autowired
	FormC_MainFormRepository mainFormRepo;
	@Autowired
	ImageSaveRepository imagerepo;

	@PostMapping("/formc/genrate-appid")
	public ResponseEntity<?> genrateAppid(HttpServletRequest req, @Valid @RequestBody FormC_GenAppID genAppIdModel)
			throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		
		genAppIdModel.setEntered_by(user);

		HashMap<String, String> resultMap = new HashMap<>();
		FormC_GenAppID resObj = new FormC_GenAppID();

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
		return new ResponseEntity<FormC_GenAppID>(resObj, HttpStatus.OK);
	}

	@PostMapping("/formc/submit-personaldetails")
	public ResponseEntity<?> personalDetails(HttpServletRequest req,
			@Valid @RequestBody FormC_PersonalDetail personalDetals) throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		FormC_PersonalDetail resObj = new FormC_PersonalDetail();
		String appUser = mainFormRepo.fetchUserId(personalDetals.getForm_c_appl_id());
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			
			if (user.equalsIgnoreCase(appUser)) {
				resObj = mainFormRepo.submit_FormC_PersonalDetail(personalDetals);
			} else {
				throw new Exception("Invalid Application Id/User.");
			}

		}
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<FormC_PersonalDetail>(resObj, HttpStatus.OK);
	}

	@PostMapping("/formc/submit-passport-visa")
	public ResponseEntity<?> passportVisa(HttpServletRequest req, @Valid @RequestBody FormC_PassPortVisa passvisa)
			throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		FormC_PassPortVisa resObj = new FormC_PassPortVisa();
		String appUser = mainFormRepo.fetchUserId(passvisa.getForm_c_appl_id());
		try {
				
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			if (user.equalsIgnoreCase(appUser)) {
				resObj = mainFormRepo.submit_FormC_PassPortVisa(passvisa);
			} else {
				throw new Exception("Invalid Application Id/User.");
			}

		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<FormC_PassPortVisa>(resObj, HttpStatus.OK);
	}

	@PostMapping("/formc/submit-arrival-nextdest")
	public ResponseEntity<?> arrivalNextDest(HttpServletRequest req, @Valid @RequestBody FormC_ArrivalNextDest arrival)
			throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);

		HashMap<String, String> resultMap = new HashMap<>();
		FormC_ArrivalNextDest resObj = new FormC_ArrivalNextDest();
		String appUser = mainFormRepo.fetchUserId(arrival.getForm_c_appl_id());
		try {

			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			if (user.equalsIgnoreCase(appUser)) {
				resObj = mainFormRepo.submit_FormC_ArrivalNextDest(arrival);
			} else {
				throw new Exception("Invalid Application Id/User.");
			}
		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "submit-arrival-nextdest-" + e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<FormC_ArrivalNextDest>(resObj, HttpStatus.OK);
	}

	@PostMapping("/formc/submit-refrence-contact")
	public ResponseEntity<?> refContact(HttpServletRequest req, @Valid @RequestBody FormC_RefContact ref)
			throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);

		HashMap<String, String> resultMap = new HashMap<>();
		FormC_RefContact resObj = new FormC_RefContact();
		String appUser = mainFormRepo.fetchUserId(ref.getForm_c_appl_id());
		try {

			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			if (user.equalsIgnoreCase(appUser)) {
				resObj = mainFormRepo.submit_FormC_RefContact(ref);
			} else {
				throw new Exception("Invalid Application Id/User.");
			}

		} catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "submit-refrence-contact" + e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<FormC_RefContact>(resObj, HttpStatus.OK);
	}

	@PostMapping("/formc/submit-photo")
	public ResponseEntity<?> photoUpload(HttpServletRequest req, @Valid @RequestBody FormC_UploadPhoto img)
			throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		int resStatus = 0;
		String appUser = mainFormRepo.fetchUserId(img.getForm_c_appl_id());
		try {

			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			if (user.equalsIgnoreCase(appUser)) {
				resStatus = imagerepo.saveImage(img.getForm_c_appl_id(), Base64.decodeBase64(img.getImg().getBytes()),
						appUser);
			} else {
				throw new Exception("Invalid Application Id/User.");
			}

		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "submit-photo" + e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		if (resStatus > 0) {
			return new ResponseEntity<String>("Ïmage Uploaded Successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Image Not Upladed. Try again!", HttpStatus.BAD_REQUEST);
		}
	}

	// Final Submit

	@PostMapping("/formc/formc-final-submit")
	public ResponseEntity<?> finalSubmitFormC(HttpServletRequest req, @RequestParam String appid) throws Exception {

		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		String appUser = mainFormRepo.fetchUserId(appid);
		HashMap<String, String> resultMap = new HashMap<>();
		String resString = "";
		try {
			
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			if (user.equals(appUser)) {
				resString = mainFormRepo.finalSubmitFormC(appid);
			} else {
				resultMap.put("status", HttpStatus.UNAUTHORIZED.toString());
				resultMap.put("message", "You are not Authorized user.");
				return new ResponseEntity<Object>(resultMap, HttpStatus.UNAUTHORIZED);
			}
		} catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "formc-final-submit " + e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(resString, HttpStatus.OK);
	}

	@PostMapping("/formc/logout")
	public ResponseEntity<?> logoutUser(HttpServletRequest req) throws Exception {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user = jwtTokenUtil.getUsernameFromToken(token);
		Date expTime=jwtTokenUtil.getExpirationDateFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		String msg="";
		try {
			msg = mainFormRepo.logoutUser(token, user, expTime);
			
		 } 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "formc-final-submit " + e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
}
