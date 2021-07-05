package wharf.studio.realworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studio.asylum.api.command.CommandHandler;
import com.studio.asylum.api.command.UnfollowProfile;
import com.studio.asylum.api.command.UnfollowProfileResult;
import com.studio.asylum.api.data.ProfileData;
import com.studio.asylum.db.tables.pojos.User;
import com.studio.asylum.db.tables.pojos.UserFollow;

import wharf.studio.realworld.exception.NotFoundException;
import wharf.studio.realworld.mapper.ProfileMapper;
import wharf.studio.realworld.mapper.UserMapper;
import wharf.studio.realworld.repository.UserFollowRepository;
import wharf.studio.realworld.repository.UserRepository;

@Component
public class UnfollowProfileHandler implements CommandHandler<UnfollowProfileResult, UnfollowProfile> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserFollowRepository userFollowRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ProfileMapper profileMapper;
	

	@Override
	public UnfollowProfileResult handle(UnfollowProfile unfollowProfile) {
		
		String fansname = unfollowProfile.getFansname();
		User fan = userRepository.findByUsername(fansname)
								.orElseThrow(() -> { 
									throw new NotFoundException();
								});
		
		String username = unfollowProfile.getUsername();
		User user = userRepository.findByUsername(username)
								.orElseThrow(() -> { 
									throw new NotFoundException();
								});

		try {
			UserFollow follow = profileMapper.follow(user, fan);
			userFollowRepository.delete(follow);
		} catch(Exception e) {
			throw new NotFoundException();
		}
		
		ProfileData profileData = profileMapper.pojo2data(user, false);
		
		UnfollowProfileResult result = UnfollowProfileResult.builder()
										.profile(profileData)
										.build();

		return result;
	}

}
