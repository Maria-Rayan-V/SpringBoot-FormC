package ivfrt.frt.frro.web.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtTokenAuthorizationOncePerRequestFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.debug("Authentication Request For '{}'", request.getRequestURL());

		final String requestTokenHeader = request.getHeader(this.tokenHeader);

		String username = null;
		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				logger.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e);
				// throw new IOException("JWT_TOKEN_UNABLE_TO_GET_USERNAME");
			} catch (ExpiredJwtException e) {
				logger.warn("JWT_TOKEN_EXPIRED", e);
				// throw new IOException("JWT_TOKEN_EXPIRED");
			} catch (Exception e) {
				logger.warn("JWT_TOKEN_EXPIRED", e);
				// throw new IOException("JWT_TOKEN_EXPIRED 1");
			}
		} else {
			logger.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
			// throw new IOException();
		}

		logger.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username);
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtInMemoryUserDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		//System.out.println("-----------------------------------" + response.getStatus());

		//addContentSecurityPolicyHeaders(request, response);
		//addCacheOptionsHeaders(request, response);
		//addXSSProtectionHeaders(request, response);
		addTransportSequrityPoloicy(request, response);
		addXFrameOptionsHeaders(request, response);
		addCROSPoloicy(request, response);
		addCROSAllowMethodPoloicy(request, response);
		addCROSAllowHeaderPoloicy(request, response);
		chain.doFilter(request, response);
	}

	private void addContentSecurityPolicyHeaders(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		List<String> cspHeaders = new ArrayList<String>();
		List<String> cspPolicies = new ArrayList<String>();
		String originLocationRef = "'self'";
		String policies = null;

		cspHeaders.add("Content-Security-Policy");
		cspHeaders.add("X-Content-Security-Policy");
		cspHeaders.add("X-WebKit-CSP");

		cspPolicies.add("default-src 'self' 'unsafe-inline' 'unsafe-eval'");
		cspPolicies.add("img-src " + originLocationRef + " data:");

		policies = cspPolicies.toString().replaceAll("(\\[|\\])", "").replaceAll(",", ";").trim();

		StringBuilder policiesBuffer = new StringBuilder(policies);

		// Add policies to all HTTP headers
		for (String header : cspHeaders) {
			httpResponse.setHeader(header, policiesBuffer.toString());
		}
	}

	private void addCacheOptionsHeaders(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// httpResponse.setHeader("Cache-Control", "no-cache, no-store,
		// must-revalidate");
		httpResponse.setHeader("Cache-Control",
				"must-revalidate, no-cache, no-store, private, post-check=0, pre-check=0"); // HTTP 1.1
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setHeader("Referrer-Policy", "origin-when-cross-origin");
	}

	private void addXSSProtectionHeaders(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// Protection against Type 1 Reflected XSS attacks
		httpResponse.addHeader("X-XSS-Protection", "1; mode=block");
	}

	private void addXContentTypeOptionsHeaders(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// Disabling browsers to perform risky mime sniffing
		httpResponse.addHeader("X-Content-Type-Options", "nosniff");
	}

	private void addXFrameOptionsHeaders(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// Disabling browsers to perform risky mime sniffing
		httpResponse.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
	}
	private void addTransportSequrityPoloicy(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
	}
	private void addCROSPoloicy(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	}
	private void addCROSAllowMethodPoloicy(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
	}
	private void addCROSAllowHeaderPoloicy(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept ,Authorization");
	}
}
