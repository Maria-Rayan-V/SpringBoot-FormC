package ivfrt.frt.frro.web.jwt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface TestRepositry {

	List<JwtUserDetails> findUserByName(String username, String salt, boolean isLoginRequest);

	List<JwtUserDetails> findUserByNameFormc(String username, String salt, boolean isLoginRequest);

	boolean checkUserFrom(String username);

	String fetchSalt(String userid);

	String logOut(HttpServletRequest req);

}
