package wharf.studio.realworld.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studio.asylum.api.command.CommandHandler;
import com.studio.asylum.api.command.LoginUser;
import com.studio.asylum.api.command.LoginUserResult;
import com.studio.asylum.api.data.UserData;
import com.studio.asylum.db.tables.pojos.User;

import wharf.studio.realworld.exception.NotFoundException;
import wharf.studio.realworld.exception.UnauthorizedException;
import wharf.studio.realworld.mapper.UserMapper;
import wharf.studio.realworld.repository.UserRepository;

@Component
public class LoginUserHandler implements CommandHandler<LoginUserResult, LoginUser> {

	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	@Override
	public LoginUserResult handle(LoginUser loginUser) {
		String email = loginUser.getEmail();
		String password = loginUser.getPassword();

		User user = repository.findByEmail(email).orElseThrow(() -> {
			throw new NotFoundException();
		});
		
		String hash = user.getPassword();
		if(!Objects.equals(password, hash)) {
			throw new UnauthorizedException();
		}
		
		UserData userData = mapper.pojo2data(user);
		LoginUserResult result = LoginUserResult.builder()
									.user(userData)
									.build();
		return result;
	}

}
