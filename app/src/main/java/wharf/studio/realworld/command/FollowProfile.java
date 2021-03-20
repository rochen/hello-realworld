package wharf.studio.realworld.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowProfile implements Command<FollowProfileResult>{

	private String username;
	private String fansname;
}
