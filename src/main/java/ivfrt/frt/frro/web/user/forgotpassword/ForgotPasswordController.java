package ivfrt.frt.frro.web.user.forgotpassword;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordRepositry forgotPassRepo;
	
	@PostMapping("/forgot-password")
	public String forgotPassword(@Valid @RequestBody ForgotPassword forgotPassword) throws Throwable {
		return forgotPassRepo.forgotPasswordLink(forgotPassword);
	}
}
