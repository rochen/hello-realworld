package wharf.studio.realworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studio.asylum.api.command.CommandHandler;
import com.studio.asylum.api.command.UpdateUser;
import com.studio.asylum.api.command.UpdateUserResult;
import com.studio.asylum.api.data.UserData;
import com.studio.asylum.db.tables.pojos.User;

import wharf.studio.realworld.exception.NotFoundException;
import wharf.studio.realworld.mapper.UserMapper;
import wharf.studio.realworld.repository.UserRepository;

@Component
public class UpdateUserHandler implements CommandHandler<UpdateUserResult, UpdateUser> {

	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	@Override
	public UpdateUserResult handle(UpdateUser updateUser) {
		String currentUsername = updateUser.getCurrentUsername();

		User user = repository.findByUsername(currentUsername).orElseThrow(() -> {
			throw new NotFoundException();
		});

		mapper.command2pojo(updateUser, user);
		repository.update(user);		
		UserData userData = mapper.pojo2data(user);
		
		UpdateUserResult result = UpdateUserResult.builder()
									.user(userData)
									.build();
		return result;
	}

}
