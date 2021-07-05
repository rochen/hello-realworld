package wharf.studio.realworld.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import com.studio.asylum.api.command.RegisterUser;
import com.studio.asylum.api.command.UpdateUser;
import com.studio.asylum.api.data.UserData;
import com.studio.asylum.db.tables.pojos.User;
import com.studio.asylum.db.tables.records.UsersRecord;

import wharf.studio.realworld.security.SecurityService;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
	
	@Autowired
	SecurityService securityService;
	
	public abstract User record2pojo(UsersRecord record);
	
	@Mapping(target = "value1", ignore = true)
	@Mapping(target = "value2", ignore = true)
	@Mapping(target = "value3", ignore = true)
	@Mapping(target = "value4", ignore = true)
	@Mapping(target = "value5", ignore = true)
	@Mapping(target = "value6", ignore = true)
	public abstract UsersRecord pojo2record(User user);
	
	@Mapping(target = "bio", ignore = true)
	@Mapping(target = "image", ignore = true)
	@Mapping(target = "userId", ignore = true)
	public abstract User command2pojo(RegisterUser user);

	@Mapping(target = "userId", ignore = true)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)	
  	public abstract void command2pojo(UpdateUser user, @MappingTarget User existingUser);

	@Mapping(target = "token", source = "username", qualifiedByName = "token")
	public abstract UserData pojo2data(User user);
	
	
	/*
	 * Named customized mapping
	 */
	
	@Named("token")
	public String getToken(String username) {
		return securityService.createToken(username);
	}

}
