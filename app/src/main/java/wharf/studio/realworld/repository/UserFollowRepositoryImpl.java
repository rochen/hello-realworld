package wharf.studio.realworld.repository;

import static com.studio.asylum.db.tables.UserFollow.USER_FOLLOWS;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.studio.asylum.db.tables.pojos.UserFollow;
import com.studio.asylum.db.tables.records.UserFollowsRecord;

import wharf.studio.realworld.mapper.ProfileMapper;

@Repository
public class UserFollowRepositoryImpl implements UserFollowRepository {
	
    @Autowired
	DSLContext jooq;

    @Autowired
    ProfileMapper mapper;

    
    @Override
    @Transactional
    public void save(UserFollow userFollow) {
        UserFollowsRecord record = mapper.pojo2record(userFollow);
		jooq.insertInto(USER_FOLLOWS)
			.set(record)
			.execute();        
    }


	@Override
	public boolean exist(UserFollow userFollow) {
		UserFollowsRecord record = mapper.pojo2record(userFollow);
		
		Integer userId = record.getUserId();
		Integer fanId = record.getFanId();
		
		boolean exists = jooq.fetchExists(USER_FOLLOWS, 
							USER_FOLLOWS.USER_ID.eq(userId), 
							USER_FOLLOWS.FAN_ID.eq(fanId));
		return exists;
	}


	@Override
	@Transactional
	public void delete(UserFollow userFollow) {
		UserFollowsRecord record = mapper.pojo2record(userFollow);
		
		Integer userId = record.getUserId();
		Integer fanId = record.getFanId();
		
		jooq.deleteFrom(USER_FOLLOWS).
				where(USER_FOLLOWS.USER_ID.eq(userId), 
					  USER_FOLLOWS.FAN_ID.eq(fanId)).
				execute();		
	}
    
}
