package wharf.studio.realworld.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.studio.asylum.api.command.FollowProfile;
import com.studio.asylum.api.command.FollowProfileResult;
import com.studio.asylum.api.command.UnfollowProfile;
import com.studio.asylum.api.command.UnfollowProfileResult;
import com.studio.asylum.api.operation.ProfileOperations;
import com.studio.asylum.api.query.GetProfile;
import com.studio.asylum.api.query.GetProfileResult;

@RestController
public class ProfileApi extends AbstractController implements ProfileOperations {
	
			
	public ResponseEntity<FollowProfileResult> follow(@PathVariable("username") String username) {
		String currentUsername = getAuthentication().getName();
		
		FollowProfile followProfile = FollowProfile.builder()
										.username(username)
										.fansname(currentUsername)
										.build();
		
		FollowProfileResult result = executeCommand(followProfile);	  
	    return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	public ResponseEntity<GetProfileResult> findByUsername(@PathVariable("username") String username) {
		// get anonymousName if not login
		String currentUsername = getAuthentication().getName();
		
		GetProfile getProfile = GetProfile.builder()
										  .username(username)
										  .currentUsername(currentUsername)
										  .build();
										
		GetProfileResult result = performQuery(getProfile);	  
	    return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	@Override
	public ResponseEntity<UnfollowProfileResult> unfollow(String username) {
		String currentUsername = getAuthentication().getName();
		
		UnfollowProfile unfollowProfile = UnfollowProfile.builder()
										.username(username)
										.fansname(currentUsername)
										.build();
		
		UnfollowProfileResult result = executeCommand(unfollowProfile);	  
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
	}
	

}

