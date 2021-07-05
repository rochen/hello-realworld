package wharf.studio.realworld.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.studio.asylum.api.data.ProfileData;
import com.studio.asylum.db.tables.pojos.User;
import com.studio.asylum.db.tables.pojos.UserFollow;
import com.studio.asylum.db.tables.records.UserFollowsRecord;

@Mapper(componentModel = "spring")
public abstract class ProfileMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userId", source = "user.userId")
	@Mapping(target = "fanId", source = "fan.userId")
	public abstract UserFollow follow(User user, User fan);

	public abstract ProfileData pojo2data(User user, boolean following);

	@Mapping(target = "value1", ignore = true)
	@Mapping(target = "value2", ignore = true)
	@Mapping(target = "value3", ignore = true)
	public abstract UserFollowsRecord pojo2record(UserFollow userFollow);
}
