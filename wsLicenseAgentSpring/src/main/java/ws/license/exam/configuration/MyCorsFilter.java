package ws.license.exam.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class MyCorsFilter implements Filter {


@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    response.setHeader("Access-Control-Allow-Origin", "*"/*request.getHeader("Origin")*/);
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
   // response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
    response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");

 
//    response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");		
//	response.setHeader("Content-Security-Policy", "default-src 'self'; frame-ancestors 'none'; reflected-xss block");
//	response.setHeader("X-XSS-Protection", "1; mode=block"); // on
//	response.setHeader("X-Frame-Options", "DENY"); // DENY, SAMEOR
//	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // HTTP 1.1.
//	response.setHeader("Access-Control-Allow-Origin", "*");
//    response.setHeader("Access-Control-Allow-Credentials", "true");
//	response.setHeader("Access-Control-Allow-Methods", "*");
//	response.setHeader("Access-Control-Max-Age", "3600");
//   response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
//
//    if ("OPTIONS".equals(request.getMethod())) {
//        response.setStatus(HttpServletResponse.SC_OK);
//    } else { 
//    	 chain.doFilter(req, res);
//    }
    
   chain.doFilter(req, res);
   
}

@Override
public void init(FilterConfig filterConfig) {
}

@Override
public void destroy() {
}

//@Override
//public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
//	// TODO Auto-generated method stub
//	
//}

}