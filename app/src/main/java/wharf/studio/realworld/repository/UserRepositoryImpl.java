package wharf.studio.realworld.repository;

import static com.studio.asylum.db.tables.User.USERS;

import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.studio.asylum.db.tables.pojos.User;
import com.studio.asylum.db.tables.records.UsersRecord;

import wharf.studio.realworld.mapper.UserMapper;;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	DSLContext jooq;
	
	@Autowired
	UserMapper mapper;

	@Override
	public Optional<User> findByEmail(String email) {		
		UsersRecord record = jooq.selectFrom(USERS)
			.where(USERS.EMAIL.eq(email))
			.fetchOne();
		
		User user = mapper.record2pojo(record);
		
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> findByUsername(String username) {
		UsersRecord record = jooq.selectFrom(USERS)
				.where(USERS.USERNAME.eq(username))
				.fetchOne();
			
		User user = mapper.record2pojo(record);
		
		return Optional.ofNullable(user);
	}

	@Transactional
	@Override
	public void save(User user) {
		UsersRecord record = mapper.pojo2record(user);		
		jooq.insertInto(USERS)
			.set(record)
			.execute();
	}

	@Transactional
	@Override
	public void update(User user) {
		Long userId = user.getUserId();
		UsersRecord record = mapper.pojo2record(user);	
		jooq.update(USERS)
			.set(record)
			.where(USERS.USER_ID.equal(userId))
			.execute();
	}



}
