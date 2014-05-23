package org.sample.spring.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Rakesh Komulwad on 5/22/2014.
 */
public class YamlPropertiesFactoryBean extends PropertiesFactoryBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(YamlPropertiesFactoryBean.class);

    private static final YamlPropertiesFactoryBean instance = new YamlPropertiesFactoryBean();

    Properties props;

    private YamlPropertiesFactoryBean(){

    }

    public void setProps(Properties valuesAsProperties) {
        props = valuesAsProperties;
    }

    public static YamlPropertiesFactoryBean getInstance(){
        return instance;
    }

    @Override
    protected Properties createProperties() throws IOException {
        return props;
    }
}
