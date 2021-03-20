package wharf.studio.realworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.studio.asylum.db.tables.pojos.User;
import com.studio.asylum.db.tables.pojos.UserFollow;

import wharf.studio.realworld.command.CommandHandler;
import wharf.studio.realworld.command.FollowProfile;
import wharf.studio.realworld.command.FollowProfileResult;
import wharf.studio.realworld.data.ProfileData;
import wharf.studio.realworld.exception.InvalidException;
import wharf.studio.realworld.exception.NotFoundException;
import wharf.studio.realworld.mapper.ProfileMapper;
import wharf.studio.realworld.mapper.UserMapper;
import wharf.studio.realworld.repository.UserFollowRepository;
import wharf.studio.realworld.repository.UserRepository;

@Component
public class FollowProfileHandler implements CommandHandler<FollowProfileResult, FollowProfile> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserFollowRepository userFollowRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	ProfileMapper profileMapper;
	

	@Override
	public FollowProfileResult handle(FollowProfile followProfile) {
		
		String fansname = followProfile.getFansname();
		User fan = userRepository.findByUsername(fansname)
								.orElseThrow(() -> { 
									throw new NotFoundException();
								});
		
		String username = followProfile.getUsername();
		User user = userRepository.findByUsername(username)
								.orElseThrow(() -> { 
									throw new NotFoundException();
								});

		try {
			UserFollow follow = profileMapper.follow(user, fan);
			userFollowRepository.save(follow);
		} catch(Exception e) {
			throw new InvalidException("duplicate record");
		}
		
		ProfileData profileData = profileMapper.pojo2data(user, true);
		
		FollowProfileResult result = FollowProfileResult.builder()
										.profile(profileData)
										.build();

		return result;
	}

}
