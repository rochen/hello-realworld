package wharf.studio.realworld.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wharf.studio.realworld.command.UpdateUser;
import wharf.studio.realworld.command.UpdateUserResult;
import wharf.studio.realworld.query.GetCurrentUser;
import wharf.studio.realworld.query.GetCurrentUserResult;

@RestController
@RequestMapping(path = "/user")
public class UserApi extends AbstractController {
			
	@GetMapping
	public ResponseEntity<GetCurrentUserResult> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
		
		String username = userDetails.getUsername();
		
		GetCurrentUser getCurrentUser = GetCurrentUser.builder()
										.username(username)
										.build();
			
		GetCurrentUserResult currentUserResult = performQuery(getCurrentUser);
	    return ResponseEntity.status(HttpStatus.OK).body(currentUserResult);
	}
	
	@PutMapping
	public ResponseEntity<UpdateUserResult> updateUser(@AuthenticationPrincipal UserDetails userDetails, 
													   @Valid @RequestBody UpdateUser updateUser) {
		
		String currentUsername = userDetails.getUsername();
		UpdateUserResult updateUserResult = executeCommand(updateUser.withCurrentUsername(currentUsername));
		
		return ResponseEntity.status(HttpStatus.OK).body(updateUserResult);
		
	}
	

}

