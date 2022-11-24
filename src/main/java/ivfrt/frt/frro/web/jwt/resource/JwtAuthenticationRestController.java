package ivfrt.frt.frro.web.jwt.resource;

import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ivfrt.frt.frro.web.checkisvaliduser.ValidUserRepository;
import ivfrt.frt.frro.web.jwt.JwtTokenUtil;
import ivfrt.frt.frro.web.jwt.JwtUserDetails;
import ivfrt.frt.frro.web.jwt.TestRepositry;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JwtAuthenticationRestController {

  @Value("${jwt.http.request.header}")
  private String tokenHeader;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  
  @Autowired
  private UserDetailsService jwtInMemoryUserDetailsService;
  @Autowired
  private UserDetailsService jwtInMemoryUserDetailsService1;
  @Autowired
  private UserDetailsService jwtInMemoryUserDetailsService2;
  

  @Autowired
  private ValidUserRepository validrepo;
  
  @Autowired
  TestRepositry testrepo;
  
  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(HttpServletRequest req,@Valid @RequestBody JwtTokenRequest authenticationRequest)
      throws AuthenticationException {
	 
	  try {
		  String token1 = req.getHeader(this.tokenHeader);
		  System.out.println(token1);
	  
	  Object obj=null;
	  
	  //Captcha Check
	  
	  String db_Captcha="";
		 try {
		  db_Captcha = validrepo.fetchCaptcha(authenticationRequest.getUsername());
		 }catch(Exception e){
			 throw new ValidationException("Invalid Captcha");
		 }
		 if(!db_Captcha.equals(authenticationRequest.getCaptcha())) {
			  throw new ValidationException("Invalid Captcha");
		 }
		//Captcha Check end
		 authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		 obj= jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
     final UserDetails userDetails=(UserDetails) obj;
     JwtUserDetails jwtUserDetail=(JwtUserDetails) obj;
     
    final String token = jwtTokenUtil.generateToken(userDetails);

    if(authenticationRequest.getRequestFrom()!=null && authenticationRequest.getRequestFrom().equals("frmcapp")){
    	validrepo.deleteSaltData_fr_users_salt(jwtUserDetail.getUsername());
    }
    
    //saveTokenDetails(token,jwtUserDetail.getUsername())
    
    return ResponseEntity.ok(new JwtTokenResponse(token,jwtUserDetail.getUsername(),jwtUserDetail.getDesignation(),
    		jwtUserDetail.getUseremailId(),encodePass(jwtUserDetail.getPassportNo()),encodePass(jwtUserDetail.getMobileNo()),
    		jwtUserDetail.getLastLogin(),jwtUserDetail.getAcco_code(),jwtUserDetail.getFrroFroCode(),jwtUserDetail.getStateCode(),jwtUserDetail.getDistCode(),
    		jwtUserDetail.getAcco_name(),jwtUserDetail.getFrroFroDesc(),jwtUserDetail.getAcco_address(),
    		jwtUserDetail.getAcco_city_district_name(),jwtUserDetail.getAcco_state(),jwtUserDetail.getFrro_fro_addr(),jwtUserDetail.getFrro_fro_city_district_name(),jwtUserDetail.getFrro_fro_state(),jwtUserDetail.getIsUserApproved()));
	  }
	  
	  catch(ValidationException ve) {
			  
			    HashMap<String, String> resultMap = new HashMap<>();
			    resultMap.put("status", "error");
				resultMap.put("message", "Invalid Captcha");
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
		  }
	  catch(Exception e) {
		  if(e.getMessage().equals("INVALID_CREDENTIALS")) {
			  //throw new AuthenticationException(e.getMessage(), e); 
			   HashMap<String, String> resultMap = new HashMap<>();
			    resultMap.put("status", "error");
				resultMap.put("message", "INVALID CREDENTIALS");
				return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
			  
		  }else {
			  throw new AuthenticationException("Exception! Something went wrong.", e); 
		  }
		  
		  }
	  
	  }

  private String encodePass(String params1) {
	  if(params1!=null) {
	    	if(params1.length()>2) {
	    		return "XXXX"+params1.substring(params1.length()-2, params1.length());
	    	}else {
	    		return params1;
	    	}
	     } else {
	    	 return null;
	     }
  }
  
  
  
  @RequestMapping(value = "${jwt.refresh.token.uri}", method = RequestMethod.GET)
  public ResponseEntity<?> refreshAndGetAuthenticationToken(@Valid HttpServletRequest request) {
    String authToken = request.getHeader(tokenHeader);
    final String token = authToken.substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    JwtUserDetails user = (JwtUserDetails) jwtInMemoryUserDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token)) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtTokenResponse(refreshedToken,user.getUsername(),user.getDesignation(),
    		  user.getUseremailId(),user.getPassportNo(),user.getMobileNo(),user.getLastLogin(),
    		  user.getAcco_code(),user.getFrroFroCode(),user.getStateCode(),user.getDistCode(),user.getAcco_name(),user.getFrroFroDesc(),user.getAcco_address(),
    		  user.getAcco_city_district_name(),user.getAcco_state(),user.getFrro_fro_addr(),user.getFrro_fro_city_district_name(),user.getFrro_fro_state(),user.getIsUserApproved()));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }

  //Logout Map In Main Form C Controller class
 
	/*
	 * @GetMapping("/formc/logout") public ResponseEntity<?>
	 * logout(HttpServletRequest req) throws Exception { HashMap<String, String>
	 * resultMap = new HashMap<>(); try { String result=testrepo.logOut(req);
	 * 
	 * resultMap.put(result, HttpStatus.OK.toString());
	 * 
	 * return new ResponseEntity<Object>(resultMap,HttpStatus.OK);
	 * 
	 * }catch (Exception e) { // TODO: handle exception resultMap.put("status",
	 * HttpStatus.BAD_REQUEST.toString()); resultMap.put("massege", e.getMessage());
	 * return new ResponseEntity<Object>(resultMap, HttpStatus.EXPECTATION_FAILED);
	 * }
	 * 
	 * }
	 */
  
  
  
  @ExceptionHandler({ AuthenticationException.class })
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  }

  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new AuthenticationException("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException("INVALID_CREDENTIALS", e);
    }
  }
}

