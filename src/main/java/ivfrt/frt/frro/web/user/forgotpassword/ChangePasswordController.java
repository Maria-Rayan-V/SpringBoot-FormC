package ivfrt.frt.frro.web.user.forgotpassword;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.jwt.JwtTokenUtil;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class ChangePasswordController {
	
	@Autowired
	private ChangePasswordRepositry changepassRepo;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	 @Value("${jwt.http.request.header}")
	  private String tokenHeader;
	
	
	@PostMapping("/formc/change-passward")
	public ResponseEntity<?> changePassword(HttpServletRequest req,@Valid @RequestBody ChangePassword changePassword ) throws Throwable{
		HashMap<String, String> resultMap = new HashMap<>();
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		try {
			
			String tokenStatus=changepassRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			String res= changepassRepo.changePassword(changePassword,user) ;
			resultMap.put("result", res);
			return  new ResponseEntity<Object>(resultMap, HttpStatus.OK);
		} 
		catch(ValidationException ve) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		
		catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Somthing went wrong.");
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		
	}
	
	@PostMapping("/change-passward")
	public ResponseEntity<?> changePasswordEservices(HttpServletRequest req,@RequestBody ChangePassword changePassword ) throws Throwable{
		HashMap<String, String> resultMap = new HashMap<>();
		try {
		String token = req.getHeader(this.tokenHeader).substring(7);
		String user=jwtTokenUtil.getUsernameFromToken(token);
		
		String res= changepassRepo.changePasswordEServices(changePassword,user) ;
		resultMap.put("result", res);
		return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
		}catch(Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
		return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
	}
	

}
