package wharf.studio.realworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studio.asylum.api.data.ProfileData;
import com.studio.asylum.api.query.GetProfile;
import com.studio.asylum.api.query.GetProfileResult;
import com.studio.asylum.api.query.QueryHandler;
import com.studio.asylum.db.tables.pojos.User;
import com.studio.asylum.db.tables.pojos.UserFollow;

import wharf.studio.realworld.exception.NotFoundException;
import wharf.studio.realworld.mapper.ProfileMapper;
import wharf.studio.realworld.mapper.UserMapper;
import wharf.studio.realworld.repository.UserFollowRepository;
import wharf.studio.realworld.repository.UserRepository;

@Component
public class GetProfileHandler implements QueryHandler<GetProfileResult, GetProfile> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserFollowRepository userFollowRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ProfileMapper profileMapper;
	

	@Override
	public GetProfileResult handle(GetProfile getProfile) {
		String currentUsername = getProfile.getCurrentUsername();
		User currentUser = userRepository.findByUsername(currentUsername)
										.orElse(null);
				
		String username = getProfile.getUsername();
		User user = userRepository.findByUsername(username)
								.orElseThrow(() -> { 
									throw new NotFoundException();
								});
		
		UserFollow userFollow = profileMapper.follow(user, currentUser);
		boolean exist = userFollowRepository.exist(userFollow);
		
		ProfileData profileData = profileMapper.pojo2data(user, exist);
		
		GetProfileResult result = GetProfileResult.builder()
										.profile(profileData)
										.build();

		return result;
	}

}
