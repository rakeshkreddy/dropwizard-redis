package org.sample.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Rakesh Komulwad on 5/22/2014.
 */
public class SpringRedisApplicationConfig extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("redis")
    private RedisConfiguration redisConfiguration = new RedisConfiguration();

    public RedisConfiguration getRedisConfiguration() {
        return redisConfiguration;
    }
}
