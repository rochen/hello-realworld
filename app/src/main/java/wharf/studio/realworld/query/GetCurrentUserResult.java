package wharf.studio.realworld.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wharf.studio.realworld.data.UserData;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetCurrentUserResult {
	private UserData user;
}
