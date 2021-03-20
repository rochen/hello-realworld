package wharf.studio.realworld.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wharf.studio.realworld.data.ProfileData;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetProfileResult {

	private ProfileData profile;
}
