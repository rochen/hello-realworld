package wharf.studio.realworld.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studio.asylum.db.tables.pojos.User;

import wharf.studio.realworld.data.UserData;
import wharf.studio.realworld.mapper.UserMapper;
import wharf.studio.realworld.query.GetCurrentUser;
import wharf.studio.realworld.query.GetCurrentUserResult;
import wharf.studio.realworld.query.QueryHandler;
import wharf.studio.realworld.repository.UserRepository;

@Component
public class GetCurrentUserHandler implements QueryHandler<GetCurrentUserResult, GetCurrentUser> {

	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	@Override
	public GetCurrentUserResult handle(GetCurrentUser getCurrentUser) {
		String username = getCurrentUser.getUsername();
		
		Optional<User> user = repository.findByUsername(username);

		UserData userData = mapper.pojo2data(user.get());		
		
		GetCurrentUserResult result = GetCurrentUserResult.builder()
											.user(userData)
											.build();

		return result;
	}

}
