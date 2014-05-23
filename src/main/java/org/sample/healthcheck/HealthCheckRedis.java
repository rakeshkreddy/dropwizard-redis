package org.sample.healthcheck;



import com.codahale.metrics.health.HealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Rakesh Komulwad on 5/23/2014.
 */
@Component
public class HealthCheckRedis extends HealthCheck {

    @Autowired
    RedisTemplate template;



    @Override
    protected Result check() throws Exception {
        String result = (String) template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return null;
            }
        });

        if("PONG".equalsIgnoreCase(result))
            return Result.healthy("Redis is health");

        return Result.unhealthy("Redis in not healthy");
    }


}
