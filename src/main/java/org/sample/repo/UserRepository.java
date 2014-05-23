package org.sample.repo;

import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Rakesh Komulwad on 5/20/2014.
 */

@Repository
public class UserRepository {

    @Autowired
    private RedisTemplate<String, User> template;

    public void add(User user) {
        template.opsForValue().set(user.getUserId().toString(), user);
    }

    public User get(Long key) {
        return template.opsForValue().get(key.toString());
    }

}
