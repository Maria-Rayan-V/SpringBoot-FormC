package ivfrt.frt.frro.web.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 5155720064139820502L;

	private final Long id;
	private final String username;
	private final String password;
	private final String designation;

	private final String useremailId;
	private final String passportNo;
	private final String mobileNo;
	private final String lastLogin;

	private final String acco_code;
	private final String acco_name;
	private final String frroFroCode;
	private final String frroFroDesc;

	private final String stateCode;
	private final String distCode;
	
	private final String acco_address;
	private final String acco_city_district_name;
	private final String acco_state;
	private final String frro_fro_addr;
	private final String frro_fro_city_district_name;
	private final String frro_fro_state;
	private final String isUserApproved;
	

	private final Collection<? extends GrantedAuthority> authorities;

	/*
	 * public JwtUserDetails(Long id, String username, String password,String
	 * designation,String useremailId, String passportNo,String mobileNo,String
	 * lastLogin, String role) { this.id = id; this.username = username;
	 * this.password = password; List<SimpleGrantedAuthority> authorities = new
	 * ArrayList<SimpleGrantedAuthority>(); authorities.add(new
	 * SimpleGrantedAuthority(role)); this.designation = designation;
	 * this.useremailId = useremailId; this.passportNo = passportNo; this.mobileNo =
	 * mobileNo; this.lastLogin = lastLogin;
	 * 
	 * this.authorities = authorities; }
	 */

	// Name, Login Id, Phone Number, Passport Number, Last Login Detail

	public JwtUserDetails(String username, String password, String useremailId, String passportNo, String mobileNo,
			String lastLogin,String acco_name,String frroFroDesc,String acco_address,String acco_city_district_name,String acco_state,String frro_fro_addr,String frro_fro_city_district_name,String frro_fro_state,String isUserApproved) {
		super();
		this.id = null;
		this.username = useremailId.toUpperCase(); // This Is require For Match Jwt Auth User
		this.password = password;
		this.designation = "";
		this.useremailId = username;
		this.passportNo = passportNo;
		this.mobileNo = mobileNo;
		this.lastLogin = lastLogin;
		this.acco_code = "";
		this.frroFroCode = "";
		this.stateCode = "";
		this.distCode = "";
		this.authorities = null;
		this.acco_name="";
		this.frroFroDesc="";
		this.acco_address="";
		this.acco_city_district_name="";
		this.acco_state="";
		this.frro_fro_addr="";
		this.frro_fro_city_district_name="";
		this.frro_fro_state="";
		this.isUserApproved="";
	}

	public JwtUserDetails(Long id, String username, String password, String designation, String useremailId,
			String passportNo, String mobileNo, String lastLogin, String role, String acco_code, String frroFroCode,
			String stateCode, String distCode,String acco_name,String frroFroDesc,String acco_address,String acco_city_district_name,
			String acco_state,String frro_fro_addr,String frro_fro_city_district_name,String frro_fro_state,String isUserApproved) {
		this.id = id;
		this.username = username;
		this.password = password;
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		this.designation = designation;
		this.useremailId = useremailId;
		this.passportNo = passportNo;
		this.mobileNo = mobileNo;
		this.lastLogin = lastLogin;
		this.stateCode = stateCode;
		this.distCode = distCode;
		this.authorities = authorities;
		this.acco_code = acco_code;
		this.frroFroCode = frroFroCode;
		this.acco_name=acco_name;
		this.frroFroDesc=frroFroDesc;
		this.acco_address=acco_address;
		this.acco_city_district_name=acco_city_district_name;
		this.acco_state=acco_state;
		this.frro_fro_addr=frro_fro_addr;
		this.frro_fro_city_district_name=frro_fro_city_district_name;
		this.frro_fro_state=frro_fro_state;
		this.isUserApproved=isUserApproved;
	}

	/*
	 * public JwtUserDetails(String username, String password,String
	 * designation,String useremailId, String passportNo,String mobileNo,String
	 * lastLogin) { super(); this.id = null; this.username = username; this.password
	 * = password; this.designation = designation; this.useremailId = useremailId;
	 * this.passportNo = passportNo; this.mobileNo = mobileNo; this.lastLogin =
	 * lastLogin; this.authorities = null; }
	 */

	public JwtUserDetails(String username, String password, String designation, String useremailId, String passportNo,
			String mobileNo, String lastLogin, String acco_code, String frroFroCode, String stateCode,
			String distCode,String acco_name,String frroFroDesc,String acco_address,String acco_city_district_name,
			String acco_state,String frro_fro_addr,String frro_fro_city_district_name,String frro_fro_state,String isUserApproved) {
		super();
		this.id = null;
		this.username = username;
		this.password = password;
		this.designation = designation;
		this.useremailId = useremailId;
		this.passportNo = passportNo;
		this.mobileNo = mobileNo;
		this.lastLogin = lastLogin;
		this.authorities = null;
		this.stateCode = stateCode;
		this.distCode = distCode;
		this.acco_code = acco_code;
		this.frroFroCode = frroFroCode;
		this.acco_name=acco_name;
		this.frroFroDesc=frroFroDesc;
		this.acco_address=acco_address;
		this.acco_city_district_name=acco_city_district_name;
		this.acco_state=acco_state;
		this.frro_fro_addr=frro_fro_addr;
		this.frro_fro_city_district_name=frro_fro_city_district_name;
		this.frro_fro_state=frro_fro_state;
		this.isUserApproved=isUserApproved;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getDesignation() {
		return designation;
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

	public String getStateCode() {
		return stateCode;
	}

	public String getDistCode() {
		return distCode;
	}

	public String getFrroFroCode() {
		return frroFroCode;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getAcco_name() {
		return acco_name;
	}

	public String getFrroFroDesc() {
		return frroFroDesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getIsUserApproved() {
		return isUserApproved;
	}


}
