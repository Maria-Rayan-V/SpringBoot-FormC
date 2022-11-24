package ivfrt.frt.frro.web.checkisvaliduser;
import java.util.HashMap;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ValidUserController {

	@Autowired
	private ValidUserRepository loginRepo;

	@GetMapping("/valid-user/formc/{userid}") // @Min(5)
	public ResponseEntity<?> generateSalt(@PathVariable String userid) {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			ValidUser vu = loginRepo.validUser(userid);
			resultMap.put("salt", vu.getSalt());
			resultMap.put("captcha", vu.getCaptcha());
			return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping("/valid-user/eservice/{userid}") // @Min(5)
	public ResponseEntity<?> generateSaltEservice(@PathVariable @Valid @Email String userid) {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			ValidUser vu = loginRepo.validUser_FromEservice(userid);
			resultMap.put("salt", vu.getSalt());
			resultMap.put("captcha", vu.getCaptcha());
			return new ResponseEntity<Object>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}

	}

	@PostMapping("/app-valid-user") // @Min(5)
	public ResponseEntity<?> generateSaltApp(@RequestBody @Valid ValidUser validuser) {
		HashMap<String, String> resultMap = new HashMap<>();
		//System.out.println("===================" + validuser.getUserId());
		try {
			ValidUser vu = loginRepo.validUser_FromEservice(validuser.getUserId());
			return new ResponseEntity<String>(vu.getSalt(), HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", e.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}

	}
	
	@GetMapping("/valid-user/formc/refresh-captcha/{userid}") // @Min(5)
	public ResponseEntity<?> refreshCaptcha(@PathVariable @Valid String userid) {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			String  captcha= loginRepo.refreshCaptcha(userid);
			return new ResponseEntity<String>(captcha, HttpStatus.OK);
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

	}

}
