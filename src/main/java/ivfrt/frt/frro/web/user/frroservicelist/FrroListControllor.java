package ivfrt.frt.frro.web.user.frroservicelist;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@CrossOrigin (origins = "http://localhost:4200")
public class FrroListControllor {

	
	@Autowired
	private FrroListRepository frroListRepo;
	
	@GetMapping("/frro-list/{natinality}/{state}/{cityDist}")
	public Map<String, Object> fetchFrroList(HttpServletRequest req, 
			@PathVariable @Valid @Pattern(regexp="^[A-Z]{3}",message="Nationality, Invalid Input!")  String natinality,
			@PathVariable @Valid @NotNull(message = "State code required") @Size(max=3)
			@Pattern(regexp = "^[0-9]+$", message = "Invalid State code.") String state,
			@PathVariable @Valid @NotNull(message = "City/District code required.")
			@Pattern(regexp = "^[0-9a-zA-Z]+$", message = "Invalid City/District code.") String cityDist){
		
		return frroListRepo.fetchFrroListByStateAndCityDist(natinality,state,cityDist);
	}
	
	
}
