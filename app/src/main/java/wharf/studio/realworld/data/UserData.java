package wharf.studio.realworld.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
	private String email;
	private String token;
	private String username;
	private String bio;
	private String image;
}
