package ivfrt.frt.frro.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ivfrt.frt.frro.web.utilities.FileStorageProperties;
import utilities.CaptchaGenServlet;

//@ComponentScan({"ivfrt.frt.frro"})
@SpringBootApplication
@ServletComponentScan
//for war

@EnableConfigurationProperties({ FileStorageProperties.class })

public class RestfulWebServicesApplication extends SpringBootServletInitializer {

//For jar
//public class RestfulWebServicesApplication {	
	// Register Servlet
	/*
	 * @Bean public ServletRegistrationBean servletRegistrationBean() {
	 * ServletRegistrationBean bean = new ServletRegistrationBean( new MyServlet(),
	 * "/register/abc"); return bean; }
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new CaptchaGenServlet(), "/captcha");
		return bean;
	}

	// CaptchaGenServlet
	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
				.allowedOrigins("http://localhost:4200")
				.allowedHeaders("*");		
				}
		};
	}
	
}
