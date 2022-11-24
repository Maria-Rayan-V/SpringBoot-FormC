package ivfrt.frt.frro.web.utilities;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstantUtil {
	
	public static final String DATE_DDMMYYYY = "(^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$)";
	
	public static final String NAT_CODE="(^[A-Z]{3})";
	
	public static final String APPID="(^[0-9a-zA-Z]{12})";
	
	public static final String ALPHA_NUM = "([a-zA-Z0-9]+$)";

	public static final String ALPHA_NUM_SP = "([a-zA-Z0-9\\s]+$)";
	
	public static final String ALPHA_SP = "(^[a-zA-Z ]+$)";
	
	//~ ` & + = ] [ | ; <> ' not allowed.
	public static final String  REMARK="(^[0-9a-zA-Z\\s\\r\\n!@#$%^*)(_\\-\\}\\{\\\"\\:\\,\\.\\?\\\\\\/]+$)";
	
	public static final String SINGLE_CHAR="(^[A-Z]{1})";
	
	public static final String ERR_MSG="You have entred wrong entry for Input :";
	
	//Form C Specific 
	public static final String ACCO_CODE="(^[0-9A-Z]{4})";
	
	public static final String PASSWORD="(^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$)";
	
	public static final String NUM="^[0-9]+$";

}
