package wharf.studio.realworld.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studio.asylum.api.command.LoginUser;
import com.studio.asylum.api.command.LoginUserResult;
import com.studio.asylum.api.command.RegisterUser;
import com.studio.asylum.api.command.RegisterUserResult;
import com.studio.asylum.api.command.UpdateUser;
import com.studio.asylum.api.command.UpdateUserResult;
import com.studio.asylum.api.operation.UserOperations;
import com.studio.asylum.api.query.GetCurrentUser;
import com.studio.asylum.api.query.GetCurrentUserResult;


@RestController
public class UsersApi extends AbstractController implements UserOperations {
			

	public ResponseEntity<LoginUserResult> login(@Valid @RequestBody LoginUser loginUser) {
		LoginUserResult result = executeCommand(loginUser);		  
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	public ResponseEntity<RegisterUserResult> register(@Valid @RequestBody RegisterUser registerUser) {
	    RegisterUserResult result = executeCommand(registerUser);		  
	    return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	
	public ResponseEntity<GetCurrentUserResult> current() {
		String currentUsername = getAuthentication().getName();
		
		GetCurrentUser getCurrentUser = GetCurrentUser.builder()
										.username(currentUsername)
										.build();
			
		GetCurrentUserResult currentUserResult = performQuery(getCurrentUser);
	    return ResponseEntity.status(HttpStatus.OK).body(currentUserResult);
	}
	
	public ResponseEntity<UpdateUserResult> update(@Valid @RequestBody UpdateUser updateUser) {
		String currentUsername = getAuthentication().getName();
		
		UpdateUserResult updateUserResult = executeCommand(updateUser.withCurrentUsername(currentUsername));
		
		return ResponseEntity.status(HttpStatus.OK).body(updateUserResult);
		
	}
	
	@GetMapping(path = "/test")
	public String test() {
		return "Hello";
	}

}

