package wharf.studio.realworld.repository;

import java.util.Optional;

import com.studio.asylum.db.tables.pojos.User;

public interface UserRepository {
	
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    void save(User user);
    
    void update(User user);
}
