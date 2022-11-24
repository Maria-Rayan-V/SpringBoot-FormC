package ivfrt.frt.frro.web.jwt;

public class FormC_FR_UserDetails {

	private String userid;
	private String password;
	private String e_mail_id;
	private String phone_no;
	private String inactive_date;
	private String frro_fro_code;
	private String active;
	
	
	
	public FormC_FR_UserDetails(String userid, String password, String e_mail_id, String phone_no, String inactive_date,
			String frro_fro_code, String active) {
		super();
		this.userid = userid;
		this.password = password;
		this.e_mail_id = e_mail_id;
		this.phone_no = phone_no;
		this.inactive_date = inactive_date;
		this.frro_fro_code = frro_fro_code;
		this.active = active;
	}
	public FormC_FR_UserDetails() {
		// TODO Auto-generated constructor stub
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getE_mail_id() {
		return e_mail_id;
	}
	public void setE_mail_id(String e_mail_id) {
		this.e_mail_id = e_mail_id;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getInactive_date() {
		return inactive_date;
	}
	public void setInactive_date(String inactive_date) {
		this.inactive_date = inactive_date;
	}
	public String getFrro_fro_code() {
		return frro_fro_code;
	}
	public void setFrro_fro_code(String frro_fro_code) {
		this.frro_fro_code = frro_fro_code;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	
}
