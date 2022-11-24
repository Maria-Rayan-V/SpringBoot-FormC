package ivfrt.frt.frro.web.jwt.resource;

import java.io.Serializable;
import org.springframework.lang.NonNull;


public class  JwtTokenRequest implements Serializable {
  
  private static final long serialVersionUID = -5616176897013108345L;
 
  	private String username;
  	@NonNull
    private String password;

  	private String requestFrom;
  	
  	private String captcha;
  	
  
    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password, String reqestFrom,String captcha) {
        this.setUsername(username);
        this.setPassword(password);
        this.setRequestFrom(reqestFrom);
        this.setCaptcha(captcha);
       
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}



	
    
}

