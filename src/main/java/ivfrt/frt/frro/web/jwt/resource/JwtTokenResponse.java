package ivfrt.frt.frro.web.jwt.resource;

import java.io.Serializable;

public class JwtTokenResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;

	private final String token;
	private final String designation;
	private final String username;
	private final String useremailId;
	private final String passportNo;
	private final String mobileNo;
	private final String lastLogin;
	private final String acco_code;
	private final String acco_name;
	private final String frro_fro_code;
	private final String frroFroDesc;
	private final String stateCode;
	private final String distCode;
	
	
	private final String acco_address;
	private final String acco_city_district_name;
	private final String acco_state;
	private final String frro_fro_addr;
	private final String frro_fro_city_district_name;
	private final String frro_fro_state;
	private final String isApprovedUser;
	
	/*
	 * public JwtTokenResponse(String token,String designation) { this.token =
	 * token; this.designation=designation;
	 * 
	 * }
	 */
    public JwtTokenResponse(String token,String username,String designation,String useremailId, 
    		String passportNo,String mobileNo,String lastLogin,String acco_code,String frro_fro_code,String stateCode,
    		String distCode,String acco_name,String frro_fro_desc,String acco_address,String acco_city_district_name,String acco_state,String frro_fro_addr,String frro_fro_city_district_name,String frro_fro_state,String isApprovedUser) {
    	this.token = token;
    	this.username = username;
    	this.designation = designation;
    	this.useremailId = useremailId;
    	this.passportNo = passportNo;
    	this.mobileNo = mobileNo;
    	this.lastLogin = lastLogin;
    	this.acco_code=acco_code;
    	this.frro_fro_code=frro_fro_code;
    	this.stateCode=stateCode;
    	this.distCode=distCode;
    	this.acco_name=acco_name;
    	this.frroFroDesc=frro_fro_desc;
    	this.acco_address=acco_address;
		this.acco_city_district_name=acco_city_district_name;
		this.acco_state=acco_state;
		this.frro_fro_addr=frro_fro_addr;
		this.frro_fro_city_district_name=frro_fro_city_district_name;
		this.frro_fro_state=frro_fro_state;
		this.isApprovedUser=isApprovedUser;
    }

    public String getToken() {
        return this.token;
    }
    public String getDesignation() {
        return this.designation;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public String getUseremailId() {
		return useremailId;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public String getAcco_code() {
		return acco_code;
	}

	public String getFrro_fro_code() {
		return frro_fro_code;
	}

	public String getStateCode() {
		return stateCode;
	}

	public String getDistCode() {
		return distCode;
	}

	public String getAcco_name() {
		return acco_name;
	}

	public String getFrroFroDesc() {
		return frroFroDesc;
	}

	public String getAcco_address() {
		return acco_address;
	}

	public String getAcco_city_district_name() {
		return acco_city_district_name;
	}

	public String getAcco_state() {
		return acco_state;
	}

	public String getFrro_fro_addr() {
		return frro_fro_addr;
	}

	public String getFrro_fro_city_district_name() {
		return frro_fro_city_district_name;
	}

	public String getFrro_fro_state() {
		return frro_fro_state;
	}

	public String getIsApprovedUser() {
		return isApprovedUser;
	}

	
	
    
}
