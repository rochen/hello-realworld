package wharf.studio.realworld.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wharf.studio.realworld.command.LoginUser;
import wharf.studio.realworld.command.LoginUserResult;
import wharf.studio.realworld.command.RegisterUser;
import wharf.studio.realworld.command.RegisterUserResult;

@RestController
@RequestMapping(path = "/users")
public class UsersApi extends AbstractController {
			
	@PostMapping
	public ResponseEntity<RegisterUserResult> registration(@Valid @RequestBody RegisterUser registerUser) {
	    RegisterUserResult result = executeCommand(registerUser);		  
	    return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PostMapping(path = "/login")
	public ResponseEntity<LoginUserResult> login(@Valid @RequestBody LoginUser loginUser) {
		LoginUserResult result = executeCommand(loginUser);		  
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	@GetMapping(path = "/test")
	public String test() {
		return "Hello";
	}
}

