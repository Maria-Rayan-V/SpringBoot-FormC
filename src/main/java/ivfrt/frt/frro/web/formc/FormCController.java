package ivfrt.frt.frro.web.formc;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.formc.mainform.FormC_MainFormRepository;
import ivfrt.frt.frro.web.jwt.JwtTokenUtil;
import ivfrt.frt.frro.web.service.imagesave.ImageSave;
import ivfrt.frt.frro.web.service.imagesave.ImageSaveRepository;

@Validated
@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class FormCController {
	
	@Autowired
	FormCRepository formcrepo;

	@Autowired
	FormC_MainFormRepository mainFormRepo;
	
	@Autowired
	ImageSaveRepository imagerepo;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;
	 
	 
	
	 
	
	//Cpmplete form break in Multiple ApI --mainform packege  02-11-21 can we delete
	@PostMapping("/formc/form-c")
	public ResponseEntity<?> createUser(HttpServletRequest req, @Valid @RequestBody FormC formc) throws Exception{
		
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		formc.setEntered_by(user);
		
		
		System.out.println("---formc.getEntered_by()--------"+formc.getEntered_by());
		
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			
			
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new Exception("Please Re-login.");
			}
			
			FormC res= formcrepo.insertFormCdata(formc); 
			ImageSave imgObj=imagerepo.downLoadImage(res.getForm_c_appl_id());
			if(imgObj!=null) {
					res.setImg(Base64.encodeBase64String(imgObj.getImgData()));
				}
			return new ResponseEntity<FormC>(res,HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	//After final Submit
	@GetMapping("/formc/appdetail-by-appid")
	public ResponseEntity<?> fetchByAppId(HttpServletRequest req, @Valid @RequestParam String appid ) throws Exception {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		String appUser=formcrepo.fetchUserIdAfterFinalSumbmit(appid);
		HashMap<String, String> resultMap = new HashMap<>();
		List<FormC> resObj=new ArrayList<FormC>();
		try {
			
			
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			if(user.equals(appUser)) {
				resObj=formcrepo.findByAppId(appid);
			}else {
				resultMap.put("status", HttpStatus.UNAUTHORIZED.toString());
				resultMap.put("message", "You are not Authorized user.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.UNAUTHORIZED);
			}
			
		}
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in appdetail-by-appid"+e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<FormC>>(resObj,HttpStatus.OK);
	}
	
	//3. Search Existing formC details(passportNumber,Nationality)-Submitted
	@GetMapping("/formc/appdetail-by-passnum")
	public ResponseEntity<?> fetchByPassNum(HttpServletRequest req, @Valid @RequestParam String passportNo,@RequestParam String nationality ) throws Exception {
		HashMap<String, String> resultMap = new HashMap<>();
		List<FormC> result=new ArrayList<FormC>();
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new Exception("Please Re-login.");
			}
			
			 result=formcrepo.findByPassnumAndNat(passportNo, nationality);
		}
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in appdetail-by-passnum");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<FormC>>(result,HttpStatus.OK);

	}
	
	//Old record deleted
	@GetMapping("/formc/userdetail")
	public  ResponseEntity<?> fetchByUserId(HttpServletRequest req, @Valid @RequestParam String accoCode,@RequestParam String userid ) throws Exception {
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		HashMap<String, String> resultMap = new HashMap<>();
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new Exception("Please Re-login.");
			}
			
			List<FormC> test=formcrepo.findByUserId(userid, accoCode);
			for (FormC formC : test) {
				Map<String,String> temp=new HashMap<String, String>();
				temp.put("form_c_appl_id",formC.getForm_c_appl_id() );
				temp.put("given_name", formC.getName());
				temp.put("surname",formC.getSurname());
				temp.put("nationality", formC.getNationality());
				temp.put("passnum",formC.getPassnum());
				result.add(temp);
			}
			return new ResponseEntity<List<Map<String,String>>>(result,HttpStatus.OK);	
		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error In userdetail.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		
		}
	}
	
	@GetMapping("/formc/pending-app-list")
	public  ResponseEntity<?> fetchPendingsByUserId(HttpServletRequest req, @Valid @RequestParam String frroCode,@RequestParam String userid ) throws Exception {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		List<ApplicationList> resObj=new ArrayList<ApplicationList>();
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			if(user.equals(userid)) {
				resObj=formcrepo.pendingApplicationList(userid, frroCode);
			}else {
				resultMap.put("status", HttpStatus.UNAUTHORIZED.toString());
				resultMap.put("message", "You are not authorized.");
				return new ResponseEntity<Object>(resultMap, HttpStatus.UNAUTHORIZED);	
			}
				
		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in Pending App List");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<ApplicationList>>(resObj,HttpStatus.OK);
	
	}
	
	@GetMapping("/formc/app-list")
	public  ResponseEntity<?> fetchApplistsByUserId(HttpServletRequest req, @Valid @RequestParam String frroCode,@RequestParam String userid ) throws Exception {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		List<ApplicationList> resObj=new ArrayList<ApplicationList>();
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			
			if(user.equals(userid)) {
				resObj=formcrepo.applicationList(userid, frroCode);
			}else {
				resultMap.put("status", HttpStatus.UNAUTHORIZED.toString());
				resultMap.put("message", "You are not authorized.");
				return new ResponseEntity<Object>(resultMap, HttpStatus.UNAUTHORIZED);	
			}
				
		} 
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {

			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in App list");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<ApplicationList>>(resObj,HttpStatus.OK);
	
	}
	
	
	
	
	
	@GetMapping("/formc/edit-application")
	public ResponseEntity<?> editApplication(HttpServletRequest req, @Valid @RequestParam String appid ) throws Exception {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		
		HashMap<String, String> resultMap = new HashMap<>();
		List<FormC> resObj=new ArrayList<FormC>();
		try {
			String appUser=mainFormRepo.fetchUserId(appid);
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			
			if(user.equals(appUser)) {
				resObj=formcrepo.findByAppIdForEdit(appid);
			}else {
				resultMap.put("status", HttpStatus.UNAUTHORIZED.toString());
				resultMap.put("message", "You are not Authorized user.");
			return new ResponseEntity<Object>(resultMap, HttpStatus.UNAUTHORIZED);
			}
			
		}
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in edit-application");
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<FormC>>(resObj,HttpStatus.OK);
	}
	
	/*
	 * List of Pending Application
	 * Parameter-  PassportNo or Natinality or Both if not provided then display all records.
	 */
	@GetMapping("/formc/pending-appdetail-by-passno-nat")
	public ResponseEntity<?> fetchByPassNoAndNationality(HttpServletRequest req,@RequestParam  String passportNo,
			@RequestParam  String nationality ) throws Exception {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		List<ApplicationList> result=new ArrayList<ApplicationList>();
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			 result=formcrepo.fetchPendingApplicationList(passportNo, nationality,user);
			
		}
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in pending-appdetail");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<ApplicationList>>(result,HttpStatus.OK);
	}
	
	
	/*
	 * List of Pending Application
	 * Parameter-  PassportNo or Natinality or Both if not provided then display all records.
	 */
	
	@GetMapping("/formc/submitted-appdetail-by-passno-nat")
	public ResponseEntity<?> fetchByPassNoAndNationalitySubmitted(HttpServletRequest req,@RequestParam  String passportNo,
			@RequestParam  String nationality ) throws Exception {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		HashMap<String, String> resultMap = new HashMap<>();
		List<ApplicationList> result=new ArrayList<ApplicationList>();
		try {
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			
			 result=formcrepo.fetchSubmittedApplicationList(passportNo, nationality,user);
		}
		catch (ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}
		catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Error in submitted-appdetail");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<List<ApplicationList>>(result,HttpStatus.OK);
	}
	
}
