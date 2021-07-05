package wharf.studio.realworld.repository;

import com.studio.asylum.db.tables.pojos.UserFollow;

public interface UserFollowRepository {
	
    void save(UserFollow userFollow);
    
    boolean exist(UserFollow userFollow);
    
    void delete(UserFollow userFollow);
    
}