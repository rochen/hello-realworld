package wharf.studio.realworld.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


public class AuthorizationFilter extends OncePerRequestFilter {
	
	private static final String EXPECTED_AUTHORIZATION = "(^Bearer) (?<token>[a-zA-Z0-9\\-_]+?\\.[a-zA-Z0-9\\-_]+?\\.[a-zA-Z0-9\\-_]+?)$";
	
	@Autowired
	private SecurityService securityService;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {	    	       

		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

		if(authorization == null) {
	    	filterChain.doFilter(request, response);
            return;			
		}
		
		Pattern pattern = Pattern.compile(EXPECTED_AUTHORIZATION);
	    Matcher matcher = pattern.matcher(authorization);
	    
	    if(matcher.find() == false) {
	    	filterChain.doFilter(request, response);
            return;
	    }

        // Get JWT token and validate
        final String token = matcher.group("token");
        String subject = securityService.validateToken(token);
        if (subject == null) {
        	filterChain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        UserDetails userDetails = securityService.loadUserByUsername(subject);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        filterChain.doFilter(request, response);		
	}

}
