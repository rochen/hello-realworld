package wharf.studio.realworld.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProfile implements Query<GetProfileResult> {

	private String username;
	private String currentUsername;
}
