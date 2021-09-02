
package ws.license.exam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

@SpringBootApplication

public class LicenseApplication extends SpringBootServletInitializer/*implements CommandLineRunner*/{
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(LicenseApplication.class);
	    }

	public static void main(String[] args) {
		
		
//      int l = args.length;
//      System.out.println("ARGS length : " + l);
//      for (String arg : args)
//          System.out.println("ARG : " + arg);
//      String[] tempArgs = new String[args.length + 1];
//      int i = 0;
//      for (i = 0; i < args.length; i++) {
//          tempArgs[i] = args[i];
//      }
//      tempArgs[i++] = "--spring.config.location=file:../application.yml";  
//      SpringApplication.run(LicenseApplication.class, tempArgs);
//		
		SpringApplication.run(LicenseApplication.class, args);
	}

}
