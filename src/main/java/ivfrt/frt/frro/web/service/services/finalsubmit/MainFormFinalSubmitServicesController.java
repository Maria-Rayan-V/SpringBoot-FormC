package ivfrt.frt.frro.web.service.services.finalsubmit;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.jwt.JwtTokenUtil;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class MainFormFinalSubmitServicesController {
	
	@Autowired
	MainFormFinalSubmitRepository mainformfinalsubmitrepo;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;
	
	 @PostMapping("/final-submit")
	  public  ResponseEntity<?> fetchLTVCategory(HttpServletRequest req, @Valid @RequestBody  MainFormFinalSubmit mainformfinalsubmit){
		 HashMap<String, String> resultMap = new HashMap<>();
		 MainFormFinalSubmit resObj=new MainFormFinalSubmit();
		 try {
			
			String token = req.getHeader(this.tokenHeader).substring(7);
			String user=jwtTokenUtil.getUsernameFromToken(token);
			String appUser=mainformfinalsubmitrepo.fetchUserId(mainformfinalsubmit.getApplication_id());
				if(user.equalsIgnoreCase(appUser)) {
					resObj= mainformfinalsubmitrepo.finalSubmit(mainformfinalsubmit);
				}else {
					throw new Exception("Invalid Application Id/User.");
				}
				}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("errors", "error in final submission");
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		 
	return new ResponseEntity<MainFormFinalSubmit>(resObj,HttpStatus.OK);
	 }
	

}
