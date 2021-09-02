package ws.license.exam.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration 
{
	private String driverClassName;
	private String url;
	private String username;
	private String password;

	
	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Profile("dev")
	@Bean
	public String devDatabaseConnect()
	{
		System.out.println("DB connection for DEV .. v 1.0.4_chanon.kha");
		System.out.println(driverClassName);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		return "DB connection for DEV";
	}
	
	@Profile("uat")
	@Bean
	public String uatDatabaseConnect()
	{
		System.out.println("DB connection for UAT");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB connection for UAT";
	}
	
	@Profile("prod")
	@Bean
	public String prodDatabaseConnect()
	{
		System.out.println("DB connection for PROD");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB connection for PROD";
	}
}
