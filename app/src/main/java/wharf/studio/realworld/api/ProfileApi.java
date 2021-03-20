package wharf.studio.realworld.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wharf.studio.realworld.command.FollowProfile;
import wharf.studio.realworld.command.FollowProfileResult;
import wharf.studio.realworld.query.GetProfile;
import wharf.studio.realworld.query.GetProfileResult;

@RestController
@RequestMapping(path = "profiles/{username}")
public class ProfileApi extends AbstractController {
	
			
	@PostMapping (path = "follow")
	public ResponseEntity<FollowProfileResult> follow(@AuthenticationPrincipal UserDetails userDetails,
													  @PathVariable("username") String username) {

		String fansname = userDetails.getUsername();
		FollowProfile followProfile = FollowProfile.builder()
										.username(username)
										.fansname(fansname)
										.build();
		
		FollowProfileResult result = executeCommand(followProfile);	  
	    return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@GetMapping
	public ResponseEntity<GetProfileResult> getProfile(@AuthenticationPrincipal UserDetails userDetails,
													  @PathVariable("username") String username) {

		String currentUsername = userDetails == null? null: userDetails.getUsername();
		GetProfile getProfile = GetProfile.builder()
										  .username(username)
										  .currentUsername(currentUsername)
										  .build();
										
		GetProfileResult result = performQuery(getProfile);	  
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	

}

