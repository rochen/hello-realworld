package wharf.studio.realworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studio.asylum.api.command.CommandHandler;
import com.studio.asylum.api.command.RegisterUser;
import com.studio.asylum.api.command.RegisterUserResult;
import com.studio.asylum.api.data.UserData;
import com.studio.asylum.db.tables.pojos.User;

import wharf.studio.realworld.exception.InvalidException;
import wharf.studio.realworld.mapper.UserMapper;
import wharf.studio.realworld.repository.UserRepository;

@Component
public class RegisterUserHandler implements CommandHandler<RegisterUserResult, RegisterUser> {

	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	@Override
	public RegisterUserResult handle(RegisterUser registerUser) {
		String email = registerUser.getEmail();
		String username = registerUser.getUsername();

		repository.findByEmail(email).ifPresent(u -> {
			throw new InvalidException("duplicate email");
		});
		
		repository.findByUsername(username).ifPresent(u -> {
			throw new InvalidException("duplicate username");
		});
		
		User user = mapper.command2pojo(registerUser);
		repository.save(user);
		UserData userData = mapper.pojo2data(user);
		
		RegisterUserResult result = RegisterUserResult.builder()
										.user(userData)
										.build();
		
		return result;
	}

}
