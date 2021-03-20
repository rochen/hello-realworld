package wharf.studio.realworld.security;

import java.security.Key;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityService implements UserDetailsService {
	
	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		
    public String createToken(String sub) {
    	JwtBuilder builder = Jwts.builder().signWith(key);
        return builder.setSubject(sub).compact();                          
    }

	public String validateToken(String token) {
    	JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();

    	try {
			String subject = parser.parseClaimsJws(token).getBody().getSubject();
			return subject;
    	} catch (SignatureException | ExpiredJwtException | MalformedJwtException | IllegalArgumentException e) {
    		log.info("invalid jwt token");
    	}    	
    	
    	return null;
    }
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return User.withUsername(username)
				.password(username)
				.authorities(Collections.emptyList())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)					
				.disabled(false)
				.build();
			
	}
	
	public String regex(String header) {
		final String JWT_REGEX = "(^Token) (?<token>[a-zA-Z0-9\\-_]+?\\.[a-zA-Z0-9\\-_]+?\\.[a-zA-Z0-9\\-_]+?)$";
		Pattern pattern = Pattern.compile(JWT_REGEX);
	    Matcher matcher = pattern.matcher(header);
	    System.out.println(matcher.find());
	    String group = matcher.group("token");
	    System.out.println(group);
	    return group;
	}
	
	public static void main(String[] args) {
		SecurityService jwt = new SecurityService();
		
		String token = jwt.createToken("monokol");
		String header = "Token " + token;
		System.out.println(header);
		
		String auth = jwt.regex(header);
		
		String valid = jwt.validateToken(auth);
		System.out.println(valid);
		
	}




    
}
