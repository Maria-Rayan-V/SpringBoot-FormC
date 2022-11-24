package ivfrt.frt.frro.web.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	private TestRepositry testRepo;

	private boolean isFormCUser;

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
			String salt = testRepo.fetchSalt(username);
			inMemoryUserList = testRepo.findUserByNameFormc(username, salt, true);//* FORMC REQUEST

		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

	public boolean isFormCUser() {
		return isFormCUser;
	}

	public void setFormCUser(boolean isFormCUser) {
		this.isFormCUser = isFormCUser;
	}

}
