package ivfrt.frt.frro.web.user.frroservicelist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class FrroListAppController {

	@Autowired
	FrroListAppRepository frroListAppRepo;
	
	@GetMapping("/app-frro-list/{natinality}/{state}/{cityDist}")
	public List<FrroListApp> fetchFrroListJWT(HttpServletRequest req, 
			@PathVariable @Valid @Pattern(regexp="^[A-Z]{3}",message="Nationality, Invalid Input!")  String natinality,
			@PathVariable @Valid @NotNull(message = "State code required") @Size(max=3)
			@Pattern(regexp = "^[0-9]+$", message = "Invalid State code.") String state,
			@PathVariable @Valid @NotNull(message = "City/District code required.")
			@Pattern(regexp = "^[0-9a-zA-Z]+$", message = "Invalid City/District code.") String cityDist){
		System.out.println("------------------------------------------------------------");
		System.out.println("getHeaderNames()-----"+req.getHeaderNames());
		System.out.println("req.getLocalAddr()----"+req.getLocalAddr());
		
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
	 System.out.println("Request received time --------------"+dateFormat.format(date));
		
		return frroListAppRepo.fetchFrroListByStateAndCityDistAPP(natinality,state,cityDist);
	}
	
	@GetMapping("/masters/app-frro-list/{natinality}/{state}/{cityDist}")
	public List<FrroListApp> fetchFrroList(HttpServletRequest req, @PathVariable String natinality,@PathVariable String state,@PathVariable String cityDist){
		System.out.println("------------------------------------------------------------");
		System.out.println("getHeaderNames()-----"+req.getHeaderNames());
		System.out.println("req.getLocalAddr()----"+req.getLocalAddr());
		
		Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
	 System.out.println("Request received time --------------"+dateFormat.format(date));
		
		return frroListAppRepo.fetchFrroListByStateAndCityDistAPP(natinality,state,cityDist);
	}
	
}
