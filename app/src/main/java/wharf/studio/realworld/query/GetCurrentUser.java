package wharf.studio.realworld.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCurrentUser implements Query<GetCurrentUserResult> {
	
	private String username;

}
