package org.sample.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.sample.config.SpringRedisApplicationConfig;
import org.sample.healthcheck.HealthCheckRedis;
import org.sample.resource.UserResource;
import org.sample.spring.property.YamlPropertiesFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Rakesh Komulwad on 5/22/2014.
 */
public class SampleApplication extends Application<SpringRedisApplicationConfig> {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleApplication.class);

    public static void main(String[] args) throws Exception {
		new SampleApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<SpringRedisApplicationConfig> bootstrap) {
		LOGGER.info("Application Initialized");
	}

	@Override
	public void run(SpringRedisApplicationConfig configuration, Environment environment) throws Exception {
        //set the properties from YAMl to the spring factory bean
        YamlPropertiesFactoryBean.getInstance().setProps(configuration.getRedisConfiguration().getValuesAsProperties());

        LOGGER.info("Instantiating Spring.....");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:spring-redis.xml");

        LOGGER.info("Finished Spring Instantiating.....");

        LOGGER.info("Registering resources");
        //get the bean from spring with all the dependencies resolved
        UserResource userResource = ctx.getBean(UserResource.class);
        //register
        environment.jersey().register(userResource);

        LOGGER.info("Registering HealthChecks");
        //register healthchecks
        HealthCheckRedis hcredis = ctx.getBean(HealthCheckRedis.class);
        environment.healthChecks().register("redis", hcredis);

	}

}
