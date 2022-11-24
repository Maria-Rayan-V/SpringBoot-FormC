package ivfrt.frt.frro.web.formc.arrivaldeparture;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.formc.mainform.FormC_MainFormRepository;
import ivfrt.frt.frro.web.jwt.JwtTokenUtil;

@Validated
@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class ArrivalDepartureDetailController {

	@Autowired
	ArrivalDepartureDetailsRepository depdetail;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Value("${jwt.http.request.header}")
	private String tokenHeader;
	@Autowired
	FormC_MainFormRepository mainFormRepo;
	
	@PostMapping("/formc/departdetail")
	public ResponseEntity<?> insertDepartDetail(HttpServletRequest req,@Valid @RequestBody DepartureDetails departureDetails)
			throws Exception {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			String token = req.getHeader(this.tokenHeader).substring(7);
			String user = jwtTokenUtil.getUsernameFromToken(token);
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			departureDetails.setEntered_by(user);
			
			
			return new ResponseEntity<DepartureDetails>(depdetail.insertDepartDetail(departureDetails), HttpStatus.OK);
		} 
		catch (ValidationException ve) {
			// TODO: handle exception
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		
		catch (Exception e) {
			// TODO: handle exception
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Exception in /formc/departdetail");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);

		}

	}

	@PostMapping("/formc/get-arrivaldetail")
	public ResponseEntity<?> fetchArrivalDetal(HttpServletRequest req, @Valid @RequestBody ArrivalDetail arrivalDetail) throws Exception {
		HashMap<String, String> resultMap = new HashMap<>();
		try {
			String token = req.getHeader(this.tokenHeader).substring(7);
			String user = jwtTokenUtil.getUsernameFromToken(token);
			String tokenStatus=mainFormRepo.checkForValidToken(token, user);
			if(tokenStatus.equals("INVALID")) {
				throw new ValidationException("Please Re-login.");
			}
			
			List<ArrivalDetail> result = depdetail.findArrivalDetail(arrivalDetail);

			return new ResponseEntity<List<ArrivalDetail>>(result, HttpStatus.OK);

		} 
		catch (ValidationException ve) {
			// TODO: handle exception
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", ve.getMessage());
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}
		catch (Exception e) {
			// TODO: handle exception
			resultMap.put("status", HttpStatus.BAD_REQUEST.toString());
			resultMap.put("message", "Exception in /formc/get-arrivaldetail");
			return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		}

	}

}
