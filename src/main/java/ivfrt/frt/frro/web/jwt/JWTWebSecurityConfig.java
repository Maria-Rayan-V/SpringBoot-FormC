package ivfrt.frt.frro.web.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;
    
    @Autowired
    private XssFilter xssFilter;

    @Value("${jwt.get.token.uri}")
    private String authenticationPath;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtInMemoryUserDetailsService)
            .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	 httpSecurity
	        .headers()
	        .addHeaderWriter(new StaticHeadersWriter("Referrer-Policy","origin-when-cross-origin"));
    	 //httpSecurity
	        //.headers()
	      //  .httpStrictTransportSecurity() //Http Strict transport Sequrity.
	    	//.includeSubDomains(true)
	    	//.maxAgeInSeconds(31536000);
     httpSecurity
	        .headers()
	        .xssProtection();
     httpSecurity
	        .headers()
	        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"));
		
		  httpSecurity 
		  .headers()
		  .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin","http://localhost:4200") 
		  );
		 
    	httpSecurity
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers(authenticationPath,"/captcha1","/masters/**","/register/**", 
            		"/valid-user/**","/visa/**","/actuator/**").permitAll()
            .anyRequest().authenticated();
     
       httpSecurity
            .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
     //  httpSecurity
      // .addFilterAfter(xssFilter, UsernamePasswordAuthenticationFilter.class);
        
        httpSecurity
            .headers()
            .frameOptions().sameOrigin()  //H2 Console Needs this setting
            .cacheControl(); //disable caching
       
        httpSecurity
	        .headers()
	        .xssProtection()
	        .and()
	        .contentSecurityPolicy("script-src 'self'")
        ;
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
            .ignoring()
            .antMatchers(
                HttpMethod.POST,
                authenticationPath
            )
          /*  .antMatchers(HttpMethod.OPTIONS, "/**")
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.GET,
                "/" //Other Stuff You want to Ignore /captcha-servlet
            )*/
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.POST,
                "/register/**" //Other Stuff You want to Ignore /captcha-servlet
            )
            .and()
            .ignoring()
            .antMatchers(HttpMethod.GET,"/captcha1")
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.GET,
                "/register/**" //Other Stuff You want to Ignore /captcha-servlet
            )
            .and()
            .ignoring()
            .antMatchers( HttpMethod.POST,"/app-valid-user/**")
            .and()
            .ignoring()
            .antMatchers( HttpMethod.GET,"/visa/**")
            .and()
            .ignoring()
            .antMatchers( HttpMethod.GET,"/valid-user/**")
            .and()
            .ignoring()
            .antMatchers( HttpMethod.GET,"/actuator/**")
           // .and()
           // .ignoring()
            //.antMatchers( HttpMethod.POST,"/actuator/**")
            //.and()
            //.ignoring()
           // .antMatchers( HttpMethod.OPTIONS,"/actuator/**")
            .and()
            .ignoring()
            .antMatchers( HttpMethod.GET,"/masters/**")
            .and()
            .ignoring()
            ;
    }
    
	/*
	 * @Bean public FilterRegistrationBean xssPreventFilter() {
	 * FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	 * 
	 * registrationBean.setFilter(new XssFilter());
	 * registrationBean.addUrlPatterns("/*");
	 * 
	 * return registrationBean; }
	 */
}

